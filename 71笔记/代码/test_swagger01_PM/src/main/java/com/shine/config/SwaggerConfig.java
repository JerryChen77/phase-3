package com.shine.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.StringVendorExtension;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket(){
        // 创建Docket对象
        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        // 设置docket，覆盖默认的信息
        docket
                .apiInfo(apiInfo())
                .groupName("test_shine");
        return docket;
    }

    public ApiInfo apiInfo() {
        /**
         * String title,
         * String description,
         * String version,
         * String termsOfServiceUrl,
         * Contact contact,
         * String license,
         * String licenseUrl,
         * Collection<VendorExtension> vendorExtensions
         */

        Contact contact = new Contact(
                "千锋互联",
                "http://www.1000phone.com/",
                "123321@1000phone.com"
        );
        List<VendorExtension> vendorExtensions = new ArrayList<>();
        vendorExtensions.add(new StringVendorExtension("公司地址","杭州上城区九堡镇旺田商务楼A座"));
        vendorExtensions.add(new StringVendorExtension("公司电话","10086"));
        vendorExtensions.add(new StringVendorExtension("公司业务","职业教育"));

        ApiInfo apiInfo = new ApiInfo(
                "千锋互联Java2103",
                "我们是光荣的Java开发者",
                "V3.0",
                "http://www.baidu.com/",
                contact,
                "京东验证中心",
                "http://www.jd.com/",
                vendorExtensions
        );
        return apiInfo;
    }
}
