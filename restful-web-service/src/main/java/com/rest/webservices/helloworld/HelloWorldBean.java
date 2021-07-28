package com.rest.webservices.helloworld;

public class HelloWorldBean{

	private String greeting;
	
	public HelloWorldBean(String greeting) {
		this.greeting = greeting;
	}
	
	//if we don't create a Getter, then the automatic conversion will not work.
	public String getGreeting() {
		return greeting;
	}

	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}

	@Override
	public String toString() {
		return "HelloWorldBean [greeting=" + greeting + "]";
	}
	
}
