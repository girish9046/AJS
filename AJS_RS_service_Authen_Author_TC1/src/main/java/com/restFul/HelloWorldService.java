package com.restFul;

import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONObject;

import com.google.gson.Gson;



 


//service class which will retrun teh countries and states
@Path("/helloo")
public class HelloWorldService {

    //method to get the list of countries,it doen not accept any input parameters,returns the hashmap as a JSON
    @GET
    @Path("/getcountry")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCountries() {
        System.out.println("...........................getCountries222");
        ArrayList countries = new ArrayList();
        for (int i = 0; i < 10; i++) {
            countries.add("country" + i);
        }
        HashMap hm = new HashMap();
        hm.put("countries", countries);
System.out.println(hm+"...........................getCountries222");
JSONObject obj = new JSONObject(hm);
        //return Response.status(200).entity(obj).build();
		  return Response.status(200).entity(hm).build();

    }
	
	 //method to DELETE thecountrie,it  accept  input parameters,returns the STRING as a JSON
    @DELETE
    @Path("/deletecountry/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCountries(@PathParam("param") String country) {
		String result=country+" deleted";
        System.out.println(".......deleteCountries........result............"+result);
        return Response.status(200).entity(result).build();

    }

    //method to get the list of states,returns the hashmap as a JSON
    @GET
    @Path("getstates/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStates(@PathParam("param") String country) {
        System.out.println("...........................getCountries2222" + country);
        ArrayList states = new ArrayList();
        for (int i = 0; i < 10; i++) {
            states.add("states" + i);
        }
        HashMap hm = new HashMap();
        hm.put("states", states);

        return Response.status(200).entity(hm).build();

    }

    //method to get the list of states,returns the hashmap as a JSON
    @GET
    @Path("printCountriesGet/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response printCountriesGet(@PathParam("param") String countryJson) {
		  System.out.println( "...GET..countryJson..........." + countryJson);
        Gson gson = new Gson();
        ArrayList country = gson.fromJson(countryJson, ArrayList.class);
        for (int i = 0; i < country.size(); i++) {
            System.out.println(i + "................" + country.get(i));
        }

        return Response.status(200).entity("printed successfully").build();

    }

    //method to get the list of states,returns the hashmap as a JSON
    @POST
    @Path("/printCountriesPost")
    @Produces(MediaType.APPLICATION_JSON)
    public Response printCountriesPost(String countryJson) {
		System.out.println( "...POST..countryJson..........." + countryJson);
        Gson gson = new Gson();
        ArrayList country = gson.fromJson(countryJson, ArrayList.class);
        for (int i = 0; i < country.size(); i++) {
            System.out.println(i + "................" + country.get(i));
        }
        HashMap hm = new HashMap();
        hm.put("country", country);
        return Response.status(201).entity(hm).build();

    }
	
	 //method to SAVE the list of states,returns the hashmap as a JSON
    @PUT
    @Path("/saveCountriesPost")
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveCountriesPost(ArrayList country) {
		System.out.println( "...PUT..countryJson11..........." + country);
//        Gson gson = new Gson();
//        ArrayList country = gson.fromJson(countryJson, ArrayList.class);
        for (int i = 0; i < country.size(); i++) {
            System.out.println(i + "................" + country.get(i));
        }
        HashMap hm = new HashMap();
        hm.put("country", country);
        return Response.status(201).entity(hm).build();

    }
	
}
