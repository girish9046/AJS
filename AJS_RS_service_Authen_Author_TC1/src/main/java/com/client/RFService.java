/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.client;

import com.encdyc.AESencrp;
import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

/**
 *
 * @author gmartham
 */
public class RFService {

	public static WebResource getService() {
		WebResource service = null;
		Client client;
		ClientConfig config;
		try {
			config = new DefaultClientConfig();
			client = Client.create(config);
			service = client.resource(getBaseURI());//getting service object with base uri
			client.addFilter(new HTTPBasicAuthFilter(Constants.AUTHORIZATION_USER, Constants.AUTHORIZATION_PASSWORD));//this should be the tomcat user .mention the user role in web.xml , tomcat-users.xml// this is for Authorization
		} catch (Exception e) {
			Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, e);
		}

		return service;
	}

	private static URI getBaseURI() {
		//base uri of webservice
		return UriBuilder.fromUri(Constants.SERVICE_URI).build();
	}

	public static String getAuthenticationKey() {
		String authenticationKey = null;
		try {
			authenticationKey = AESencrp.encrypt(Constants.AUTHENTICATION_KEY_RAW);//this is the authentication
		} catch (Exception e) {
			Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, e);
		}
		return authenticationKey;
	}

	public static void main(String[] args) {
		WebResource service = getService();
		Gson gson = new Gson();
		String authenticationKey = getAuthenticationKey();

		HashMap cmap = (HashMap) gson.fromJson((String) (service.path(Constants.SERVICE_PATH).path(Constants.HELLOO_PATH).path("/getcountry").header(Constants.AUTHENTICATION_PROPERTY, authenticationKey).accept(MediaType.APPLICATION_JSON).get(String.class)), HashMap.class);
		ArrayList countries = (ArrayList) cmap.get("countries");
		for (int i = 0; i < countries.size(); i++) {
			System.out.println("countries............." + countries.get(i));
		}
	}
	
	
}
