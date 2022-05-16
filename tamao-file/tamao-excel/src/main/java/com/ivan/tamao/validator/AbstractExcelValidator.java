package com.ivan.tamao.validator;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.BooleanUtil;
import com.ivan.tamao.read.ExcelReaderResult;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>excel 基础校验</p>
 *
 * @author ivan
 * @className ExcelBaseValidator
 * @since 2022/5/12 22:07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Slf4j
public abstract class AbstractExcelValidator<T> implements ExcelValidator<T> {

    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    /**
     * 字段校验
     * @param data
     * @return
     */
    @Override
    public String validate(T data) {
        StringBuilder errorMessageBuilder = new StringBuilder();

        Set<ConstraintViolation<T>> violationSet = validator.validate(data, Default.class);

        if (CollectionUtil.isNotEmpty(violationSet)) {
            errorMessageBuilder.append(violationSet.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(";")));
        }

        log.info(errorMessageBuilder.toString());
        return errorMessageBuilder.toString();
    }

    @Override
    public List<ExcelReaderResult<T>> validate(List<ExcelReaderResult<T>> excelReaderResultList) {
        List<T> successList = excelReaderResultList.stream()
                .filter(item -> BooleanUtil.isFalse(item.getIsError()))
                .map(ExcelReaderResult::getData)
                .collect(Collectors.toList());

        return bizValidate(successList);
    }

    protected abstract List<ExcelReaderResult<T>> bizValidate(List<T> successList);
}
