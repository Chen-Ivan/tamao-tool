package com.ivan.tamao.support;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p></p>
 *
 * @author ivan
 * @className SwaggerProperties
 * @since 2021/12/13 22:09
 */
@Data
@ConfigurationProperties("swagger")
public class SwaggerProperties {
    /**
     * 是否开启swagger
     */
    private Boolean enable = false;

    /**
     * 标题
     **/
    private String title = "";

    /**
     * 描述
     **/
    private String description = "";

    /**
     * 版本
     **/
    private String version = "";

    /**
     * host信息
     **/
    private String host = "";

    /**
     * 联系人信息
     */
    private Contact contact = new Contact();

    @Data
    @NoArgsConstructor
    public static class Contact {

        /**
         * 联系人
         **/
        private String name = "";

        /**
         * 联系人url
         **/
        private String url = "";

        /**
         * 联系人email
         **/
        private String email = "";

    }

}
