package com.cn.zyd.auth.service;

import com.cn.zyd.auth.dao.ActReDeploymentDao;
import com.cn.zyd.auth.dao.ColumnsDao;
import com.cn.zyd.auth.db.EntityModel;
import com.cn.zyd.auth.db.SqlBuilder;
import com.cn.zyd.auth.entity.ColumnInfo;
import org.activiti.engine.RepositoryService;
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
    private RepositoryService repositoryService;
    @Autowired
    private ActReDeploymentDao actReDeploymentDao;
    @Autowired
    private ColumnsDao columnsDao;

    /**
     * 根据bpmn转换成sql
     */
    public void createProcess(String deploymentName, String bpmnXml) {
        // 1 部署实例
        Deployment deployment = repositoryService.createDeployment().addString(deploymentName, bpmnXml).deploy();
        // 2 从数据库获取添加的实例转成sql
        String deploymentId = deployment.getId();
        Map<String, Object> actReDeploymentMap = actReDeploymentDao.selectById(deploymentId);
        // act_re_deployment
        SqlBuilder deploymentSqlBuilder = madeSqlBuilder(DB, ACT_RE_DEPLOYMENT, actReDeploymentMap);
        // act_re_procdef
        SqlBuilder procdefSqlBuilder = madeSqlBuilder(DB, ACT_GE_BYTEARRAY, actReDeploymentMap);
        // act_ge_bytearray
        SqlBuilder bytearraySqlBuilder = madeSqlBuilder(DB, ACT_GE_BYTEARRAY, actReDeploymentMap);
        // 组装sql
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
