package edu.isep.genielogiciel.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.beans.PropertyVetoException;

@EnableWebMvc
@EnableAutoConfiguration
@Configuration
@PropertySource(value={"classpath:projet-gl.properties"}, ignoreResourceNotFound = true)
@EnableTransactionManagement
@EnableJpaRepositories("edu.isep.genielogiciel.*")
@ComponentScan(basePackages = { "edu.isep.genielogiciel.*" })
@EntityScan("edu.isep.genielogiciel.*")
public class MVCConfig implements WebMvcConfigurer {

    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/public/**").addResourceLocations("/public/");
    }

}
