package com.enzenar.enzen.getjobdescription.schema;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"id",
"descone",
"desctwo",
"status",
"responseCode",
"responseText"
})
public class JobDescriptionReesponseSchema {

@JsonProperty("id")
private String id;
@JsonProperty("descone")
private String descone;
@JsonProperty("desctwo")
private String desctwo;
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

@JsonProperty("descone")
public String getDescone() {
return descone;
}

@JsonProperty("descone")
public void setDescone(String descone) {
this.descone = descone;
}

@JsonProperty("desctwo")
public String getDesctwo() {
return desctwo;
}

@JsonProperty("desctwo")
public void setDesctwo(String desctwo) {
this.desctwo = desctwo;
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
