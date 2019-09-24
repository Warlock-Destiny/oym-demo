package com.cn.zyd.web.zhangyd.freemarker;

import com.cn.zyd.web.zhangyd.freemarker.dao.MysqlDao;
import com.cn.zyd.web.zhangyd.freemarker.model.ColumnInfo;
import com.cn.zyd.web.zhangyd.freemarker.model.TableInfo;
import com.cn.zyd.web.zhangyd.freemarker.util.DbUtil;
import com.cn.zyd.web.zhangyd.freemarker.util.SpringContextUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class FreemarkerApplication {

    public static void main(String[] args) throws IOException, TemplateException {
        SpringApplication.run(FreemarkerApplication.class, args);
        TableInfo tableInfo = SpringContextUtil.getBean(MysqlDao.class).getDbInfo("test", "t_syn_order_info");
        tableInfo.setTableName(DbUtil.db2Up(tableInfo.getTableName()));
        List<ColumnInfo> columnInfo = SpringContextUtil.getBean(MysqlDao.class).getColumnInfo("test", "t_syn_order_info");
        columnInfo.forEach(x -> {
            String type = x.getType();
            String columnName = x.getColumnName();
            x.setColumnName(DbUtil.column2UpWrite(columnName));
            x.setType(DbUtil.getjavaType(type));
        });
        tableInfo.setColumnInfoList(columnInfo);
        Configuration configuration = SpringContextUtil.getBean(FreeMarkerConfigurationFactoryBean.class).getObject();
        Template template = configuration.getTemplate("model.ftl");
        template.process(tableInfo, new FileWriter("D://aa.txt"));
    }


}
