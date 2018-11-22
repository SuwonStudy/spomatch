package com.spomatch;

import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.google.common.base.Predicate;

import springfox.documentation.RequestHandler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger 2 is enabled through the @EnableSwagger2 annotation.
 * 
 * This configuration is enough to integrate Swagger 2 into an existing Spring Boot project. 
 * 
 * For other Spring projects, some additional tuning is required.
 */
@EnableSwagger2
@SpringBootApplication
public class SpoMatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpoMatchApplication.class, args);
	}

	/**
	 * Docket: A builder which is intended to be the primary interface into the
	 * Springfox framework.
	 * 
	 * Provides sensible defaults and convenience methods for configuration.
	 */
	@Bean
	public Docket api() {

		DocumentationType documentationType = DocumentationType.SWAGGER_2;

		Docket d = new Docket(documentationType);

		Predicate<RequestHandler> requestHadlerSelector = RequestHandlerSelectors.any();

		Predicate<String> pathSelector = PathSelectors.any();

		/**
		 * select() method returns an instance of ApiSelectorBuilder, 
		 * which provides a way to control the endpoints exposed by Swagger.
		 */
		return d.select()
				.apis(requestHadlerSelector)
				.paths(pathSelector)
				.build()
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {

		String applicationName = "스포매치";
		String applicationUrl = "https://spomatch.com";
		
		String title = applicationName;
		String description = "스포츠 랭킹 앤 매칭";
		String version = "1.0.0";
		String termsOfServiceUrl = applicationUrl + "/terms";
		Contact contact = new Contact(applicationName, applicationUrl, "service@spomatch.com");
		String license = "Apache License 2.0";
		String licenseUrl = "https://www.apache.org/licenses/LICENSE-2.0";

		ApiInfo apiInfo = new ApiInfo(
			title, 
			description, 
			version, 
			termsOfServiceUrl, 
			contact, 
			license, 
			licenseUrl, 
			new ArrayList<>()
		);
		
		return apiInfo;
	}
	
}
