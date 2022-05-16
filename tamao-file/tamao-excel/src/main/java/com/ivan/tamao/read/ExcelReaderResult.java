package com.ivan.tamao.read;

import com.alibaba.excel.util.StringUtils;
import lombok.Data;

/**
 * <p></p>
 *
 * @author ivan
 * @since 2022/5/15 22:48
 */
@Data
public class ExcelReaderResult<T> {

    private T data;

    private String errorMessage;

    private Boolean isError;

    public ExcelReaderResult(T data, String errorMessage) {
        this.data = data;
        this.errorMessage = errorMessage;
        this.isError = StringUtils.isBlank(errorMessage);
    }
}
