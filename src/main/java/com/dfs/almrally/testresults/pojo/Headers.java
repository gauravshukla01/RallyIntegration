package com.dfs.almrally.testresults.pojo;
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

public class Headers {

		@JsonProperty("number")
		public Integer number;
		
		@JsonProperty("description")
		public String description;
		
}
