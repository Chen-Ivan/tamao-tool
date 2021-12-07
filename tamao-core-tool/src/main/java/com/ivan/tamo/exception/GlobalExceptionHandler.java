package com.ivan.tamo.exception;

import com.ivan.tamo.api.IResultCode;
import com.ivan.tamo.api.R;
import com.ivan.tamo.api.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <p>全局异常处理</p>
 *
 * @author ivan
 * @className GlobalExceptionHandler
 * @since 2021/12/7 23:36
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 默认全局异常处理。
     * @param e the e
     * @return R
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public R<String> exception(Exception e) {
        log.error("全局异常信息 ex={}", e.getMessage(), e);
        return R.fail(ResultCode.FAILURE, e.getMessage());
    }

}
