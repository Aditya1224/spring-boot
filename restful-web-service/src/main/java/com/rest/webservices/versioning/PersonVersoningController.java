package com.rest.webservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersoningController {

	/* 1.
	 * URI Versioning
	 * URI versioning is the most straightforward approach.
	 *  It specified in the URL as a query string. 
	 *  It violates the principle that a URI should refer to a unique resource.
	 *  You are also guaranteed to break client integration when a version is updated.
	 *  Twitter uses URI versioning.
	 *  http://api.demo.com/v1
	 *  http://apiv1.demo.com
	 */
	@GetMapping("/v1/person")
	public PersonV1 personV1() {
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping("/v2/person")
	public PersonV2 personV2() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}
	
	/*
	 *2. Versioning RESTful Services - Header and Content Negotiation Approach, 
	 * 
	 */
	
	@GetMapping(value = "/person/param", params = "version=1")
	public PersonV1 paramV1() {
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping(value = "/person/param", params = "version=2")
	public PersonV2 paramV2() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}
	
	//3. Versioning using Accept Header
	
	@GetMapping(value = "/person/header", headers = "X-API-VERSION=1")
	public PersonV1 headerV1() {
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping(value = "/person/header", headers = "X-API-VERSION=2")
	public PersonV2 headerV2() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}
	
	@GetMapping(value = "/person/produces", produces="application/vnd.company.app-v1+json")
	public PersonV1 producerV1() {
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping(value = "/person/produces", produces="application/vnd.company.app-v2+json")
	public PersonV2 producerV2() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}
}

