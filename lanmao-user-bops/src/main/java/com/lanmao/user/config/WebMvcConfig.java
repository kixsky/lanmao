package com.lanmao.user.config;

import com.lanmao.user.auth.AuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor()).addPathPatterns("/**");
    }

    @Bean
    public AuthInterceptor loginInterceptor() {
        return new AuthInterceptor();
    }

    @Override
    protected RequestMappingHandlerMapping createRequestMappingHandlerMapping() {
        return new RequestMappingHandlerMapping() {

            @Override
            protected boolean isHandler(Class<?> beanType) {
                return AnnotatedElementUtils.hasAnnotation(beanType, Controller.class)
                        || AnnotatedElementUtils.hasAnnotation(beanType, RestController.class);
            }
        };
    }
}
