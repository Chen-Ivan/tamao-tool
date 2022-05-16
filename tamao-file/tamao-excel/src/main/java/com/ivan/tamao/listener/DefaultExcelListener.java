package com.ivan.tamao.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.ivan.tamao.exception.BizException;
import com.ivan.tamao.read.ExcelReaderResult;
import com.ivan.tamao.validator.ExcelValidator;
import com.ivan.tamo.api.ResultCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.compress.utils.Lists;

import java.util.List;

/**
 * <p></p>
 *
 * @author ivan
 * @className CommonExcelListener
 * @since 2022/5/11 22:49
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DefaultExcelListener<T> extends AnalysisEventListener<T>  {

    /**
     * excel总条数阈值
     */
    private static final Integer MAX_SIZE = 10000;

    private ExcelValidator<T> excelValidator;
    private List<ExcelReaderResult<T>> excelReaderResultList = Lists.newArrayList();

    private List<T> successList = Lists.newArrayList();

    public DefaultExcelListener() {
    }

    public DefaultExcelListener(ExcelValidator<T> excelValidator) {
        this.excelValidator = excelValidator;
    }

    @Override
    public void invoke(T data, AnalysisContext context) {
        if (excelReaderResultList.size() > MAX_SIZE) {
            throw new BizException(ResultCode.FAILURE);
        }
        String errorMessage = excelValidator.validate(data);
        ExcelReaderResult<T> excelReaderResult = new ExcelReaderResult<T>(data, errorMessage);
        excelReaderResultList.add(excelReaderResult);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        excelValidator.validate(excelReaderResultList);
    }


}
