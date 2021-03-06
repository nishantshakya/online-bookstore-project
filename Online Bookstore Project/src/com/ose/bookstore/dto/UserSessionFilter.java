package com.ose.bookstore.dto;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ose.bookstore.controller.UserAccountController;


/**Servlet Filter implementation class UserSessionFilter
 * @author OSE Nepal
 * @version 1.3.0 Oct 4, 2013
 */
@WebFilter("/faces/webpages/registered/*")
public class UserSessionFilter implements Filter {

	@Inject
	UserAccountController userAccountController;
	public UserSessionFilter() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		if (userAccountController.isFlag()) {
			System.out.println("logged in");
			chain.doFilter(request, response);
		} else {
			System.out.println("not logged in");
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/faces/webpages/userRegistration.xhtml");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

}
