package com.rest.webservices.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FiltringController {

//	@GetMapping("/filtring")
//	public SomeBean retrieveSomeBean() {
//		return new SomeBean("value1", "value2", "value3");
//	}
//	
//	@GetMapping("/filtring-list")
//	public List<SomeBean> retrieveListOfSomeBean() {
//		return Arrays.asList(new SomeBean("value1", "value2", "value3"),
//				new SomeBean("value4", "value5", "value6"));
//	}

	// Filter field1, field2
	@GetMapping("/filtring")
	public MappingJacksonValue retrieveSomeBean() {
		SomeBean someBean = new SomeBean("value1", "value2", "value3");

		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");
		FilterProvider filterProvider = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);

		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);
		mappingJacksonValue.setFilters(filterProvider);
		return mappingJacksonValue;
	}

	// field2 ,field3
	@GetMapping("/filtring-list")
	public MappingJacksonValue retrieveListOfSomeBean() {
		List<SomeBean> someBeans = Arrays.asList(new SomeBean("value1", "value2", "value3"), 
				new SomeBean("value4", "value5", "value6"));
				SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");
		FilterProvider filterProvider = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);

		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBeans);
		mappingJacksonValue.setFilters(filterProvider);
		return mappingJacksonValue;
		
	}
}
