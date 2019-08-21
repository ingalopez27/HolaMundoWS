package mx.com.naturgy.vass.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import mx.com.naturgy.vass.bean.Name;
import mx.com.naturgy.vass.bean.Student;
import mx.com.naturgy.vass.filtering.NameObject;
import mx.com.naturgy.vass.filtering.SomeBean;

@RestController
public class FilteringController {

	@RequestMapping(method = RequestMethod.POST, path = "/v3")
	public SomeBean retriveBean() {
		return new SomeBean("value1", "value2", "value3");
	}

	@GetMapping(value = "/v2/name", params = "version=1")
	public String name() {
		Name name = new Name("Alan", "Lopez");
		return name.toString();
	}

	@PostMapping(value = "/v3/name", headers = "X-API-VERSION=1")
	public Name name2() {
		return new Name("Alan", "Lopez");
	}

	@PostMapping(value = "/v3/name/produce", produces = "application/json")
	public String name3() {
		Name name = new Name("Alan", "Lopez");
		return name.toString();
	}

	@PostMapping(value = "/v3/name/{id}", produces = "application/json")
	public NameObject salida(@PathVariable("id") int id) {
		return new NameObject(new Name("Alan", "Lopez"));
	}
}


