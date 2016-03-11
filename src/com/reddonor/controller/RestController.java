package com.reddonor.controller;
 
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.reddonor.entity.DonorsList;
import com.reddonor.entity.LoginBean;
import com.reddonor.entity.UserBean;
import com.reddonor.services.RedDonorService;
import com.restfb.json.JsonArray;

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
				
			  return new ModelAndView("home","facebook", facebook);	
			}
			return new ModelAndView("welcome", "facebook",facebook);
		}
		return new ModelAndView("index", "model","homepage");
	}
	
	

	@RequestMapping("/fblogin.htm")
	public String fblogin(HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		Facebook facebook = new FacebookFactory().getInstance();
		String appId = "1626283114256597";
        String appSecret="d009f78c766e45f09bd1dfe7a0bb966f";
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
	public ModelAndView loginUser(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// read inputs
		System.out.println("login entered");
		String username = request.getParameter("uname");
		String drPswd = request.getParameter("pwd");
		Map<String, String> map = new HashMap<String, String>();
		try {

			LoginBean loginBean = redDonorService.login(username, drPswd);
			if (loginBean.getStatus()) {
				UserBean user = new UserBean();
				if(username.contains("@")){
					request.setAttribute("email", username);
				}
				else{
					request.setAttribute("mobile", username);
				}
				user = this.getUserDeatails(request, response);
				map.put("status", "200");
				map.put("session", loginBean.getCsrfToken());
				System.out.println("sucess");
				if (user.getBloodGroup() != null) {

					return new ModelAndView("home", "loginBean", loginBean);
				} else {
					map.put("firstname", user.getfName());
					return new ModelAndView("welcome", "model", loginBean);

				}
			} else {
				map.put("status", "404");
				System.out.println("failure");
				return new ModelAndView("index", "model", "homepage");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			map.put("status", "400");
		}
		return new ModelAndView("jsonView", "model", map);

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
		String fbId = facebook.getMe().getId();
		String fname = facebook.getMe().getFirstName();
		String lname = facebook.getMe().getLastName();
		String email = null;
		String password = null;
		request.setAttribute("fbid", fbId);
		UserBean user = getUserDeatails(request, response);
        if(!(fbId.equals(user.getFacebookId()))){
        	
        	LoginBean loginBean = redDonorService.register(fname, lname, email, password,fbId);
		    Map<String , String> map = new HashMap<String , String>();
		   if(loginBean.getStatus())
		   {
			   return new ModelAndView("fbwelcome","model", loginBean);   
		   }
		   else 
			   return new ModelAndView("index");
        	
        	
		
		}
        else if(user.getBloodGroup() == null || user.getBloodGroup() == ""){
        	
			  return new ModelAndView("fbwelcome","facebook", facebook);	
        	
        }
        else{
        	return new ModelAndView("home","facebook", facebook);	
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
	
	@RequestMapping(value="/register1.htm", method = RequestMethod.POST)
	public ModelAndView registerUser(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		//read inputs
		
		String fname = request.getParameter("firstname");
		String lname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String fbId = request.getParameter("facebookid");
		
		
		// basic validations 
		try
		{
		    
		    LoginBean loginBean = redDonorService.register(fname, lname, email, password,fbId);
		    Map<String , String> map = new HashMap<String , String>();
		   if(loginBean.getStatus())
		   {
			   return new ModelAndView("welcome","model", loginBean);   
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
	
	
	@RequestMapping(value="/register2.htm", method = RequestMethod.POST)
	public ModelAndView registerUserDetails(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		//read inputs
		//int drMobile = Integer.parseInt(request.getParameter("mobile"));
		System.out.println("registration method executin");
		
		String bloodGroup = request.getParameter("bloodGroup");
		String mobile = request.getParameter("mobile");
		String state = request.getParameter("states");
		String district = request.getParameter("districts");
		String subDistrict = request.getParameter("subDistricts");
		String usrID = request.getParameter("usrID");
		String facebookId = request.getParameter("facebookID");
		System.out.println("facebook id of an user is"+facebookId);
		
		try
		{
		    //redDonorService.register(drMobile, drPswd,drEmail,drciId,drArId,drCoId,drBgId,drFirstName,drLastName);
		    
		    UserBean user = redDonorService.registerDetails(bloodGroup, mobile, state, district, subDistrict, usrID,facebookId);
		    System.out.println("user blood group is::"+user.getBloodGroup());
		   if(user.getBloodGroup()!= null)
		   {
			   return new ModelAndView("home");  
		   }
		   else 
			   return new ModelAndView("welcome");
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
 
 

	@RequestMapping("/getUserDetails.htm")
	public UserBean getUserDeatails(HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");
		String facebookId = (String) request.getAttribute("fbid");
		if(email == null && mobile == null && facebookId == null){
			email = (String)request.getAttribute("email");
			mobile = (String)request.getAttribute("mobile");
		}
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
			map.put("status", Boolean.FALSE);
			
			 
			
		return new ModelAndView("jsonView", "model",map);
		
	}
	
	@RequestMapping("/donorSearch.htm")
	public ModelAndView donorSearch(HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		System.out.println("get donor list entered");
		String bloodGroup = request.getParameter("bloodGroup");
		String state = request.getParameter("state");
		String district = request.getParameter("district");
		String subDistrict = request.getParameter("subDistrict");
		JSONArray data = redDonorService.getDonorList(bloodGroup,state,district,subDistrict);
		//Iterator it = list.iterator();
		
		
		

		return new ModelAndView("jsonView", "rows",data);
	}
	
	@RequestMapping("/getStatesList.htm")
	public ModelAndView getStatesList(HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		System.out.println("get states list entered");
		String country = request.getParameter("country");
		JSONArray statesJson = redDonorService.getStatesList(country);
		return new ModelAndView("jsonView","states",statesJson);
		
	}
	
	@RequestMapping("/getDistrictsList.htm")
	public ModelAndView getDistrictsList(HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		System.out.println("getDistrictsList entered");
		String state = request.getParameter("state");
		JSONArray districtJson = redDonorService.getDistrictsList(state);
		return new ModelAndView("jsonView","districts",districtJson);
		
	}
	
	@RequestMapping("/getSubDistrictsList.htm")
	public ModelAndView getSubDistrictsList(HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		System.out.println("getSubDistrictsList entered");
		String district = request.getParameter("district");
		JSONArray subDistrictJson = redDonorService.getSubDistrictsList(district);
		return new ModelAndView("jsonView","subDistricts",subDistrictJson);
		
	}
	
	

 
}