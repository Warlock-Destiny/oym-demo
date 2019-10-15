package com.cn.zyd.activiti.service.impl;

import com.cn.zyd.activiti.dao.ActGeBytearrayDao;
import com.cn.zyd.activiti.dao.ActReDeploymentDao;
import com.cn.zyd.activiti.dao.ActReProcdefDao;
import com.cn.zyd.activiti.dao.ColumnsDao;
import com.cn.zyd.activiti.db.EntityModel;
import com.cn.zyd.activiti.db.SqlBuilder;
import com.cn.zyd.activiti.dto.DeploymentDto;
import com.cn.zyd.activiti.dto.ProcessDto;
import com.cn.zyd.activiti.entity.ColumnInfo;
import com.cn.zyd.activiti.service.DeploymentService;
import lombok.extern.slf4j.Slf4j;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.impl.util.io.StringStreamSource;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class DeploymentServiceImpl implements DeploymentService {

    private static final String DB = "activiti";
    private static final String ACT_GE_BYTEARRAY = "act_ge_bytearray";
    private static final String ACT_RE_PROCDEF = "act_re_procdef";
    private static final String ACT_RE_DEPLOYMENT = "act_re_deployment";

    @Autowired
    private ActReDeploymentDao actReDeploymentDao;
    @Autowired
    private ActGeBytearrayDao actGeBytearrayDao;
    @Autowired
    private ActReProcdefDao actReProcdefDao;
    @Autowired
    private ColumnsDao columnsDao;

    @Autowired
    private RepositoryService repositoryService;

    /**
     * 部署流程 并且返回sql
     *
     * @param deploymentId 如果deploymenbtId为null，则代表插入，否则更新
     * @return 本次部署的sql
     */
    @Override
    public String deploy(String deployName, String deploymentId, String bpmnXml) {
        //校验
        BpmnXMLConverter bpmnXMLConverter = new BpmnXMLConverter();
        try {
            bpmnXMLConverter.convertToBpmnModel(new StringStreamSource(bpmnXml), true, true);
        } catch (Exception e) {
            log.error("bpmn解析错误,deployName:{}", deployName, e);
            return "bpmn解析错误,deployName:" + deployName + "错误信息:" + e.getMessage();
        }
        //初次部署
        if (StringUtils.isEmpty(deploymentId)) {
            Deployment deployment = repositoryService.createDeployment().addString(deployName + ".bpmn", bpmnXml).name(deployName).deploy();
            if (deployment == null) {
                return "部署失败";
            }
            return builderSql(deployment.getId());
        }
        //更新部署
        actGeBytearrayDao.updateBpmnByDeploymentId(deploymentId, bpmnXml.getBytes(StandardCharsets.UTF_8));
        Deployment deployment = repositoryService.createDeploymentQuery().deploymentId(deploymentId).singleResult();
        return builderSql(deployment.getId());
    }

    /**
     * 展示已部署的列表
     */
    @Override
    public List<DeploymentDto> listDeployment() {
        List<Deployment> deploymentList = repositoryService.createDeploymentQuery().list();
        List<DeploymentDto> deploymentDtoList = new ArrayList<>();
        deploymentList.forEach(deployment ->
                deploymentDtoList.add(new DeploymentDto()
                        .setId(deployment.getId())
                        .setName(deployment.getName())
                        .setDeploymentTime(deployment.getDeploymentTime())));
        return deploymentDtoList;
    }

    @Override
    public List<ProcessDto> listProcess() {
        List<ProcessDefinition> processInstanceList = repositoryService.createProcessDefinitionQuery().list();
        List<ProcessDto> processDtoList = new ArrayList<>(processInstanceList.size());
        processInstanceList.forEach(processDefinition ->
                processDtoList.add(new ProcessDto()
                        .setId(processDefinition.getId())
                        .setName(processDefinition.getName())
                        .setDeploymentId(processDefinition.getDeploymentId())
                        .setDescription(processDefinition.getDescription())
                        .setKey(processDefinition.getKey())
                ));
        return processDtoList;
    }


    /**
     * 展示部署的具体xml
     *
     * @param deploymentId 部署id
     * @return 详细信息 带bpmn
     */
    @Override
    public DeploymentDto detail(String deploymentId) {
        Deployment deployment = repositoryService.createDeploymentQuery().deploymentId(deploymentId).singleResult();
        if (deployment == null) {
            return null;
        }
        List<Map<String, Object>> list = actGeBytearrayDao.selectByDeploymentId(deploymentId);
        if (!list.isEmpty()) {
            return new DeploymentDto()
                    .setId(deployment.getId())
                    .setName(deployment.getName())
                    .setDeploymentTime(deployment.getDeploymentTime())
                    .setBpmnXml(new String((byte[]) list.get(0).get("BYTES_"), StandardCharsets.UTF_8));
        }
        return null;
    }

    @Override
    public String remove(String deploymentId) {
        String builderSql = builderDeleteSql(deploymentId);
        repositoryService.deleteDeployment(deploymentId);
        return builderSql;
    }

    String builderSql(String deploymentId) {
        List<SqlBuilder> sqlBuilderList = getBpmnEntity(deploymentId);
        StringBuilder sb = new StringBuilder();
        sb.append("-------------------插入-------------------").append("\n");
        sqlBuilderList.forEach(x -> {
            sb.append(x.buildInsertSql()).append("\n");
        });
        sb.append("-------------------回滚-------------------").append("\n");
        sqlBuilderList.forEach(x -> {
            sb.append(x.buildDeleteSql()).append("\n");
        });
        return sb.toString();
    }

    String builderDeleteSql(String deploymentId) {
        List<SqlBuilder> sqlBuilderList = getBpmnEntity(deploymentId);
        StringBuilder sb = new StringBuilder();
        sqlBuilderList.forEach(x -> {
            sb.append(x.buildDeleteSql()).append("\n");
        });
        return sb.toString();
    }

    private List<SqlBuilder> getBpmnEntity(String deploymentId) {
        List<SqlBuilder> sqlBuilderList = new ArrayList<>();
        sqlBuilderList.add(madeSqlBuilder(DB, ACT_RE_DEPLOYMENT, actReDeploymentDao.selectById(deploymentId)));
        List<Map<String, Object>> actGeBytearrayList = actGeBytearrayDao.selectByDeploymentId(deploymentId);
        actGeBytearrayList.forEach(x -> {
            sqlBuilderList.add(madeSqlBuilder(DB, ACT_GE_BYTEARRAY, x));
        });
        sqlBuilderList.add(madeSqlBuilder(DB, ACT_RE_PROCDEF, actReProcdefDao.selectByDeploymentId(deploymentId)));
        return sqlBuilderList;
    }

    private SqlBuilder madeSqlBuilder(String db, String table, Map<String, Object> actReDeploymentMap) {
        List<ColumnInfo> columnInfoList = columnsDao.selectTableInfoByDbAndTable(db, table);
        EntityModel entityModel = new EntityModel();
        List<EntityModel.Field> fieldList = new ArrayList<>();
        columnInfoList.forEach(x -> {
            EntityModel.Field field = new EntityModel.Field();
            field.setPrimary(StringUtils.equals(x.getColumnKey(), "PRI"))
                    .setColumnName(x.getColumnName())
                    .setFieldType(x.getDataType());
            fieldList.add(field);
        });
        entityModel.setColumns(fieldList);
        return SqlBuilder.newInstance(db, table, entityModel, actReDeploymentMap);

    }

}