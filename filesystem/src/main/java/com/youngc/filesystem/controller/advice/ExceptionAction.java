package com.youngc.filesystem.controller.advice;

import com.youngc.filesystem.common.ServiceException;
import com.youngc.filesystem.controller.IBaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一异常处理
 */
@Slf4j
@RestControllerAdvice
public class ExceptionAction implements IBaseController {

  @ExceptionHandler({Exception.class})
  public ResponseEntity exception(Exception e) {
    if (e instanceof ServiceException) {
      String errorMsg = ( (ServiceException) e ).getMsg();
      log.error(errorMsg, e);
      return ResponseEntity.ok(errorJson((errorMsg)));
    } else {
      log.error("系统异常！", e);
      return ResponseEntity.ok(errorJson());
    }
  }

}
