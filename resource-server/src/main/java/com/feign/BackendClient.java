package com.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "feign-resource-service", url = "${sample.client.url}", configuration = FeignConfig.class)

public interface BackendClient {

	@RequestMapping(method = RequestMethod.GET, value = "/all")
	List<Country> getAll();

	@RequestMapping(method = RequestMethod.GET, value = "/country/{name}")
	Country geCountry(@PathVariable String name);

	@RequestMapping(method = RequestMethod.POST, value = "/country")
	List<Country> create(@RequestBody Country newCountry);

}
