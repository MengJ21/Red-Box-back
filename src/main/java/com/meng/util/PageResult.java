package com.meng.util;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
@EqualsAndHashCode(callSuper = true)
@Data
public class PageResult<T> extends Result{
    /**
     * 总记录数
     */
    private long total;
    /**
     * 分页的数据
     */
    private List<T> rows;
    public PageResult(long total, List<T> list){
        this.setFlag(true);
        this.setMessage("分页查询成功");
        this.total=total;
        this.rows=list;
    }
}
