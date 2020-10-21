package com.oym.base.mongodb.dao;

import com.oym.base.mongodb.entity.ConnectInfo;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ConnectDao extends MongoDbDao<ConnectInfo> {
    /***
     * 根据id查询对象
     */
    public List<ConnectInfo> queryByIds(List<String> ids) {
        Query query = new Query(Criteria.where("_id").in(ids));
        return this.mongoTemplate.find(query, tClass);
    }
}
