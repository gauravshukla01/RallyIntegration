package com.dfs.almrally.feature.pojo;


import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({	"rows",
						"comments",
						"line",
						"name",
						"keyword"
					})
public class Step {
	
	@JsonProperty("comments")
	@JsonIgnore
	public List<Comment> comments;
	
	@JsonProperty("rows")
	public List<Row> rows=null;
	
	@JsonProperty("line")
	public Integer line;

	@JsonProperty("name")
	public String name;
	
	@JsonProperty("keyword")
	public String keyword;
}
