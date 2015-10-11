package com.vitalina.library.service;

public class Pagination {
    Integer pageSize;
    Integer pageCount;
    Integer currentPage;

    public Pagination() {
    }

    public Pagination(Integer pageSize, Integer currentPage) {
        this.pageSize = pageSize;

        if(currentPage == null) {
            this.currentPage = 0;
        } else {
            this.currentPage = currentPage;
        }
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

}
