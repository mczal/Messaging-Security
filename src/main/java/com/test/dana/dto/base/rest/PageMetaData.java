package com.test.dana.dto.base.rest;

import org.springframework.data.domain.Page;

/**
 * Created by mczal on 8/10/17.
 */
public class PageMetaData {

  /**
   * Current page number
   */
  private int pageNumber;

  /**
   * Number of elements in a single page
   */
  private int pageSize;

  /**
   * Total pages
   */
  private int totalPages;

  /**
   * Total all elements from all pages
   */
  private long totalRecords;

  public PageMetaData(int pageSize, int pageNumber, long totalRecords, int totalPages) {
    this.pageSize = pageSize;
    this.pageNumber = pageNumber;
    this.totalRecords = totalRecords;
    this.totalPages = totalPages;
  }

  public PageMetaData(Page page) {
    this.pageSize = page.getSize();
    this.pageNumber = page.getNumber();
    this.totalRecords = page.getTotalElements();
    this.totalPages = page.getTotalPages();
  }

  public int getPageNumber() {
    return pageNumber;
  }

  public void setPageNumber(int pageNumber) {
    this.pageNumber = pageNumber;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public int getTotalPages() {
    return totalPages;
  }

  public void setTotalPages(int totalPages) {
    this.totalPages = totalPages;
  }

  public long getTotalRecords() {
    return totalRecords;
  }

  public void setTotalRecords(long totalRecords) {
    this.totalRecords = totalRecords;
  }

}
