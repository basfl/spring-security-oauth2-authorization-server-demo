package com.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class TransactionFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		 HttpServletRequest req = (HttpServletRequest) request;
	        System.out.println(
	          "Starting a transaction for req : {}"+ 
	          req.getRequestURI());
	 
	        chain.doFilter(request, response);
	        System.out.println(
	          "Committing a transaction for req : {}"+
	          req.getRequestURI());
		
	}



	
    // other methods 
}
