package com.ivan.tamao.exception;

import com.ivan.tamao.exception.BizException;
import com.ivan.tamo.api.IResultCode;

/**
 * <p>校验异常</p>
 *
 * @author ivan
 * @className ValidatorException
 * @since 2022/5/12 22:37
 */
public class ValidatorException extends BizException {
    public ValidatorException(String errorMessage, Integer errorCode) {
        super(errorMessage, errorCode);
    }

    public ValidatorException(IResultCode resultCode) {
        super(resultCode);
    }
}
