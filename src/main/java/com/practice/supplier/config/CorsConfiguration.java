package com.practice.supplier.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

    @Value("${upload.picture.path}")
    private  String uploadPicturePath;

    @Value("/${server.version}/img/**")
    private  String imgPath;

    @Value("/${server.version}/excel/**")
    private  String excelPath;


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //设置允许跨域的路径
        registry.addMapping("/**")
                //设置允许跨域请求的域名
                .allowedOrigins("*")
                //是否允许证书 不再默认开启
                .allowCredentials(true)
                //设置允许的方法
                .allowedMethods("*")
                //跨域允许时间
                .maxAge(3600);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(excelPath).addResourceLocations("file:/usr/local/course-accreditation/upload/excel/");
        registry.addResourceHandler(imgPath).addResourceLocations("file:"+uploadPicturePath);
    }
}