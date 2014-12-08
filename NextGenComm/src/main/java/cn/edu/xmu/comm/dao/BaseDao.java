package cn.edu.xmu.comm.dao;

import org.hibernate.Query;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Set;

/**
 * Created by Roger on 2014/12/8 0008.
 */
public interface BaseDao<T, I extends Serializable> {

    /**
     * 强制与数据库同步
     */
    public void flush();

    /**
     * 清楚缓存数据
     */
    public void clear();

    /**
     * 持久化或更新实体对象
     *
     * @param entity
     */
    public void saveOrUpdate(T entity);

    /**
     * 持久化或更新尸体对象
     *
     * @param entity
     */
    public void merge(T entity);

    /**
     * 删除实体类
     *
     * @param entity
     */
    public void delete(T entity);

    /**
     * 获取实体
     *
     * @param qlString
     * @param parameter
     * @return
     */
    public T getByHql(String qlString, Parameter parameter);

    /**
     * 创建 QL 查询对象
     *
     * @param qlString
     * @param parameter
     * @return
     */
    public Query createQuery(String qlString, Parameter parameter);

}
