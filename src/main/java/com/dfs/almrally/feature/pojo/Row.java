package com.dfs.almrally.feature.pojo;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({	"comments",
						"cells",
						"line",
						"id"
					})
public class Row {
	
	@JsonProperty("comments")
	@JsonIgnore
	public List<Comment> comments;
	
	@JsonProperty("cells")
	public List<String> cells;
	
	@JsonProperty("line")
	public Integer line;
	
	@JsonProperty("id")
	public String id;

}
