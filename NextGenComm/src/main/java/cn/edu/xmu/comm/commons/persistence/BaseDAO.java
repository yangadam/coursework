package cn.edu.xmu.comm.commons.persistence;

import org.hibernate.Session;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

/**
 * description
 *
 * @author Mengmeng Yang
 * @version 1/5/2015 0005
 */
public interface BaseDAO<T, I extends Serializable> {
    /**
     * 获取session
     *
     * @return session
     */
    Session currentSession();

    /**
     * 强制与数据库同步
     */
    void flush();

    /**
     * 清除缓存数据
     */
    void clear();

    /**
     * 持久化实体对象，立即生成SQL语句
     *
     * @param entity 实体对象
     * @return 实体对象id
     */
    @SuppressWarnings("unchecked")
    I save(T entity);

    /**
     * 持久化实体对象列表，立即生成SQL语句
     *
     * @param entities 实体对象列表
     */
    List<I> save(List<T> entities);

    /**
     * 持久化实体对象，会话结束时生成SQL语句
     *
     * @param entity 实体对象
     */
    void persist(T entity);

    /**
     * 持久化实体对象列表，立即生成SQL语句
     *
     * @param entities 实体对象列表
     */
    void persist(List<T> entities);

    /**
     * 更新实体对象
     *
     * @param entity 实体对象
     */
    void update(T entity);

    /**
     * 将实体对象属性覆盖到数据库
     *
     * @param entity 实体对象
     */
    void merge(T entity);

    /**
     * 将实体对象列表覆盖到数据库
     *
     * @param entities 实体对象
     */
    void merge(List<T> entities);

    /**
     * 持久化或更新实体对象
     *
     * @param entity 实体对象
     */
    void saveOrUpdate(T entity);

    /**
     * 删除实体对象
     *
     * @param entity 实体对象
     */
    void delete(T entity);

    /**
     * 通过id删除实体对象
     *
     * @param id id
     */
    void delete(I id);

    /**
     * 查询记录的数量
     *
     * @param qlString  查询语句
     * @param parameter 参数
     * @return 记录数量
     */
    Long count(String qlString, Parameter parameter);

    /**
     * 从数据库刷新实体对象
     *
     * @param entity 实体对象
     */
    void refresh(T entity);

    /**
     * 通过id加载实体对象
     *
     * @param id 实体对象id
     * @return 实体对象
     */
    @SuppressWarnings("unchecked")
    T load(I id);

    /**
     * 通过id加载实体对象
     *
     * @param entity 目标实体对象
     * @param id     实体对象id
     */
    void load(T entity, I id);

    /**
     * 通过id获取实体对象
     *
     * @param id 实体对象id
     * @return 实体对象
     */
    @SuppressWarnings("unchecked")
    T get(I id);

    /**
     * 获取所有实体对象
     *
     * @return 实体对象列表
     */
    @SuppressWarnings("unchecked")
    List<T> getAll();

    /**
     * 获取所有实体对象（分页）
     *
     * @param page 分页对象
     * @return 分页对象
     */
    Page<T> getAll(Page<T> page);

    /**
     * 获取所有实体对象的迭代器
     *
     * @return 迭代器
     */
    @SuppressWarnings("unchecked")
    Iterator<T> getAllIterator();

    /**
     * 通过QL语句获取单个实体
     *
     * @param qlString  查询语句
     * @param parameter 查询参数
     * @return 实体对象
     */
    @SuppressWarnings("unchecked")
    T getByQL(String qlString, Parameter parameter);

    /**
     * 通过QL语句查找
     *
     * @param qlString  查询语句
     * @param parameter 查询参数
     * @return 实体列表
     */
    @SuppressWarnings("unchecked")
    List<T> searchByQL(String qlString, Parameter parameter);

    /**
     * 通过QL语句查找属性
     *
     * @param qlString  查询语句
     * @param parameter 查询参数
     * @return 属性列表
     */
    @SuppressWarnings("unchecked")
    List getAttrsByQL(String qlString, Parameter parameter);

    /**
     * 通过QL语句查找
     *
     * @param qlString  查询语句
     * @param parameter 查询参数
     * @param page      分页对象
     * @return 分页对象
     */
    @SuppressWarnings("unchecked")
    Page<T> searchByQL(String qlString, Parameter parameter, Page<T> page);

    /**
     * 通过QL语句查找，返回迭代器
     *
     * @param qlString  查询语句
     * @param parameter 查询参数
     * @return 迭代器
     */
    @SuppressWarnings("unchecked")
    Iterator<T> searchByQLIterator(String qlString, Parameter parameter);
}
