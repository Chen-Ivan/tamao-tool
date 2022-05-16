package com.ivan.tamao.read;

import com.alibaba.excel.EasyExcel;

/**
 * <p>excel read</p>
 *
 * @author ivan
 * @since 2022/5/15 11:31
 */
public class ExcelReader {

    /**
     * 读取excel
     *
     * @param excelReadMetaData excel元数据
     */
    public void read(ExcelReadMetaData excelReadMetaData) {
        EasyExcel.read(excelReadMetaData.getInputStream(), excelReadMetaData.getHead(), excelReadMetaData.getExcelListener())
                .sheet()
                .doRead();
    }
}
