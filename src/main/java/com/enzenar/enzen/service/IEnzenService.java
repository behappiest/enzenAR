package com.enzenar.enzen.service;

import java.io.IOException;

import com.enzenar.enzen.schema.getdevicerequestschema.GetDeviceResponseSchema;
import com.google.gson.JsonObject;

public interface IEnzenService {

	JsonObject authenicateUser(String request);

	String getTempAndAirQuality(String request) throws IOException;

	JsonObject getAboutDevice(String request);

	JsonObject jobToDo(String request);

	JsonObject getJobDescription(String request);

	JsonObject submitReport(String request);

}
