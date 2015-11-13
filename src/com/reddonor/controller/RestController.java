package com.reddonor.controller;
 
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.reddonor.entity.LoginBean;
import com.reddonor.entity.UserBean;
import com.reddonor.services.RedDonorService;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;

@Controller
public class RestController{
	
	private RedDonorService redDonorService;
	
	@RequestMapping("/redirectUrl.htm")
	public ModelAndView redirectUrl(HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		Facebook facebook = null;
		if(request.getSession().getAttribute("facebook") != null) 
		{
			facebook = (Facebook) request.getSession().getAttribute("facebook");
			String facebookId = facebook.getMe().getId();
			request.setAttribute("fbid", facebookId);
			 
			if(facebookId.equals(getUserDeatails(request, response).getFacebookId())){
				
			  return new ModelAndView("search","facebook", facebook);	
			}
			return new ModelAndView("welcome", "facebook",facebook);
		}
		return new ModelAndView("index", "model","homepage");
	}
	
	

	@RequestMapping("/fblogin.htm")
	public String fblogin(HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		Facebook facebook = new FacebookFactory().getInstance();
		String appId = "827202724017029";
        String appSecret="8bdc6372917fb927803528804329180e";
        facebook.setOAuthAppId(appId, appSecret);
        request.getSession().setAttribute("facebook", facebook);
        StringBuffer callbackURL = request.getRequestURL();
        
        int index = callbackURL.lastIndexOf("/");
        System.out.println(callbackURL);
        callbackURL.replace(index, callbackURL.length(), "").append("/callback.htm");
        String redirectURL = facebook.getOAuthAuthorizationURL(callbackURL.toString());
        System.out.println(redirectURL);
        return "redirect:"+redirectURL;
 
	}
	
	@RequestMapping(value = "/login.htm", method = RequestMethod.POST)
	public ModelAndView loginUser(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		//read inputs
		//System.out.println("login entered");
	   String username = request.getParameter("uname");
	   String drPswd = request.getParameter("pwd");
	   Map<String, String> map = new HashMap<String,String>();
	   try{  
		   
		LoginBean loginBean = redDonorService.login(username, drPswd);
		if(loginBean.getStatus())
		{
		map.put("status", "200");
		map.put("session", loginBean.getCsrfToken());
		System.out.println("sucess");
		
		return new ModelAndView("search", "loginBean",loginBean);
		}
		else
		{
	        map.put("status", "404");
	        System.out.println("failure");
	        return new ModelAndView("index", "model","homepage");
		}
	   }
	   catch(Exception e)
	   {
		   System.out.println(e.getMessage()); 
		   e.printStackTrace();
		   map.put("status", "400");
	   }
		return new ModelAndView("jsonView", "model",map);
 
	}
 
	@RequestMapping("/callback.htm")
	public ModelAndView callBack(HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		Facebook facebook = (Facebook) request.getSession().getAttribute("facebook");
        String oauthCode = request.getParameter("code");
        try {
            facebook.getOAuthAccessToken(oauthCode);
        } catch (FacebookException e) {
            throw new ServletException(e);
        }
		String facebookId = facebook.getMe().getId();
		request.setAttribute("fbid", facebookId);
        if(facebookId.equals(getUserDeatails(request, response).getFacebookId())){
			
			  return new ModelAndView("search","facebook", facebook);	
		}
        else{
		      return new ModelAndView("welcome", "facebook",facebook);
        }
 
	}
 
	@RequestMapping("/logout.htm")
	public String update(HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		Facebook facebook = (Facebook) request.getSession().getAttribute("facebook");
        String accessToken = "";
        try {
        	accessToken = facebook.getOAuthAccessToken().getToken();
        } catch (Exception e) {
            throw new ServletException(e);
        }
        request.getSession().invalidate();

        // Log Out of the Facebook
        StringBuffer next = request.getRequestURL();
        int index = next.lastIndexOf("/");
        next.replace(index+1, next.length(), "");
        System.out.println(next.toString() +"redirectUrl.htm");
        System.out.println(accessToken);
        String url = "http://www.facebook.com/logout.php?next=" + next.toString() +"redirectUrl.htm"+ "&access_token=" + accessToken;
        //response.sendRedirect("http://www.facebook.com/logout.php?next=" + next.toString() + "&access_token=" + accessToken);
        return "redirect:"+ url;
		//return new ModelAndView(" ", " "," ");
 
	}
	
	@RequestMapping(value="/register.htm", method = RequestMethod.POST)
	public ModelAndView registerUser(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		//read inputs
		//int drMobile = Integer.parseInt(request.getParameter("mobile"));
		
		String fname = request.getParameter("firstname");
		String lname = request.getParameter("lastname");
		String email = request.getParameter("email");
		//int drciId = Integer.parseInt(request.getParameter("city"));
		//int drArId = Integer.parseInt(request.getParameter("area"));
		//int drCoId = Integer.parseInt(request.getParameter("country"));
		//int drBgId = Integer.parseInt(request.getParameter("bloodgroup"));
		String password = request.getParameter("password");
		
		//String drFirstName = request.getParameter("firstName");
		//String drLastName = request.getParameter("lastname");
		//Map<String, String> map = new HashMap<String,String>();
		//map.put("status", "200");
		// basic validations 
		try
		{
		    //redDonorService.register(drMobile, drPswd,drEmail,drciId,drArId,drCoId,drBgId,drFirstName,drLastName);
		    
		    boolean reg = redDonorService.register(fname, lname, email, password);
		   if(reg)
		   {
			   return new ModelAndView("welcome");   
		   }
		   else 
			   return new ModelAndView("index");
		// read all names from request
		}
		catch(Exception e)
		{
	    e.printStackTrace();
		//map.put("status", "104");
		}
		// call register
		return new ModelAndView("", "model","");
 
	}
 
	@SuppressWarnings("unused")
	@RequestMapping("/getUserDetails.htm")
	public UserBean getUserDeatails(HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		System.out.println("getuser details entered");
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");
		String facebookId = (String) request.getAttribute("fbid");
		UserBean user = null;
		try{
			if(email != null && email != "")
			{
				user = redDonorService.getUserDetails(email);
			}
			else if(mobile != null){
				user = redDonorService.getUserDetails(mobile);
			}
			else{
				user = redDonorService.getUserDetails(facebookId);
			}
			System.out.println(user.toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("getuser details ended");
 
		return user;
 
	}
	
	
	public void setRedDonorService(RedDonorService redDonorService)
	{
		this.redDonorService = redDonorService;
	}
	public RedDonorService getRedDonorService() {
		return redDonorService;
	}
	
	@RequestMapping("/isEmailAvailable.htm")
	public ModelAndView isEmailAvailable(HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String,Object>();	
		String email = request.getParameter("email");
		boolean isEmailAvailable = redDonorService.isEmailAvailable(email);
		if(isEmailAvailable){
			map.put("status", Boolean.TRUE);
		}
		else
			map.put("status", Boolean.TRUE);
			
			 
			
		return new ModelAndView("jsonView", "model",map);
		
	}
	

 
}