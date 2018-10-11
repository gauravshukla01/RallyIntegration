package com.dfs.almrally.feature.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
						"comments",
						"line",
						"name",
						"description",
						"id",
						"rows",
						"keyword"
					})
public class Example {
	
	@JsonProperty("comments")
	@JsonIgnore
	public List<Comment> comments;
	
	@JsonProperty("line")
	public Integer line;

	@JsonProperty("rows")
	public List<Row> rows = null;
	
	@JsonProperty("name")
	public String name;
	
	@JsonProperty("description")
	public String description;
	
	@JsonProperty("id")
	public String id;
	
	@JsonProperty("keyword")
	public String keyword;
}