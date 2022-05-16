package com.ivan.tamao.manager;

import com.ivan.tamao.builder.ExcelReaderBuilder;
import com.ivan.tamao.validator.ExcelValidator;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;

/**
 * <p></p>
 *
 * @author ivan
 * @since 2022/5/14 23:42
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper = false)
public class ExcelManager {

    public static void read(InputStream inputStream, Class<?> head, ExcelValidator<?> excelValidator) {
        ExcelReaderBuilder.builder()
                .file(inputStream)
                .head(head)
                .validator(excelValidator)
                .doRead();
    }

}
