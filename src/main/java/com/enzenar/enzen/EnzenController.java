package com.enzenar.enzen;

import java.nio.charset.Charset;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.web.bind.annotation.RequestBody;

import com.enzenar.enzen.schema.getdevicerequestschema.GetDeviceResponseSchema;
import com.enzenar.enzen.service.IEnzenService;
import com.enzenar.enzen.serviceImpl.EnzenServiceImpl;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

@Path("/controller")
public class EnzenController {

	IEnzenService service = EnzenServiceImpl.getInstance();
	
	@POST
	@Path("/authenticateUser")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_XML})
	public String login(@RequestBody String request){
		JsonObject response = new JsonObject();
		try{
			response = service.authenicateUser(request);
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println(response.toString());
		return response.toString();
	}
	
	@POST
	@Path("/getTempAndAirQuality")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_XML})
	public String getTempAndAirQuality(@RequestBody String request){
		String response = null;
		try{
			response = service.getTempAndAirQuality(request);
		}catch(Exception e){
			e.printStackTrace();
		}
		//System.out.println(response.toString());
		return response;
	}
	
	@POST
	@Path("/getAboutDevice")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_XML})
	public String getAboutDevice(@RequestBody String request){
		JsonObject response = new JsonObject();
		try{
			response = service.getAboutDevice(request);
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("getAboutDevice Response ---- "+new Gson().toJson(response));
		return response.toString();
	}
	@POST
	@Path("/jobToDo")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_XML})
	public String jobToDo(@RequestBody String request){
		JsonObject response = new JsonObject();
		try{
			response = service.jobToDo(request);
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("jobToDo Response ---- "+new Gson().toJson(response));
		return response.toString();
	}
	@POST
	@Path("/getJobDescription")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_XML})
	public String getJobDescription(@RequestBody String request){
		JsonObject response = new JsonObject();
		try{
			response = service.getJobDescription(request);
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("jobToDo Response ---- "+new Gson().toJson(response));
		return response.toString();
	}
	
	@POST
	@Path("/submitReport")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_XML})
	public String submitReport(@RequestBody String request){
		JsonObject response = new JsonObject();
		try{
			response = service.submitReport(request);
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("submitReport Response ---- "+new Gson().toJson(response));
		return response.toString();
	}
}