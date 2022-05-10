package com.ivan.tamao.config;

import cn.hutool.core.collection.CollectionUtil;
import com.ivan.tamao.support.SwaggerProperties;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * <p>swagger配置类</p>
 *
 * @author ivan
 * @className SwaggerConfiguration
 * @since 2021/12/8 22:52
 */
@EnableOpenApi
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(name = "swagger.enable", havingValue = "true")
@EnableConfigurationProperties(SwaggerProperties.class)
public class SwaggerConfiguration {

    private final SwaggerProperties swaggerProperties;


    public SwaggerConfiguration(SwaggerProperties swaggerProperties) {
        this.swaggerProperties = swaggerProperties;
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .pathMapping("/")
                // 是否开启swagger
                .enable(swaggerProperties.getEnable())
                // api元信息
                .apiInfo(apiInfo())
                // 接口调试地址
                .host(swaggerProperties.getHost())
                // 选择的接口
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                // 通讯协议
                .protocols(CollectionUtil.newHashSet("http", "https"));
    }

    /**
     * API 页面上半部分展示信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(swaggerProperties.getTitle() + " Api Doc")
                .description(swaggerProperties.getDescription())
                .contact(new Contact(swaggerProperties.getContact().getName(), swaggerProperties.getContact().getUrl(), swaggerProperties.getContact().getEmail()))
                .version("Application Version: " + swaggerProperties.getVersion() + ", Spring Boot Version: " + SpringBootVersion.getVersion())
                .build();
    }
}
