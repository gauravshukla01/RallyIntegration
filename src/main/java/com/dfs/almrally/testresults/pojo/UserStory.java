package com.dfs.almrally.testresults.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonPropertyOrder({
	"id",
	"storyName",
	"path",
	"type"
	})
	
public class UserStory {

	@JsonProperty("id")
	public String id;
	
	@JsonProperty("storyName")
	public String storyName;
	
	@JsonProperty("path")
	public String path;
	
	@JsonProperty("type")
	public String type;

}
