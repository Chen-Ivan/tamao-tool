package com.ivan.tamao.mybatis.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * <p>基本字段</p>
 *
 * @author ivan
 * @className BaseEntity
 * @since 2021/12/5 21:55
 */
@Data
@EqualsAndHashCode
public class BaseEntity {

    /**
     * 创建者Id
     */
    @TableField(fill = FieldFill.INSERT)
    private String createId;

    /**
     * 创建者
     */
    @TableField(fill = FieldFill.INSERT)
    private String createName;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新者ID
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateId;

    /**
     * 更新者名称
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateName;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

}
