package com.dfs.almrally.feature.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
					"line",
					"name"
					})
public class Tag {

	@JsonProperty("line")
	public Integer line;
	
	@JsonProperty("name")
	public String name;
}