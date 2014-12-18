package cn.edu.xmu.comm.commons.persistence;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * DAO支持类的实现
 *
 * @param <T>
 * @version 2014-12-16
 * @Author Mengmeng Yang
 */
public class BaseDAO<T, I extends Serializable> {

    @Resource
    private SessionFactory sessionFactory;

    private Class<T> clazz;

    public BaseDAO() {
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        this.clazz = (Class) type.getActualTypeArguments()[0];
    }

    /**
     * 获取session
     *
     * @return
     */
    public Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * 强制与数据库同步
     */
    public void flush() {
        currentSession().flush();
    }

    /**
     * 清楚缓存数据
     */
    public void clear() {
        currentSession().clear();
    }

    /**
     * 持久化或更新实体对象
     *
     * @param entity
     */
    public void saveOrUpdate(T entity) {
        currentSession().saveOrUpdate(entity);
    }

    /**
     * 持久化或更新实体对象
     *
     * @param entity
     */
    public void merge(T entity) {
        currentSession().merge(entity);
    }

    /**
     * 删除实体类
     *
     * @param entity
     */
    public void delete(T entity) {
        currentSession().delete(entity);
    }

    /**
     * 通过id获取实体
     *
     * @param id
     * @return
     */
    public T getById(I id) {
        return (T) currentSession().get(clazz, id);
    }

    /**
     * 获取所有实体
     *
     * @return
     */
    public List<T> getAll() {
        return searchByHql("from " + clazz.getName(), new Parameter());
    }

    /**
     * 通过Hql语句获取单个实体
     *
     * @param qlString
     * @param parameter
     * @return
     */
    public T getByHql(String qlString, Parameter parameter) {
        Query query = createQuery(qlString, parameter);
        return (T) query.uniqueResult();
    }

    /**
     * 通过Hql语句查找
     *
     * @param qlString
     * @param parameter
     * @return
     */
    public List<T> searchByHql(String qlString, Parameter parameter) {
        Query query = createQuery(qlString, parameter);
        return query.list();
    }

    //region Private Methods

    /**
     * 创建 QL 查询对象
     *
     * @param qlString
     * @param parameter
     * @return
     */
    private Query createQuery(String qlString, Parameter parameter) {
        Query query = currentSession().createQuery(qlString);
        setParameter(query, parameter);
        return query;
    }

    /**
     * 设置查询参数
     *
     * @param query
     * @param parameter
     */
    private void setParameter(Query query, Parameter parameter) {
        if (parameter != null) {
            Set<String> keySet = parameter.keySet();
            for (String string : keySet) {
                Object value = parameter.get(string);
                //这里考虑传入的参数是什么类型，不同类型使用的方法不同
                if (value instanceof Collection<?>) {
                    query.setParameterList(string, (Collection<?>) value);
                } else if (value instanceof Object[]) {
                    query.setParameterList(string, (Object[]) value);
                } else {
                    query.setParameter(string, value);
                }
            }
        }
    }
    //endregion

}
