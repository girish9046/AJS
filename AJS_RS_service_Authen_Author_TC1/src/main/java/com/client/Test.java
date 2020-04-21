/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.client;


import com.encdyc.AESencrp;
import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
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

public class Test {

    public static void main(String[] args) {
		
		try {
			String authenticationKey="Basic GIRISH MARTHAM";//AESencrp.encrypt("Basic GIRISH MARTHAM");//this is the authentication
			ClientConfig config = new DefaultClientConfig();
			Client client = Client.create(config);
			WebResource service = client.resource(getBaseURI());//getting service object with base uri
			//client.addFilter(new HTTPBasicAuthFilter("tomcat", "tomcat"));//this should be the tomcat user .mention the user role in web.xml , tomcat-users.xml// this is for Authorization
			//client.getProperties().put("Authentication", "Basic GIRISH MARTHAM");
			//service.header("Authentication", "Basic GIRISH MARTHAM");
			//service.setProperty("Authentication", "Basic GIRISH MARTHAM");
			//http://10.3.34.141:8080/JerryRestFull/rest/helloo/country
			//Authorization: Basic dG9tY2F0OnRvbWNhdA==
			//Authentication: Basic GIRISH MARTHAM
			
			Gson gson = new Gson();
			
			
			
			
			//code to get the list of countries
//"rest" is service path , "helloo" is service class path , "country" is the path of method which returns countries ,this method does not have input parameters.
			HashMap cmap = (HashMap) gson.fromJson((String) (service.path("rest").path("helloo/getcountry").header("Authentication", authenticationKey).accept(MediaType.APPLICATION_JSON).get(String.class)), HashMap.class);
			ArrayList countries = (ArrayList) cmap.get("countries");
			for (int i = 0; i < countries.size(); i++) {
				System.out.println("countries............." + countries.get(i));
			}
			
//code to delete country details for given country
//"rest" is service path , "helloo" is service class path , "states" is the path of method which returns states ,this method have input parameters "india".
			String dcountry = service.path("rest").path("helloo/deletecountry").path("/india").header("Authentication", authenticationKey).accept(MediaType.APPLICATION_JSON).delete(String.class);
			//String dcountry = (String) gson.fromJson((String) (service.path("rest").path("helloo/deletecountry").path("/india").header("Authentication", authenticationKey).accept(MediaType.APPLICATION_JSON).delete(String.class)), String.class);
			
				System.out.println("deleted............." +dcountry);
			
//code to get the list of states for given country
//"rest" is service path , "helloo" is service class path , "states" is the path of method which returns states ,this method have input parameters "india".
			HashMap smap = (HashMap) gson.fromJson((String) (service.path("rest").path("helloo/getstates").path("/india").header("Authentication", authenticationKey).accept(MediaType.APPLICATION_JSON).get(String.class)), HashMap.class);
			ArrayList states = (ArrayList) smap.get("states");
			for (int i = 0; i < states.size(); i++) {
				System.out.println("states............." + states.get(i));
			}
			
//code to print the list of countries GET request
//"rest" is service path , "helloo" is service class path , "printCountriesGet" is the path of method and "countrieslist" is the JSNO form of arraylist.
			String countrieslist = gson.toJson(countries);//convert arraylist to JSON
			System.out.println("GET countrieslist............." + countrieslist);
			String resultGet = service.path("rest").path("helloo/printCountriesGet").path("/" + countrieslist).header("Authentication", authenticationKey).accept(MediaType.APPLICATION_JSON).get(String.class); //GET request
			System.out.println("GET result............." + resultGet);
			
//code to print the list of countries POST request and to get the hashmap returmed from service
//"rest" is service path , "helloo" is service class path , "printCountriesGet" is the path of method and "countrieslist" is the JSNO form of arraylist.
			ClientResponse response = service.path("rest").path("helloo/printCountriesPost").header("Authentication", authenticationKey).type(MediaType.APPLICATION_JSON).post(ClientResponse.class, countrieslist); //POST request
			
			String resultPost = response.getEntity(String.class);
			HashMap countriesmap=gson.fromJson((String)resultPost, HashMap.class);
			System.out.println("countriesmap............." + countriesmap);
			ArrayList countriesh = (ArrayList) countriesmap.get("country");
			for (int i = 0; i < countriesh.size(); i++) {
				System.out.println("countriesh............." + countriesh.get(i));
			}
			System.out.println("POST result............." + resultPost);
			
			
			
			
			//code to save the list of countries PUT request and to get the hashmap returmed from service
//"rest" is service path , "helloo" is service class path , "printCountriesGet" is the path of method and "countrieslist" is the JSNO form of arraylist.
			ClientResponse responsesave = service.path("rest").path("helloo/saveCountriesPost").header("Authentication", authenticationKey).type(MediaType.APPLICATION_JSON).put(ClientResponse.class, countrieslist); //PUT request
			
			String resultPost1 = responsesave.getEntity(String.class);
			HashMap countriesmap1=gson.fromJson((String)resultPost1, HashMap.class);
			System.out.println("countriesmap1............." + countriesmap1);
			ArrayList countriesh1 = (ArrayList) countriesmap1.get("country");
			for (int i = 0; i < countriesh1.size(); i++) {
				System.out.println("countriesh............." + countriesh1.get(i));
			}
			System.out.println("PUT result1............." + resultPost1);
		} catch (Exception ex) {
			Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
		}

    }

    private static URI getBaseURI() {
        //base uri of webservice
        return UriBuilder.fromUri("http://localhost/AJS_RS_service_Authen_Author_TC1/").build();
    }

}


