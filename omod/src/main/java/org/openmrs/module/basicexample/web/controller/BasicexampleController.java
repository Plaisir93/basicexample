/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.basicexample.web.controller;

import java.util.List;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import javax.servlet.http.HttpSession;
import com.google.gson.Gson;
import org.openmrs.module.basicexample.PlrUser;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.User;
import org.openmrs.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.openmrs.api.context.Context;

/**
 * This class configured as controller using annotation and mapped with the URL of
 * 'module/basicexample/basicexampleLink.form'.
 */
@Controller("${rootrootArtifactId}.BasicexampleController")
@RequestMapping("module/basicexample/basicexample.form")
public class BasicexampleController {
	
	/** Logger for this class and subclasses */
	protected final Log log = LogFactory.getLog(getClass());
	
	@Autowired
	UserService userService;
	
	/** Success form view name */
	private final String VIEW = "/module/basicexample/basicexample";
	
	/**
	 * Initially called after the getUsers method to get the landing form name
	 * 
	 * @return String form view name
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String onGet() {
		return VIEW;
	}
	
	/**
	 * All the parameters are optional based on the necessity
	 * 
	 * @param httpSession
	 * @param anyRequestObject
	 * @param errors
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String onPost(HttpSession httpSession, @ModelAttribute("anyRequestObject") Object anyRequestObject,
	        BindingResult errors) {
		
		if (errors.hasErrors()) {
			// return error view
		}
		
		return VIEW;
	}
	
	/**
	 * This class returns the form backing object. This can be a string, a boolean, or a normal java
	 * pojo. The bean name defined in the ModelAttribute annotation and the type can be just defined
	 * by the return type of this method
	 */
	
	@ModelAttribute("users")
	protected List<User> getUsers() throws Exception {
		List<User> users = userService.getAllUsers();
		
		Gson gson = new Gson();
		FileWriter writer = null;
		ArrayList<PlrUser> arrayUsers = new ArrayList<PlrUser>();
		
		try {
			writer = new FileWriter("beusers.json");
			for (User usr : users) {
				PlrUser pUser = new PlrUser();
				pUser.setId(usr.getId());
				pUser.setFamilyName(usr.getFamilyName());
				pUser.setFirstName(usr.getGivenName());
				System.out.println(usr.getFamilyName());
				arrayUsers.add(pUser);
			}
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		gson.toJson(arrayUsers, writer);
		return users;
		// this object will be made available to the jsp page under the variable name
		// that is defined in the @ModuleAttribute tag
	}
}
