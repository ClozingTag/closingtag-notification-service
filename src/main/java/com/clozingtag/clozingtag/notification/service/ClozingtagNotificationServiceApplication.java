package com.clozingtag.clozingtag.notification.service;

import com.clozingtag.clozingtag.notification.service.configuration.AppConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@EnableConfigurationProperties(value = AppConfiguration.class)
@RefreshScope
public class ClozingtagNotificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClozingtagNotificationServiceApplication.class, args);
	}

}
