package com.example.config;

import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

@Order(1)
public class WebAppSecurityInitializer extends AbstractSecurityWebApplicationInitializer {
}