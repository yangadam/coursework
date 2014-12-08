package cn.edu.xmu.comm.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Set;

/**
 * Created by Roger on 2014/12/8 0008.
 */
public class BaseDaoImpl<T, I extends Serializable> {

    @Autowired
    private SessionFactory sessionFactory;

    private Class<T> clazz;

    public BaseDaoImpl() {
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        this.clazz = (Class) type.getActualTypeArguments()[0];
    }

    private Session currentSession() {
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
     * 持久化或更新尸体对象
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
     * 获取实体
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
     * 创建 QL 查询对象
     *
     * @param qlString
     * @param parameter
     * @return
     */
    public Query createQuery(String qlString, Parameter parameter) {
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

}
