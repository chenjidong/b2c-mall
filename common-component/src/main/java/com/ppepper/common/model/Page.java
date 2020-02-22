package com.ppepper.common.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Created with ChenJiDong
 * Created By 2020-02-05
 */
@Data
@NoArgsConstructor
public class Page<T> implements Serializable {
    private List<T> items;

    private int pageNo;

    private int pageSize;

    private long count;

    public Page(List<T> items, int pageNo, int pageSize, long count) {
        this.items = items;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.count = count;
    }

    public long getTotalPageNo() {
        if (count <= 0)
            return 0;
        return count / pageSize + (count % pageSize == 0 ? 0 : 1);
    }

    public List<T> getItems() {
        return items;
    }

    public boolean hasNext() {
        return getPageNo() < getTotalPageNo();
    }

    public boolean hasPrevious() {
        return getPageNo() > 1;
    }

    public String getMsg() {
        return "第" + pageNo + "页,共" + count + "条";
    }

    public int getCode() {
        return 0;
    }

    public long getCount() {
        return this.count;
    }

    public long getTotal() {
        return this.count;
    }
}
