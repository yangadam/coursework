package cn.edu.xmu.comm.commons.persistence;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页对象
 *
 * @param <T> 实体类型参数
 * @author Mengmeng Yang
 * @version 12/25/2014
 */
public class Page<T> {

    /**
     * 是否分页
     */
    private boolean enable;

    /**
     * 当前页码
     */
    private int currentPage;

    /**
     * 每页大小
     */
    private int pageSize;

    /**
     * 记录数
     */
    private long count = -1;

    /**
     * 页数
     */
    private int pageCount = -1;

    /**
     * 实体列表
     */
    private List<T> list = new ArrayList<T>();

    /**
     * 构造函数（默认每页10条记录，当前第一页）
     *
     * @param enable 是否分页
     */
    public Page(boolean enable) {
        this.enable = enable;
        if (enable) {
            this.currentPage = 1;
            this.pageSize = 10;
        }
    }

    /**
     * 构造函数
     *
     * @param pageSize 每页大小
     */
    public Page(int pageSize) {
        this.enable = true;
        this.pageSize = pageSize;
        this.currentPage = 1;
    }

    /**
     * 构造函数
     *
     * @param pageSize    每页大小
     * @param currentPage 当前页码
     */
    public Page(int pageSize, int currentPage) {
        this.enable = true;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
    }

    /**
     * 下一页
     *
     * @return 分页对象
     */
    public Page<T> next() {
        if (currentPage < pageCount) {
            currentPage++;
        }
        return this;
    }

    /**
     * 上一页
     *
     * @return 分页对象
     */
    public Page<T> previous() {
        if (currentPage > 1) {
            currentPage--;
        }
        return this;
    }

    /**
     * 跳转页
     *
     * @param page 页码
     * @return 分页对象
     */
    public Page<T> goTo(int page) {
        if (0 < page && page <= pageCount) {
            currentPage = page;
        }
        return this;
    }

    /**
     * 跳转到第一页
     *
     * @return 分页对象
     */
    public Page<T> goToFirst() {
        currentPage = 1;
        return this;
    }

    /**
     * 跳转到最后一页
     *
     * @return 分页对象
     */
    public Page<T> goToLast() {
        if (pageCount > 0) {
            currentPage = pageCount;
        }
        return this;
    }

    /**
     * 起始记录号
     *
     * @return 起始
     */
    public int start() {
        return pageSize * (currentPage - 1) + 1;
    }

    public boolean isEnable() {
        return enable;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
        if (count > 0) {
            pageCount = (int) ((count - 1) / pageSize) + 1;
        }
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
        if (isEnable()) {
            count = list.size();
            if (count > 0) {
                pageCount = (int) ((count - 1) / pageSize) + 1;
            }
        }
    }

}
