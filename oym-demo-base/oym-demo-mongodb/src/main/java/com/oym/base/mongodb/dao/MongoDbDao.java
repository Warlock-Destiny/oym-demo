package com.oym.base.mongodb.dao;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.oym.base.mongodb.entity.BaseCommonEntity;
import com.oym.base.mongodb.exception.NoSuchParameterTypeException;
import com.oym.base.mongodb.util.ClassUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: MongoDbDao
 * @Description: 
 * @author: zhangyd
 * @date: 2020/10/21 13:39
 */
@Slf4j
public abstract class MongoDbDao<T extends BaseCommonEntity> implements BaseDao<T> {

    protected Class<T> tClass;
    protected List<PropertyDescriptor> propertyDescriptorList;
    protected Map<String, Method> readMethodMap;

    @Autowired
    protected MongoTemplate mongoTemplate;

    public MongoDbDao() {
        try {
            this.tClass = (Class<T>) initParamType();
            this.propertyDescriptorList = initPropertyDescriptor(tClass);
            this.readMethodMap = initReadMethod(propertyDescriptorList);
        } catch (NoSuchParameterTypeException | IntrospectionException e) {
            throw new RuntimeException("init fail:" + this.getClass());
        }
    }

    /***
     *  新增
     */
    public void insert(T t) {
        t.setCreateTime(now());
        this.mongoTemplate.insert(t);
    }

    /***
     * 保存
     */
    public void save(T t) {
        t.setCreateTime(now());
        this.mongoTemplate.save(t);
    }


    /***
     * 根据id查询对象
     */
    public T queryById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        return this.mongoTemplate.findOne(query, tClass);
    }

    /**
     * 根据条件查询集合
     */
    public List<T> queryList(T object) {
        Query query = getQueryByObject(object);
        return mongoTemplate.find(query, tClass);
    }

    /**
     * 查询所有
     */
    public List<T> queryAll() {
        return mongoTemplate.findAll(tClass);
    }

    /**
     * 根据条件查询只返回一个文档
     */
    public T queryOne(T object) {
        Query query = getQueryByObject(object);
        return mongoTemplate.findOne(query, tClass);
    }

    /***
     * 根据条件分页查询
     */
    public List<T> getPage(T object, int start, int size) {
        Query query = getQueryByObject(object);
        query.skip(start);
        query.limit(size);
        return this.mongoTemplate.find(query, tClass);
    }

    /***
     * 根据条件查询库中符合条件的记录数量
     */
    public Long getCount(T object) {
        Query query = getQueryByObject(object);
        return this.mongoTemplate.count(query, tClass);
    }

    /***
     * 删除对象
     */
    public int delete(T t) {
        return (int) this.mongoTemplate.remove(t).getDeletedCount();
    }

    /**
     * 根据id删除
     */
    public void deleteById(String id) {
        Criteria criteria = Criteria.where("_id").is(id);
        Query query = new Query(criteria);
        T obj = this.mongoTemplate.findOne(query, tClass);
        if (obj != null) {
            this.delete(obj);
        }
    }

    /*
     * MongoDB中更新操作分为三种
     * 1：updateFirst     修改第一条
     * 2：updateMulti     修改所有匹配的记录
     * 3：upsert  修改时如果不存在则进行添加操作
     * */

    /**
     * 修改匹配到的第一条记录
     */
    public void updateFirst(String id, T targetObj) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = getUpdateByObject(targetObj);
        this.mongoTemplate.updateFirst(query, update, tClass);
    }

    /***
     * 修改匹配到的所有记录
     */
    public void updateMulti(T srcObj, T targetObj) {
        Query query = getQueryByObject(srcObj);
        Update update = getUpdateByObject(targetObj);
        this.mongoTemplate.updateMulti(query, update, tClass);
    }

    /***
     * 修改匹配到的记录，若不存在该记录则进行添加
     */
    public void updateInsert(T srcObj, T targetObj) {
        Query query = getQueryByObject(srcObj);
        Update update = getUpdateByObject(targetObj);
        this.mongoTemplate.upsert(query, update, tClass);
    }

    /**
     * 将查询条件对象转换为query
     */
    private Query getQueryByObject(T object) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        readMethodMap.forEach((fieldName, method) -> {
            try {
                Object value = method.invoke(object);
                if (value != null) {
                    criteria.and(fieldName).is(value);
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                log.error("get field:{} error", fieldName, e);
            }
        });
        query.addCriteria(criteria);
        return query;
    }


    /**
     * 将查询条件对象转换为update
     */
    private Update getUpdateByObject(T object) {
        Update update = new Update();
        readMethodMap.forEach((fieldName, method) -> {
            try {
                Object value = method.invoke(object);
                if (value != null) {
                    update.set(fieldName, value);
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                log.error("get field:{} error", fieldName, e);
            }
        });
        return update;
    }


    private Class<?> initParamType() throws NoSuchParameterTypeException {
        return ClassUtil.getActualParameter(this.getClass(), 0);
    }

    private List<PropertyDescriptor> initPropertyDescriptor(Class<T> clazz) throws IntrospectionException {
        return ImmutableList.copyOf(ClassUtil.getProperty(clazz));
    }

    private Map<String, Method> initReadMethod(List<PropertyDescriptor> propertyDescriptorList) {
        ImmutableMap.Builder<String, Method> builder = ImmutableMap.builder();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptorList) {
            builder.put(propertyDescriptor.getName(), propertyDescriptor.getReadMethod());
        }
        return builder.build();
    }

    private Date now() {
        return new Date();
    }

}