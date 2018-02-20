package com.vitz.springbootcameldemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.apache.camel.component.servlet.CamelHttpTransportServlet;

@SpringBootApplication
public class SpringBootCamelDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCamelDemoApplication.class, args);
	}

}
