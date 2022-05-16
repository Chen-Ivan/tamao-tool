package com.ivan.tamao.builder;

import com.ivan.tamao.listener.DefaultExcelListener;
import com.ivan.tamao.read.ExcelReadMetaData;
import com.ivan.tamao.read.ExcelReader;
import com.ivan.tamao.validator.ExcelValidator;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;

/**
 * <p></p>
 *
 * @author ivan
 * @since 2022/5/15 11:33
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Slf4j
public class ExcelReaderBuilder {


    private ExcelReadMetaData excelReadMetaData;

    private DefaultExcelListener excelListener;

    public ExcelReaderBuilder() {
        this.excelReadMetaData = new ExcelReadMetaData();
        this.excelListener = new DefaultExcelListener();
    }

    public static ExcelReader build() {
        return new ExcelReader();
    }

    public static ExcelReaderBuilder builder() {
        return new ExcelReaderBuilder();
    }

    /**
     * build inputStream.
     * @param inputStream Input stream to read.
     * @return excel read build
     */
    public ExcelReaderBuilder file(InputStream inputStream) {
        excelReadMetaData.setInputStream(inputStream);
        return this;
    }

    /**
     * build head row.
     * @param row head row.
     * @return excel read build
     */
    public ExcelReaderBuilder headRow(int row) {
        excelReadMetaData.setHeadRow(row);
        return this;
    }

    /**
     * build head
     * @param head Annotate the class.
     * @return excel read build
     */
    public ExcelReaderBuilder head(Class<?> head) {
        excelReadMetaData.setHead(head);
        return this;
    }


    public ExcelReaderBuilder validator(ExcelValidator<?> excelValidator) {
        excelListener.setExcelValidator(excelValidator);
        excelReadMetaData.setExcelListener(excelListener);
        return this;
    }


    public void doRead() {
        ExcelReader excelReader = build();
        excelReader.read(excelReadMetaData);
    }

}
