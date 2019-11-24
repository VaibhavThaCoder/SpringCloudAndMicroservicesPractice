package com.in28minutes.microservices.limitsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.microservices.limitsservice.bean.LimitConfiguration;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class LimitsConfigurationController {

	@Autowired
	private Configuration configuration;

	@GetMapping("/limits")
	public LimitConfiguration retrieveLimitsFromConfigurations() {

		// extracting the limits values from Configuration class
		LimitConfiguration limitConfiguration = new LimitConfiguration(configuration.getMaximum(),
				configuration.getMinimum());

		// hardcoding the max and min values
//		LimitConfiguration limitConfiguration = new LimitConfiguration(1000, 1);
		return limitConfiguration;
	}

	@GetMapping("/fault-tolerance-example")
	@HystrixCommand(fallbackMethod = "fallbackRetrieveConfiguration")
	// here we have given default limit configuration for the limits service so that
	// if limits service is not available other services dependent on this limits
	// service will take this configuration and will continue the work
	
	public LimitConfiguration retrieveConfiguration() { // if the fallback method is also not present the runtime
														// exception is thrown saying not available with status code 500
		throw new RuntimeException("Not available");
	}

	public LimitConfiguration fallbackRetrieveConfiguration() { // this method name is specified in fallbackMethod
																// parameter above in hystrix command
		return new LimitConfiguration(999, 9);
	}

}
