package cn.edu.xmu.comm.commons.persistence;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Iterator;
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
     * @return session
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
     * 持久化实体对象，立即生成SQL语句
     *
     * @param entity 实体对象
     * @return 实体对象id
     */
    @SuppressWarnings("unchecked")
    public I save(T entity) {
        return (I) currentSession().save(entity);
    }

    /**
     * 持久化实体对象，会话结束时生成SQL语句
     *
     * @param entity 实体对象
     */
    public void persist(T entity) {
        currentSession().persist(entity);
    }

    /**
     * 更新实体对象
     *
     * @param entity 实体对象
     */
    public void update(T entity) {
        currentSession().update(entity);
    }

    /**
     * 将实体对象属性覆盖到数据库
     *
     * @param entity 实体对象
     */
    public void merge(T entity) {
        currentSession().merge(entity);
    }

    /**
     * 持久化或更新实体对象
     *
     * @param entity 实体对象
     */
    public void saveOrUpdate(T entity) {
        currentSession().saveOrUpdate(entity);
    }

    /**
     * 删除实体对象
     *
     * @param entity 实体对象
     */
    public void delete(T entity) {
        currentSession().delete(entity);
    }

    /**
     * 通过id加载实体对象
     *
     * @param id 实体对象id
     * @return 实体对象
     */
    @SuppressWarnings("unchecked")
    public T load(I id) {
        return (T) currentSession().load(clazz, id);
    }

    /**
     * 通过id加载实体对象
     *
     * @param entity 目标实体对象
     * @param id     实体对象id
     */
    public void load(T entity, I id) {
        currentSession().load(entity, id);
    }

    /**
     * 通过id获取实体对象
     *
     * @param id 实体对象id
     * @return 实体对象
     */
    @SuppressWarnings("unchecked")
    public T get(I id) {
        return (T) currentSession().get(clazz, id);
    }

    /**
     * 获取所有实体对象
     *
     * @return 实体对象列表
     */
    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        return (List<T>) currentSession().createCriteria(clazz).list();
    }

    /**
     * 获取所有实体对象的迭代器
     *
     * @return 迭代器
     */
    @SuppressWarnings("unchecked")
    public Iterator<T> getAllIterator() {
        Query query = createQuery("from " + clazz.getSimpleName(), new Parameter());
        return query.iterate();
    }

    /**
     * 通过QL语句获取单个实体
     *
     * @param qlString  查询语句
     * @param parameter 查询参数
     * @return 实体对象
     */
    @SuppressWarnings("unchecked")
    public T getByQL(String qlString, Parameter parameter) {
        Query query = createQuery(qlString, parameter);
        return (T) query.uniqueResult();
    }

    /**
     * 通过QL语句查找
     *
     * @param qlString  查询语句
     * @param parameter 查询参数
     * @return 实体列表
     */
    @SuppressWarnings("unchecked")
    public List<T> searchByQL(String qlString, Parameter parameter) {
        Query query = createQuery(qlString, parameter);
        return query.list();
    }

    /**
     * 通过QL语句查找，返回迭代器
     *
     * @param qlString  查询语句
     * @param parameter 查询参数
     * @return 迭代器
     */
    @SuppressWarnings("unchecked")
    public Iterator<T> searchByQLIterator(String qlString, Parameter parameter) {
        Query query = createQuery(qlString, parameter);
        return query.iterate();
    }


    //region Protected And Private Methods

    /**
     * 创建 QL 查询对象
     */
    protected Query createQuery(String qlString, Parameter parameter) {
        Query query = currentSession().createQuery(qlString);
        setParameter(query, parameter);
        return query;
    }

    /**
     * 设置查询参数
     */
    private void setParameter(Query query, Parameter parameter) {
        if (parameter != null) {
            Set<String> keySet = parameter.keySet();
            for (String string : keySet) {
                Object value = parameter.get(string);
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
