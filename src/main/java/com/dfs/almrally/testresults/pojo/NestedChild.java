package com.dfs.almrally.testresults.pojo;


import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonPropertyOrder({
	"number",
	"description",
	"duration",
	"startTime",
	"screenshots",
	"result",
	"restQuery"
	})
	
public class NestedChild {

	@JsonProperty("number")
	public Integer number;
	
	@JsonProperty("description")
	public String description;
	
	@JsonProperty("duration")
	public Integer duration;
	
	@JsonProperty("startTime")
	public double startTime;
	
	
	@JsonProperty("screenshots")
	public List<NestedScreenshot> screenshots = null;
	
	@JsonProperty("result")
	public String result;
	

}