package com.test.dana.dto.message.request;

import com.test.dana.dto.base.BaseRequest;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mczal on 8/12/17.
 */
public class ReadMessageDtoRequest extends BaseRequest {

  @NotNull
  @Size(min = 1)
  private List<String> ids = new ArrayList();

  public List<String> getIds() {
    return ids;
  }

  public void setIds(List<String> ids) {
    this.ids = ids;
  }

}
