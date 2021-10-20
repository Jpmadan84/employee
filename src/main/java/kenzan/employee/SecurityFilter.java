package kenzan.employee;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.GenericFilterBean;

import kenzan.employee.service.Logger;

@Configuration
public class SecurityFilter extends GenericFilterBean {
	@Value("${authorization-header}")
	private String token;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResonse = (HttpServletResponse) response;
		Logger.in(getClass()).info("Request uri: "+httpRequest.getRequestURI());
		
		String authorizationHeader = httpRequest.getHeader("token");
		if ("DELETE".equals(httpRequest.getMethod())
				&& !token.equals(authorizationHeader)) {
			httpResonse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}
		chain.doFilter(httpRequest, response);
		
	}
	

}
