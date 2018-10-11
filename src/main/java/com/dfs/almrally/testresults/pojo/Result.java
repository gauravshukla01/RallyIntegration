package com.dfs.almrally.testresults.pojo;


import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

		@JsonInclude(JsonInclude.Include.NON_NULL)
		@JsonPropertyOrder({
		"name",
		"testSteps",
		"userStory",
		"featureTag",
		"title",
		"description",
		"tags",
		"startTime",
		"duration",
		"sessionId",
		"driver",
		"dataTable",
		"manual",
		"testSource",
		"result",
		"versions"
		})
		
		
public class Result {

	@JsonProperty("name")
	public String name;
	
	@JsonProperty("testSteps")
	public List<TestStep> testSteps = null;
	
	@JsonProperty("userStory")
	public UserStory userStory;
	
	@JsonProperty("featureTag")
	public FeatureTag featureTag;
	
	@JsonProperty("title")
	public String title;
	
	@JsonProperty("description")
	public String description;
	
	@JsonProperty("tags")
	public List<Tag> tags = null;
	
	@JsonProperty("startTime")
	public double startTime;
	
	@JsonProperty("duration")
	public Integer duration;
	
	@JsonProperty("sessionId")
	public String sessionId;
	
	@JsonProperty("driver")
	public String driver;
	
	@JsonProperty("dataTable")
	public DataTable dataTable;

	@JsonProperty("manual")
	public Boolean manual;
	
	@JsonProperty("testSource")
	public String testSource;
	
	@JsonProperty("result")
	public String results;
	
	@JsonProperty("versions")
	public List<String> versions = null;
}