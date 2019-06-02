package com.spring.demo.model.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collections;

@Getter
@Setter
@ToString
@SuppressWarnings("unchecked")
public class ResultData<T> implements Serializable {

  private Integer code;
  private T data;
  private String msg;

  public ResultData(Integer code, String msg, T data) {
    this.code = code;
    this.msg = msg;
    this.data = data;
  }

  public ResultData(Integer code, String msg) {
    this.code = code;
    this.msg = msg;
    this.data = (T)Collections.emptyMap();
  }

  public ResultData(Msg msg) {
    this.code = msg.getCode();
    this.msg = msg.getMsg();
    this.data = (T)Collections.emptyMap();
  }

  public ResultData(@NotNull T data) {
    this.code = Msg.SUCCESS.getCode();
    this.msg = Msg.SUCCESS.getMsg();
    this.data = data;
  }


}
