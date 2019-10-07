package com.cn.zyd.activiti.service;

import com.cn.zyd.activiti.dao.ActGeBytearrayDao;
import com.cn.zyd.activiti.dao.ActReDeploymentDao;
import com.cn.zyd.activiti.dao.ActReProcdefDao;
import com.cn.zyd.activiti.dao.ColumnsDao;
import com.cn.zyd.activiti.db.EntityModel;
import com.cn.zyd.activiti.db.SqlBuilder;
import com.cn.zyd.activiti.entity.ActGeBytearray;
import com.cn.zyd.activiti.entity.ColumnInfo;
import org.activiti.api.task.runtime.TaskRuntime;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ActivitiService {

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
    @Autowired
    private TaskRuntime taskRuntime;
    @Autowired
    private RuntimeService runtimeService;

    /**
     * 根据bpmn转换成sql
     */
    public String deploy(String deploymentName, String bpmnXml) {
        // 1 部署实例
        Deployment deployment = repositoryService.createDeployment().addString(deploymentName, bpmnXml).deploy();
        if (deployment == null) {
            return "";
        }
        return builderSql(deployment.getId());
    }

    String builderSql(String deploymentId) {
        // 2 从数据库获取添加的实例转成sql
        // act_re_deployment
        List<SqlBuilder> sqlBuilderList = new ArrayList<>();
        sqlBuilderList.add(madeSqlBuilder(DB, ACT_RE_DEPLOYMENT, actReDeploymentDao.selectById(deploymentId)));
        // act_ge_bytearray
        List<Map<String, Object>> actGeBytearrayList = actGeBytearrayDao.selectByDeploymentId(deploymentId);
        actGeBytearrayList.forEach(x -> {
            sqlBuilderList.add(madeSqlBuilder(DB, ACT_GE_BYTEARRAY, x));
        });
        // act_re_procdef
        sqlBuilderList.add(madeSqlBuilder(DB, ACT_RE_PROCDEF, actReProcdefDao.selectByDeploymentId(deploymentId)));
        StringBuilder sb = new StringBuilder();
        sqlBuilderList.forEach(x -> {
            sb.append(x.buildInsertSql()).append("\n");
        });
        sqlBuilderList.forEach(x -> {
            sb.append(x.buildDeleteSql()).append("\n");
        });
        return sb.toString();
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
