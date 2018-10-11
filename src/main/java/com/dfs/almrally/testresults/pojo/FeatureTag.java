package com.dfs.almrally.testresults.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonPropertyOrder({
	"name",
	"type"
	})
	
	
public class FeatureTag {

	@JsonProperty("name")
	public String name;
	
	@JsonProperty("type")
	public String type;

}