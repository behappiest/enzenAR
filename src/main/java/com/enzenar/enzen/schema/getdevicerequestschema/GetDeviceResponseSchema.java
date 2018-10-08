package com.enzenar.enzen.schema.getdevicerequestschema;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"id",
"manufacturerName",
"manufacturerSerialNumber",
"kindOfTransformer",
"yearOfCommision",
"numberOfPhases",
"ratedPower",
"ratedFrequency",
"ratedVoltage",
"ratedCurrent",
"vectorGroupSymbol",
"typesOfCoolong",
"totalWeight",
"status",
"responseCode",
"responseText"
})
public class GetDeviceResponseSchema {

@JsonProperty("id")
private String id;
@JsonProperty("manufacturerName")
private String manufacturerName;
@JsonProperty("manufacturerSerialNumber")
private String manufacturerSerialNumber;
@JsonProperty("kindOfTransformer")
private String kindOfTransformer;
@JsonProperty("yearOfCommision")
private String yearOfCommision;
@JsonProperty("numberOfPhases")
private String numberOfPhases;
@JsonProperty("ratedPower")
private String ratedPower;
@JsonProperty("ratedFrequency")
private String ratedFrequency;
@JsonProperty("ratedVoltage")
private String ratedVoltage;
@JsonProperty("ratedCurrent")
private String ratedCurrent;
@JsonProperty("vectorGroupSymbol")
private String vectorGroupSymbol;
@JsonProperty("typesOfCoolong")
private String typesOfCoolong;
@JsonProperty("totalWeight")
private String totalWeight;
@JsonProperty("status")
private String status;
@JsonProperty("responseCode")
private String responseCode;
@JsonProperty("responseText")
private String responseText;

@JsonProperty("id")
public String getId() {
return id;
}

@JsonProperty("id")
public void setId(String id) {
this.id = id;
}

@JsonProperty("manufacturerName")
public String getManufacturerName() {
return manufacturerName;
}

@JsonProperty("manufacturerName")
public void setManufacturerName(String manufacturerName) {
this.manufacturerName = manufacturerName;
}

@JsonProperty("manufacturerSerialNumber")
public String getManufacturerSerialNumber() {
return manufacturerSerialNumber;
}

@JsonProperty("manufacturerSerialNumber")
public void setManufacturerSerialNumber(String manufacturerSerialNumber) {
this.manufacturerSerialNumber = manufacturerSerialNumber;
}

@JsonProperty("kindOfTransformer")
public String getKindOfTransformer() {
return kindOfTransformer;
}

@JsonProperty("kindOfTransformer")
public void setKindOfTransformer(String kindOfTransformer) {
this.kindOfTransformer = kindOfTransformer;
}

@JsonProperty("yearOfCommision")
public String getYearOfCommision() {
return yearOfCommision;
}

@JsonProperty("yearOfCommision")
public void setYearOfCommision(String yearOfCommision) {
this.yearOfCommision = yearOfCommision;
}

@JsonProperty("numberOfPhases")
public String getNumberOfPhases() {
return numberOfPhases;
}

@JsonProperty("numberOfPhases")
public void setNumberOfPhases(String numberOfPhases) {
this.numberOfPhases = numberOfPhases;
}

@JsonProperty("ratedPower")
public String getRatedPower() {
return ratedPower;
}

@JsonProperty("ratedPower")
public void setRatedPower(String ratedPower) {
this.ratedPower = ratedPower;
}

@JsonProperty("ratedFrequency")
public String getRatedFrequency() {
return ratedFrequency;
}

@JsonProperty("ratedFrequency")
public void setRatedFrequency(String ratedFrequency) {
this.ratedFrequency = ratedFrequency;
}

@JsonProperty("ratedVoltage")
public String getRatedVoltage() {
return ratedVoltage;
}

@JsonProperty("ratedVoltage")
public void setRatedVoltage(String ratedVoltage) {
this.ratedVoltage = ratedVoltage;
}

@JsonProperty("ratedCurrent")
public String getRatedCurrent() {
return ratedCurrent;
}

@JsonProperty("ratedCurrent")
public void setRatedCurrent(String ratedCurrent) {
this.ratedCurrent = ratedCurrent;
}

@JsonProperty("vectorGroupSymbol")
public String getVectorGroupSymbol() {
return vectorGroupSymbol;
}

@JsonProperty("vectorGroupSymbol")
public void setVectorGroupSymbol(String vectorGroupSymbol) {
this.vectorGroupSymbol = vectorGroupSymbol;
}

@JsonProperty("typesOfCoolong")
public String getTypesOfCoolong() {
return typesOfCoolong;
}

@JsonProperty("typesOfCoolong")
public void setTypesOfCoolong(String typesOfCoolong) {
this.typesOfCoolong = typesOfCoolong;
}

@JsonProperty("totalWeight")
public String getTotalWeight() {
return totalWeight;
}

@JsonProperty("totalWeight")
public void setTotalWeight(String totalWeight) {
this.totalWeight = totalWeight;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public String getResponseCode() {
	return responseCode;
}

public void setResponseCode(String responseCode) {
	this.responseCode = responseCode;
}

public String getResponseText() {
	return responseText;
}

public void setResponseText(String responseText) {
	this.responseText = responseText;
}



}

