package com.test.dana.dto.base.rest;

import com.test.dana.dto.base.BaseResponse;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by mczal on 8/10/17.
 */
public class RestListResponse<T extends BaseResponse> extends BaseRestResponse {

  private List<T> content;

  private PageMetaData pageMetaData;

  @Deprecated
  public RestListResponse(String requestId, HttpCode httpCode, String message, List<T> content,
      PageMetaData pageMetaData) {
    super(requestId, httpCode, message);
    this.content = content;
    this.pageMetaData = pageMetaData;
  }

  @Deprecated
  public RestListResponse(String requestId, HttpCode httpCode, List<T> content,
      PageMetaData pageMetaData) {
    super(requestId, httpCode);
    this.content = content;
    this.pageMetaData = pageMetaData;
  }

  public RestListResponse(String requestId, HttpCode httpCode, Page<T> page) {
    super(requestId, httpCode);
    this.content = page.getContent();
    this.pageMetaData = new PageMetaData(page);
  }

  public List<T> getContent() {
    return content;
  }

  public void setContent(List<T> content) {
    this.content = content;
  }

  public PageMetaData getPageMetaData() {
    return pageMetaData;
  }

  public void setPageMetaData(PageMetaData pageMetaData) {
    this.pageMetaData = pageMetaData;
  }
}
