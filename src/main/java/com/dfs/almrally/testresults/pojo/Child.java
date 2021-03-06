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
	
public class Child {

	@JsonProperty("number")
	public Integer number;
	
	@JsonProperty("description")
	public String description;
	
	@JsonProperty("duration")
	public Integer duration;
	
	@JsonProperty("startTime")
	public double startTime;
	
	@JsonProperty("screenshots")
	public List<Snapshot> screenshots = null;
	
	@JsonProperty("result")
	public String result;
	
	@JsonProperty("children")
	public List<NestedChild> children = null;
	
}
