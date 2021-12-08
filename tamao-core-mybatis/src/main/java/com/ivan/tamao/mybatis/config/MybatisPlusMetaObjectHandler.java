package com.ivan.tamao.mybatis.config;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.nio.charset.Charset;
import java.time.LocalDateTime;

/**
 * <p></p>
 *
 * @author ivan
 * @className MybatisPlusMetaObjectHandler
 * @since 2021/12/5 22:00
 */
@Slf4j
public class MybatisPlusMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        LocalDateTime now = LocalDateTime.now();

        fillValIfNullByName("createId", 1L, metaObject, false);
        fillValIfNullByName("createName", "admin", metaObject, false);
        fillValIfNullByName("createTime", now, metaObject, false);
        fillValIfNullByName("updateId", 1L, metaObject, false);
        fillValIfNullByName("updateName", "admin", metaObject, false);
        fillValIfNullByName("updateTime", now, metaObject, false);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        fillValIfNullByName("updateId", 1L, metaObject, true);
        fillValIfNullByName("updateName", "admin", metaObject, true);
        fillValIfNullByName("updateTime", LocalDateTime.now(), metaObject, true);
    }


    /**
     * 填充值，先判断是否有手动设置，优先手动设置的值。
     * @param fieldName 属性名
     * @param fieldVal 属性值
     * @param metaObject MetaObject
     * @param isCover 是否覆盖原有值,避免更新操作手动入参
     */
    private static void fillValIfNullByName(String fieldName, Object fieldVal, MetaObject metaObject, boolean isCover) {
        // 1. 没有 get 方法
        if (!metaObject.hasGetter(fieldName) || !metaObject.hasSetter(fieldName)) {
            return;
        }
        // 2. 如果用户有手动设置的值
        Object userSetValue = metaObject.getValue(fieldName);
        String setValueStr = StrUtil.str(userSetValue, Charset.defaultCharset());
        if (StrUtil.isNotBlank(setValueStr) && !isCover) {
            return;
        }
        // 3. field 类型相同时设置
        Class<?> getterType = metaObject.getGetterType(fieldName);
        if (ClassUtils.isAssignableValue(getterType, fieldVal)) {
            metaObject.setValue(fieldName, fieldVal);
        }
    }
}
