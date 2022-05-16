package com.ivan.tamao.validator;

import com.ivan.tamao.read.ExcelReaderResult;

import java.util.List;

/**
 * <p></p>
 *
 * @author ivan
 * @since 2022/5/15 23:14
 */

public interface ExcelValidator<T> {

    String validate(T data);

    List<ExcelReaderResult<T>> validate(List<ExcelReaderResult<T>> excelReaderResultList);

}
