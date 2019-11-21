package com.in28minutes.microservices.currencyconversionservice;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//this below annotation takes two argument name=name of external service and url=url of service
//@FeignClient(name = "currency-exchange-service", url = "localhost:8000")
//@FeignClient(name="currency-exchange-service")

//telling this api to go through the zull api gateway service
@FeignClient(name = "netflix-zuul-api-gateway-server")

@RibbonClient(name = "currency-exchange-service")
public interface CurrencyExchangeServiceProxy {

	// @GetMapping("/currency-exchange/from/{from}/to/{to}")

	// telling the api to go through zull api gateway and thus it is need to specigy
	// name of the appication to which the apis is associated with

	// this api will invoked in the getMapping when we call retrieveExchangeValue()
	@GetMapping("/currency-exchange-service/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversionBean retrieveExchangeValue(@PathVariable("from") String from,
			@PathVariable("to") String to); // whenever we are using feign it is necessary to specify the exact path
											// variables thst why we write @PathVariable("from")
}
