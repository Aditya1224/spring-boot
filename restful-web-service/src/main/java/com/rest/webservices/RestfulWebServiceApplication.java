package com.rest.webservices;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import com.google.common.base.Predicate;

import springfox.documentation.RequestHandler;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class RestfulWebServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulWebServiceApplication.class, args);
	}

	/*
	 * {SessionLocaleResolver} The SessionLocaleResolver allows you to retrieve
	 * locales from the session that might be associated with the user's request.
	 * {AcceptHeaderLocaleResolver} This locale resolver inspects the
	 * accept-language header in the request that was sent by the browser of the
	 * client. Usually this header field contains the locale of the client's
	 * operating system.
	 */
	@Bean
	public LocaleResolver localeResolver() {
//		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}

	//Set {spring.messages.basename=messages} in application.properties and remove below code
//	@Bean//Be careful about the name of method  - should be messagSource
//	public ResourceBundleMessageSource messagSource() {
//		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//		messageSource.setBasename("messages");
//		return messageSource;
//	}
	
	@Bean
	   public Docket productApi() {
	      return new Docket(DocumentationType.SWAGGER_2).select()
	         .apis((Predicate<RequestHandler>) RequestHandlerSelectors.basePackage("com.rest.webservices")).build();
	   }

}
