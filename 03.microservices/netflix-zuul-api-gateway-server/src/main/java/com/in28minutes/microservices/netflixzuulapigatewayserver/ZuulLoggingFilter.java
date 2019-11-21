package com.in28minutes.microservices.netflixzuulapigatewayserver;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class ZuulLoggingFilter extends ZuulFilter {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean shouldFilter() {
		return true;  //should the filter be executed or not
		     	// so it is set to true
	}

	@Override
	public Object run() {  //here the actual logic of filtering is present
		HttpServletRequest request = RequestContext.getCurrentContext().getRequest(); //gives us the current http request
		logger.info("request -> {} request uri -> {}", request, request.getRequestURI()); //{} =>place holder
		return null;
	}

	@Override
	public String filterType() {
		return "pre";  //it specifies when the filtering should happen, before or after the request execution
		               //so it is set to pre i.e filtering will take place the execution of api request
	}

	@Override
	public int filterOrder() { // setting up the priority order for filerting the api requests
		return 1; // filter order/priority is 1
	}
}
