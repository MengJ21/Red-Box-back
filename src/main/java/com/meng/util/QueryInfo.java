package com.meng.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryInfo {
    /**
     * 第几页
     */
    private Integer pageNumber;
    /**
     * 一页多少条数据
     */
    private Integer pageSize;
    /**
     * 查询的内容
     */
    private String queryString;
}

