package com.example.web.ui.error;

import com.google.common.base.Throwables;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.MessageFormat;
import java.util.Set;

@Controller
@SessionAttributes("userRoles")
class CustomErrorController {

	/**
	 * Display an error page, as defined in web.xml <code>custom-error</code> element.
	 */
	@RequestMapping("/generalError")
	public String generalError(HttpServletRequest request, HttpServletResponse response, Model model) {
		// retrieve some useful information from the request
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
		// String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");
		String exceptionMessage = getExceptionMessage(throwable, statusCode);

		String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
		if (requestUri == null) {
			requestUri = "Unknown";
		}

		String message = MessageFormat.format("{0} returned for {1} with message {2}",
			statusCode, requestUri, exceptionMessage
		);

		model.addAttribute("errorMessage", message);
        return "error/general";
	}

	@RequestMapping("/accessDenied")
    public String accessDenied(HttpServletRequest request, HttpServletResponse response, Model model){
         // check user role to decide where we will send them after access denied page
        Set<String> userRoles = AuthorityUtils.authorityListToSet(SecurityContextHolder.getContext().getAuthentication().getAuthorities());

        String referer = request.getHeader("Referer");

        String message = MessageFormat.format("403 Forbidden - You are not authorised to view {0} please click the link below to return to the home page", referer);
        model.addAttribute("errorMessage", message);

        if(userRoles.contains("ROLE_CUSTOMER")){
            model.addAttribute("signInUrl", "/customer/home");
        }else if(userRoles.contains("ROLE_ADMIN")){
            model.addAttribute("signInUrl", "/admin/home");
        }

        return "error/forbidden";


    }





    private String getExceptionMessage(Throwable throwable, Integer statusCode) {
		if (throwable != null) {
			return Throwables.getRootCause(throwable).getMessage();
		}
		HttpStatus httpStatus = HttpStatus.valueOf(statusCode);
		return httpStatus.getReasonPhrase();
	}



}
