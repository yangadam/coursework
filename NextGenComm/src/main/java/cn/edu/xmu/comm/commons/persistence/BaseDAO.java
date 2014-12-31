package cn.edu.xmu.comm.commons.persistence;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.*;

/**
 * DAO支持类的实现
 *
 * @param <T> 实体类型参数
 * @param <I> 实体主键类型参数
 * @author Mengmeng Yang
 * @version 2014-12-16
 */
public class BaseDAO<T, I extends Serializable> {

    @Resource
    private SessionFactory sessionFactory;

    private Class<T> clazz;
    private String clazzName;

    @SuppressWarnings("unchecked")
    protected BaseDAO() {
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        this.clazz = (Class<T>) type.getActualTypeArguments()[0];
        this.clazzName = this.clazz.getSimpleName();
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
     * 清除缓存数据
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
     * 持久化实体对象列表，立即生成SQL语句
     *
     * @param entities 实体对象列表
     */
    public List<I> save(List<T> entities) {
        List<I> ids = new ArrayList<I>();
        for (int i = 0; i < entities.size(); i++) {
            ids.add(save(entities.get(i)));
            if (i % 20 == 0) {
                currentSession().flush();
                currentSession().clear();
            }
        }
        return ids;
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
     * 持久化实体对象列表，立即生成SQL语句
     *
     * @param entities 实体对象列表
     */
    public void persist(List<T> entities) {
        for (int i = 0; i < entities.size(); i++) {
            persist(entities.get(i));
            if (i % 20 == 0) {
                currentSession().flush();
                currentSession().clear();
            }
        }
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
     * 通过id删除实体对象
     *
     * @param id id
     */
    public void delete(I id) {
        String ql = "delete from " + clazzName + " t where t.id = :p1";
        createQuery(ql, new Parameter(id)).executeUpdate();
    }

    /**
     * 查询记录的数量
     *
     * @param qlString  查询语句
     * @param parameter 参数
     * @return 记录数量
     */
    public Long count(String qlString, Parameter parameter) {
        int beginPos = qlString.toLowerCase().indexOf("from");
        String countString = "select count(*) ".concat(qlString.substring(beginPos));
        return (Long) createQuery(countString, parameter).uniqueResult();
    }

    /**
     * 从数据库刷新实体对象
     *
     * @param entity 实体对象
     */
    public void refresh(T entity) {
        currentSession().refresh(entity);
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
        return searchByQL("from " + clazz.getSimpleName(), new Parameter());
    }

    /**
     * 获取所有实体对象（分页）
     *
     * @param page 分页对象
     * @return 分页对象
     */
    public Page<T> getAll(Page<T> page) {
        String ql = "from " + clazz.getSimpleName();
        return searchByQL(ql, new Parameter(), page);
    }

    /**
     * 获取所有实体对象的迭代器
     *
     * @return 迭代器
     */
    @SuppressWarnings("unchecked")
    public Iterator<T> getAllIterator() {
        Query query = createQuery("select x from " + clazzName + " x", new Parameter());
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
     * 通过QL语句查找属性
     *
     * @param qlString  查询语句
     * @param parameter 查询参数
     * @return 属性列表
     */
    @SuppressWarnings("unchecked")
    public List getAttrsByQL(String qlString, Parameter parameter) {
        Query query = createQuery(qlString, parameter);
        return query.list();
    }

    /**
     * 通过QL语句查找
     *
     * @param qlString  查询语句
     * @param parameter 查询参数
     * @param page      分页对象
     * @return 分页对象
     */
    @SuppressWarnings("unchecked")
    public Page<T> searchByQL(String qlString, Parameter parameter, Page<T> page) {
        if (page.getCount() == -1) {
            page.setCount(count(qlString, new Parameter()));
        }
        if (page.isEnable()) {
            Query query = createQuery(qlString, new Parameter());
            query.setFirstResult(page.start());
            query.setMaxResults(page.getPageSize());
            page.setList(query.list());
        } else {
            page.setList(getAll());
        }
        return page;
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
     *
     * @param qlString  查询语句
     * @param parameter 查询参数
     * @return 查询对象
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
