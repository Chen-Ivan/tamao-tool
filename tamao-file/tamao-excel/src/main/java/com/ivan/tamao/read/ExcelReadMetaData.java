package com.ivan.tamao.read;

import com.alibaba.excel.read.listener.ReadListener;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.InputStream;

/**
 * <p>读取元数据</p>
 *
 * @author ivan
 * @since 2022/5/15 12:11
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ExcelReadMetaData {

    /**
     * input stream
     */
    private InputStream inputStream;

    /**
     * 表头行数
     */
    private int headRow = 1;

    /**
     * 表头所对应的对象
     */
    private Class<?> head;

    /**
     * 监听器
     */
    private ReadListener<?> excelListener;

}
