package com.CoupleCompass.config;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SiteMeshConfig {

    @Bean
    public FilterRegistrationBean<ConfigurableSiteMeshFilter> siteMeshFilter() {
        FilterRegistrationBean<ConfigurableSiteMeshFilter> filter = new FilterRegistrationBean<>();
        filter.setFilter(new ConfigurableSiteMeshFilter() {
            @Override
            protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
                builder.addDecoratorPath("/*", "/WEB-INF/decorators/default.jsp");
                builder.addDecoratorPath("/login", "/WEB-INF/decorators/login_default.jsp");
                builder.addDecoratorPath("/signup", "/WEB-INF/decorators/login_default.jsp");
            }
        });
        filter.addUrlPatterns("/*");
        return filter;
    }
}
