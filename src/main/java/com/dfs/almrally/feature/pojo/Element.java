package com.dfs.almrally.feature.pojo;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"comments",
					"examples",
					"line",
					"name",
					"description",
					"id",
					"type",
					"keyword",
					"steps",
					"tags"
				})
public class Element {

	@JsonProperty("comments")
	@JsonIgnore
	public List<Comment> comments;
	
	@JsonProperty("examples")
	public List<Example> examples = null;
	
	@JsonProperty("line")
	public Integer line;
	
	@JsonProperty("name")
	public String name;

	@JsonProperty("description")
	public String description;
	
	@JsonProperty("id")
	public String id;
	
	@JsonProperty("type")
	public String type;
	
	@JsonProperty("keyword")
	public String keyword;
	
	@JsonProperty("steps")
	public List<Step> steps = new ArrayList<>();
	
	@JsonProperty("tags")
	public List<Tag> tags = new ArrayList<>();

}

