package com.dfs.almrally.testresults.pojo;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

		@JsonInclude(JsonInclude.Include.NON_NULL)
		@JsonPropertyOrder({
		"values",
		"result"
		})
		
public class Rows{

		@JsonProperty("values")
		public List<String> values = null;
		
		@JsonProperty("result")
		public String result;

}