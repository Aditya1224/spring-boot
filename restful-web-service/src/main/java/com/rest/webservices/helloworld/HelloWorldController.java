package com.rest.webservices.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

//Controller
@RestController
public class HelloWorldController {

	@Autowired
	private MessageSource messageSource;

	// GET
	// URI - /hello-world
	// Method - "Hello World!"
//	@RequestMapping(method = RequestMethod.GET, path = "/hello-world")
	@GetMapping(path = "/hello-world") // Not required method name
	public String helloWorld() {
		return "Hello World!";
	}

	// Q: How does the hello world bean get converted to JSON?
	// Ans: Spring Boot auto configuration because the http message convertors and
	// the Jackson beans are getting initialized.
	@GetMapping(path = "/hello-world-bean") // Not required method name
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World Bean!");
	}

	// http://localhost:8080/hello-world/path-variable/test-path-variable
	@GetMapping(path = "/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello WOrld Path-Variable %s", name));
	}

	/*
	 * {@RequestHeader(name = "Accept-Language")}The request header which would be
	 * containing this is Accept-Language. So, based on the Accept-Language header,
	 * you decide the locale.
	 */
//	@GetMapping(path = "/hello-world-internationalized")
//	public String helloWorldInternationalized(@RequestHeader(name = "Accept-Language", required = false) String localeString) {
//		Locale locale = new Locale(localeString);
//		System.out.println("internationalized*********************");
//		return messageSource.getMessage("good.morning.messages", null, locale);
//	}
	@GetMapping(path = "/hello-world-internationalized")
	public String helloWorldInternationalized() {
		System.out.println("internationalized*********************");
		return messageSource.getMessage("good.morning.messages", null, LocaleContextHolder.getLocale());
	}

}
