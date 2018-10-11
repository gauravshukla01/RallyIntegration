package com.dfs.almrally.feature.pojo;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({	"comments",
						"tags",
						"line",
						"elements",
						"name",
						"description",
						"id",
						"keyword",
						"uri"
					})
public class Feature {
	
	@JsonProperty("comments")
	@JsonIgnore
	public List<Comment> comments;
	
	@JsonProperty("tags")
	public List<Tag> tags = new ArrayList<>();
	
	@JsonProperty("line")
	public 	int line;
	
	@JsonProperty("elements")
	public List<Element> elements;
	
	@JsonProperty("name")
	public String name;
	
	@JsonProperty("description")
	public String description;
	
	@JsonProperty("id")
	public String id;
	
	@JsonProperty("keyword")
	public String keyword;
	
	@JsonProperty("uri")
	public String uri;
}
