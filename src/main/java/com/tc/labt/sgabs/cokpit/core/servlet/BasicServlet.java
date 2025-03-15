package com.tc.labt.sgabs.cokpit.core.servlet;

import java.io.IOException;
import java.net.URI;
import java.util.Objects;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atlassian.sal.api.auth.LoginUriProvider;
import com.atlassian.sal.api.user.UserManager;
import com.atlassian.sal.api.user.UserProfile;

public abstract class BasicServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1289408801493210789L;
	
	private final LoginUriProvider uriProvider;
	private final UserManager userManager;

    public BasicServlet(LoginUriProvider loginUriProvider, UserManager userManager){
        this.uriProvider = loginUriProvider;
        this.userManager = userManager;
    }

    private URI getUri(HttpServletRequest request){

        StringBuffer buffer = request.getRequestURL();
        if(request.getQueryString() != null){
            buffer.append("?");
            buffer.append(request.getQueryString());
        }
        return URI.create(buffer.toString());
    }

    public void redirectToLoginPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect(uriProvider.getLoginUri(getUri(request)).toASCIIString());
    }
    
    public void hasAccessToCokpit(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	UserProfile userProfile = userManager.getRemoteUser(request);
    	Objects.requireNonNull(userProfile);
    }
    
    public void hasAccessToCokpitOfSubsidiary(HttpServletRequest request, HttpServletResponse response, String subsidiary) throws IOException {
    	UserProfile userProfile = userManager.getRemoteUser(request);
    	Objects.requireNonNull(userProfile);
    }
}
