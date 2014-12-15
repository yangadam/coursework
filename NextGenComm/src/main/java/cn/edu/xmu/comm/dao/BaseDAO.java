package cn.edu.xmu.comm.dao;

import org.hibernate.Query;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by Yummy on 12/4/2014 0004.
 */
public class BaseDAO<T, I extends Serializable> extends HibernateDaoSupport {

    private Class<T> clazz;

    public BaseDAO() {
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        clazz = (Class<T>) type.getActualTypeArguments()[0];
    }

    public void flush() {
        currentSession().flush();
    }

    public void clear() {
        currentSession().clear();
    }

    public void saveOrUpdate(T entity) {
        currentSession().saveOrUpdate(entity);
    }

    public void saveOrUpdate(List<T> entities) {
        for (T entity : entities) {
            saveOrUpdate(entity);
        }
    }

    public void delete(T entity) {
        currentSession().delete(entity);
    }

    public void delete(List<T> entities) {
        for (T entity : entities) {
            delete(entity);
        }
    }

    public List<T> getAll() {
        return currentSession().createCriteria(clazz).list();
    }

    public T getById(I id) {
        return (T) currentSession().get(clazz, id);
    }

    public T getByHql(String qlString) {
        return getByHql(qlString, null);
    }

    public T getByHql(String qlString, Parameter parameter) {
        Query query = createQuery(qlString, parameter);
        return (T) query.uniqueResult();
    }

    public Query createQuery(String qlString, Parameter parameter) {
        Query query = currentSession().createQuery(qlString);
        setParameter(query, parameter);
        return query;
    }

    private void setParameter(Query query, Parameter parameter) {
        if (parameter != null) {
            Set<String> keySet = parameter.keySet();
            for (String key : keySet) {
                Object value = parameter.get(key);
                if (value instanceof Collection<?>) {
                    query.setParameterList(key, (Collection<?>) value);
                } else if (value instanceof Object[]) {
                    query.setParameterList(key, (Object[]) value);
                } else {
                    query.setParameter(key, value);
                }
            }
        }
    }

}
