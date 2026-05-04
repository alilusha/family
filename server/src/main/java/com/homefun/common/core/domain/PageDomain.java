package com.homefun.common.core.domain;

import java.io.Serializable;
import java.util.List;

public class PageDomain implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long pageNum;
    private Long pageSize;
    private String orderByColumn;
    private String isAsc = "asc";
    private String reasonable = "false";

    public String getOrderBy() {
        if (orderByColumn == null || orderByColumn.isEmpty()) {
            return "";
        }
        return orderByColumn + " " + isAsc;
    }

    public PageDomain() {
    }

    public PageDomain(Long pageNum, Long pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public Long getPageNum() {
        return pageNum;
    }

    public void setPageNum(Long pageNum) {
        this.pageNum = pageNum;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderByColumn() {
        return orderByColumn;
    }

    public void setOrderByColumn(String orderByColumn) {
        this.orderByColumn = orderByColumn;
    }

    public String getIsAsc() {
        return isAsc;
    }

    public void setIsAsc(String isAsc) {
        if (isAsc != null && !isAsc.isEmpty()) {
            if ("ascending".equals(isAsc)) {
                this.isAsc = "asc";
            } else if ("descending".equals(isAsc)) {
                this.isAsc = "desc";
            } else {
                this.isAsc = isAsc;
            }
        }
    }

    public String getReasonable() {
        return reasonable;
    }

    public void setReasonable(String reasonable) {
        this.reasonable = reasonable;
    }
}
