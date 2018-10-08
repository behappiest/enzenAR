package com.enzenar.enzen.jobtodo.schema;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"task1",
"task2",
"task3",
"task4",
"task5",
"status",
"responseCode",
"responseText"
})
public class JobToDoResponseSchema {

@JsonProperty("task1")
private String task1;
@JsonProperty("task2")
private String task2;
@JsonProperty("task3")
private String task3;
@JsonProperty("task4")
private String task4;
@JsonProperty("task5")
private String task5;

@JsonProperty("status")
private String status;

@JsonProperty("responseCode")
private String responseCode;

@JsonProperty("responseText")
private String responseText;


@JsonProperty("task1")
public String getTask1() {
return task1;
}

@JsonProperty("task1")
public void setTask1(String task1) {
this.task1 = task1;
}

@JsonProperty("task2")
public String getTask2() {
return task2;
}

@JsonProperty("task2")
public void setTask2(String task2) {
this.task2 = task2;
}

@JsonProperty("task3")
public String getTask3() {
return task3;
}

@JsonProperty("task3")
public void setTask3(String task3) {
this.task3 = task3;
}

@JsonProperty("task4")
public String getTask4() {
return task4;
}

@JsonProperty("task4")
public void setTask4(String task4) {
this.task4 = task4;
}

@JsonProperty("task5")
public String getTask5() {
return task5;
}

@JsonProperty("task5")
public void setTask5(String task5) {
this.task5 = task5;
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
