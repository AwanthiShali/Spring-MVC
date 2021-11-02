package com.awa.config;
/*
 * @author Awanthi Shalika
 * @since 10/25/2021
 */

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(JPAConfig.class)
public class WebRootConfig {
}
