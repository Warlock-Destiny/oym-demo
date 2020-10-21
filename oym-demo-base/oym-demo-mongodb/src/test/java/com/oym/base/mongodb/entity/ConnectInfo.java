package com.oym.base.mongodb.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 连接表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Document("t_connect_info1")
public class ConnectInfo extends BaseCommonEntity {
    @Field(value = "ip")
    private String ip;
    @Field(value = "port")
    private Integer port;
    @Field(value = "username")
    private String username;
    @Field(value = "password")
    private String password;
    @Field(value = "path")
    private String path;
    @Field(value = "connect_name")
    private String connectName;
}
