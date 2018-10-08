package com.enzenar.enzen.getjobdescription.schema;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"username",
"jobDesc"
})
public class JobDescriptionRequestSchema {

@JsonProperty("username")
private String username;
@JsonProperty("jobDesc")
private String jobDesc;

@JsonProperty("username")
public String getUsername() {
return username;
}

@JsonProperty("username")
public void setUsername(String username) {
this.username = username;
}

@JsonProperty("jobDesc")
public String getJobDesc() {
return jobDesc;
}

@JsonProperty("jobDesc")
public void setJobDesc(String jobDesc) {
this.jobDesc = jobDesc;
}

}
