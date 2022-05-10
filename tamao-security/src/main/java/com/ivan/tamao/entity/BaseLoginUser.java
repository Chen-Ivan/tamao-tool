package com.ivan.tamao.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>登录用户</p>
 *
 * @author ivan
 * @className LoginUser
 * @since 2021/12/19 14:37
 */
@Data
@EqualsAndHashCode
public class BaseLoginUser {

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

}
