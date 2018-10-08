package com.enzenar.enzen.serviceImpl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Date;

import com.enzenar.enzen.airqualityresponseschema.AirQualityAPIResponse;
import com.enzenar.enzen.getjobdescription.schema.JobDescriptionReesponseSchema;
import com.enzenar.enzen.getjobdescription.schema.JobDescriptionRequestSchema;
import com.enzenar.enzen.jobtodo.schema.JobToDoRequestSchema;
import com.enzenar.enzen.jobtodo.schema.JobToDoResponseSchema;
import com.enzenar.enzen.schema.LoginRequestSchema;
import com.enzenar.enzen.schema.Response;
import com.enzenar.enzen.schema.getdevicerequestschema.GetDeviceRequestSchema;
import com.enzenar.enzen.schema.getdevicerequestschema.GetDeviceResponseSchema;
import com.enzenar.enzen.service.IEnzenService;
import com.enzenar.enzen.submitfinalreport.schema.SubmitReportRequestSchema;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class EnzenServiceImpl implements IEnzenService{

	private static IEnzenService sevice;
	public static IEnzenService getInstance() {
		if (sevice == null) {
			sevice = new EnzenServiceImpl();
	}
	return sevice;
	}
	
	
	@Override
	public JsonObject authenicateUser(String request) {
		JsonObject response = new JsonObject();
		Gson gson = new Gson();
		LoginRequestSchema loginRequest = gson.fromJson(request, LoginRequestSchema.class);
		try{
			String filePath = "/Users/surajshukla/Documents/personal/enzendb/";
			byte[] jsonData = Files.readAllBytes(Paths.get(filePath+loginRequest.getUsername()+".txt"));
			//create ObjectMapper instance
			ObjectMapper objectMapper = new ObjectMapper();
			//convert json string to object
			LoginRequestSchema fileResponse = objectMapper.readValue(jsonData, LoginRequestSchema.class);
			if(fileResponse.getUsername().equals(loginRequest.getUsername()) && fileResponse.getPassword().equals(loginRequest.getPassword())){
				System.out.println("Username/password match");
				response.addProperty("status", "success");
				response.addProperty("responseCode", "0000");
			}else{
				System.out.println("Username/password is incorrect");
				response.addProperty("status", "failure");
				response.addProperty("responseText", "username/password is incorrect");
				response.addProperty("responseCode", "0001");
			}
		}catch(Exception e){
			e.printStackTrace();
			response.addProperty("status", "failure");
			response.addProperty("responseText", "we are unable to process your request, please try later");
			response.addProperty("responseCode", "0001");
		}
		
		return response;
	}


	@Override
	public String getTempAndAirQuality(String request) {
		Gson gson = new Gson();
		JsonObject responseObject = new JsonObject();
		JsonObject requestObject = gson.fromJson(request, JsonObject.class);
		String weatherAPIURL = "http://api.openweathermap.org/data/2.5/weather?lat="+requestObject.get("lat").getAsString()+"&lon="+requestObject.get("lon").getAsString()+"&appid=f4a964b17f4ab5fbcb14ea733510717e";
		String airQualityURL = "http://api.waqi.info/feed/geo:"+requestObject.get("lat").getAsString()+";"+requestObject.get("lon").getAsString()+"/?token=2dba0cc11daa30f14a4d32e0a95af4afb585d8ac";
		try{
		Response finalResponse = getTemperture(weatherAPIURL);
		AirQualityAPIResponse airQualityAPIResponse = getAirQuality(airQualityURL);
		
		int airQuality = Integer.parseInt(airQualityAPIResponse.getData().getAqi().toString());
		if(airQuality >=0 && airQuality <= 50){
			responseObject.addProperty("airqualitytype", "Good");
		}else if(airQuality >=51 && airQuality <= 100){
			responseObject.addProperty("airqualitytype", "Moderate");
		}else if(airQuality >=101 && airQuality <= 150){
			responseObject.addProperty("airqualitytype", "Unhealthy");
		}else if(airQuality >=151 && airQuality <= 200){
			responseObject.addProperty("airqualitytype", "Unhealthy");
		}else if(airQuality >=201 && airQuality <= 300){
			responseObject.addProperty("airqualitytype", "very Unhealthy");
		}
		responseObject.addProperty("airquality", airQualityAPIResponse.getData().getAqi());
		String relativeTemperature = finalResponse.getMain().getTemp().toString()+"\u00b0C";
		responseObject.addProperty("relativetemp", new String(relativeTemperature.getBytes(),Charset.forName("UTF-8")));
		responseObject.addProperty("status", "success");
		responseObject.addProperty("responseCode", "0000");
		
		}catch(Exception e){
			responseObject.addProperty("status", "failure");
			responseObject.addProperty("responseCode", "0001");
			responseObject.addProperty("responseText", "We are unable to process the request, please try later");
		}
		return responseObject.toString();
	}


	private AirQualityAPIResponse getAirQuality(String airQualityURL) throws IOException {
		Gson gson = new Gson();
		final String USER_AGENT = "Mozilla/5.0";
		URL obj = new URL(airQualityURL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		// optional default is GET
		con.setRequestMethod("GET");
		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + airQualityURL);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		AirQualityAPIResponse finalResponse = gson.fromJson(response.toString(), AirQualityAPIResponse.class);
		return finalResponse;
	}


	private Response getTemperture(String weatherAPIURL) throws IOException {
		Gson gson = new Gson();
		final String USER_AGENT = "Mozilla/5.0";
		URL obj = new URL(weatherAPIURL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		// optional default is GET
		con.setRequestMethod("GET");
		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + weatherAPIURL);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		Response finalResponse = gson.fromJson(response.toString(), Response.class);
		Double tempInKelvin = finalResponse.getMain().getTemp();
		Double tempInCel = tempInKelvin - 273.15;
		DecimalFormat f = new DecimalFormat("##");
		finalResponse.getMain().setTemp(new Double(f.format(tempInCel)));
		return finalResponse;
		
	}


	@Override
	public JsonObject getAboutDevice(String request) {
		GetDeviceResponseSchema fileResponse = new GetDeviceResponseSchema();
		JsonObject responseObject = new JsonObject();
		Gson gson = new Gson();
		GetDeviceRequestSchema getDeviceRequestSchema = gson.fromJson(request, GetDeviceRequestSchema.class);
		try{
			System.out.println("getAboutDevice Request ---- "+request);
			String filePath = "/Users/surajshukla/Documents/personal/enzendb/device/";
			System.out.println("filePath----"+filePath+getDeviceRequestSchema.getDeviceType()+".txt");
			byte[] jsonData = Files.readAllBytes(Paths.get(filePath+getDeviceRequestSchema.getDeviceType()+".txt"));
			//create ObjectMapper instance
			ObjectMapper objectMapper = new ObjectMapper();
			//convert json string to object
			fileResponse = objectMapper.readValue(jsonData, GetDeviceResponseSchema.class);
			System.out.println(fileResponse.getKindOfTransformer());
			fileResponse.setStatus("success");
			fileResponse.setResponseCode("0000");
		}catch(Exception e){
			e.printStackTrace();
			fileResponse.setStatus("failure");
			fileResponse.setResponseCode("0001");
			fileResponse.setResponseText("we are unable to process your request, please try later");
		}
		responseObject = gson.fromJson(gson.toJson(fileResponse), JsonObject.class);
		return responseObject;
	}


	@Override
	public JsonObject jobToDo(String request) {
		JobToDoResponseSchema fileResponse = new JobToDoResponseSchema();
		JsonObject responseObject = new JsonObject();
		Gson gson = new Gson();
		JobToDoRequestSchema getDeviceRequestSchema = gson.fromJson(request, JobToDoRequestSchema.class);
		try{
			System.out.println("jobToDo Request ---- "+request);
			String filePath = "/Users/surajshukla/Documents/personal/enzendb/"+getDeviceRequestSchema.getUsername()+"/JobToDo.txt";
			System.out.println("jobToDo file path -- "+filePath);
			byte[] jsonData = Files.readAllBytes(Paths.get(filePath));
			//create ObjectMapper instance
			ObjectMapper objectMapper = new ObjectMapper();
			//convert json string to object
			fileResponse = objectMapper.readValue(jsonData, JobToDoResponseSchema.class);
			fileResponse.setStatus("success");
			fileResponse.setResponseCode("0000");
		}catch(Exception e){
			e.printStackTrace();
			fileResponse.setStatus("failure");
			fileResponse.setResponseCode("0001");
			fileResponse.setResponseText("we are unable to process your request, please try later");
		}
		responseObject = gson.fromJson(gson.toJson(fileResponse), JsonObject.class);
		return responseObject;
	}


	@Override
	public JsonObject getJobDescription(String request) {
		JobDescriptionReesponseSchema fileResponse = new JobDescriptionReesponseSchema();
		JsonObject responseObject = new JsonObject();
		Gson gson = new Gson();
		JobDescriptionRequestSchema getDeviceRequestSchema = gson.fromJson(request, JobDescriptionRequestSchema.class);
		try{
			System.out.println("getJobDescription Request ---- "+request);
			String filePath = "/Users/surajshukla/Documents/personal/enzendb/"+getDeviceRequestSchema.getUsername()+"/"+getDeviceRequestSchema.getJobDesc()+".txt";
			System.out.println("jobToDo file path -- "+filePath);
			byte[] jsonData = Files.readAllBytes(Paths.get(filePath));
			//create ObjectMapper instance
			ObjectMapper objectMapper = new ObjectMapper();
			//convert json string to object
			fileResponse = objectMapper.readValue(jsonData, JobDescriptionReesponseSchema.class);
			fileResponse.setStatus("success");
			fileResponse.setResponseCode("0000");
		}catch(Exception e){
			e.printStackTrace();
			fileResponse.setStatus("failure");
			fileResponse.setResponseCode("0001");
			fileResponse.setResponseText("we are unable to process your request, please try later");
		}
		responseObject = gson.fromJson(gson.toJson(fileResponse), JsonObject.class);
		return responseObject;
	}


	@Override
	public JsonObject submitReport(String request) {
		JsonObject responseObject = new JsonObject();
		Gson gson = new Gson();
		SubmitReportRequestSchema submitReportRequestSchema = gson.fromJson(request, SubmitReportRequestSchema.class);
		try{
			 Date date= new Date();
			 long time = date.getTime();
			 Timestamp ts = new Timestamp(time);
			 System.out.println("Current Time Stamp: " + ts);
			System.out.println("getJobDescription Request ---- "+request);
			String filePath = "/Users/surajshukla/Documents/personal/enzendb/"+submitReportRequestSchema.getUsername()+"/"+submitReportRequestSchema.getUsername()+ts+".txt";
			System.out.println("jobToDo file path -- "+filePath);
			 // Create new file
			JsonObject contentObject = new JsonObject();
			contentObject.addProperty("issueDesc", submitReportRequestSchema.getIssueDetail());
			contentObject.addProperty("engineerName", submitReportRequestSchema.getUsername());
			contentObject.addProperty("issueStatus", submitReportRequestSchema.getIssueStatus());
            String content = contentObject.toString();
            File file = new File(filePath);

            // If file doesn't exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            // Write in file
            bw.write(content);

            // Close connection
            bw.close();
			//create ObjectMapper instance
            responseObject.addProperty("status", "success");
			responseObject.addProperty("responseCode","0000");
		}catch(Exception e){
			e.printStackTrace();
			responseObject.addProperty("status", "failure");
			responseObject.addProperty("responseCode","0001");
			responseObject.addProperty("responseText","we are unable to process your request, please try later");
		}
		return responseObject;
	}

}
