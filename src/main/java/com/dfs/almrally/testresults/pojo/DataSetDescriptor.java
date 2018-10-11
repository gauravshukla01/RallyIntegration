package com.dfs.almrally.testresults.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

		@JsonInclude(JsonInclude.Include.NON_NULL)
		@JsonPropertyOrder({
		"startRow",
		"rowCount",
		"name",
		"description"
		})
		
		
public class DataSetDescriptor {

		@JsonProperty("startRow")
		public Integer startRow;
		
		@JsonProperty("rowCount")
		public Integer rowCount;
		
		@JsonProperty("name")
		public String name;
		
		@JsonProperty("description")
		public String description;

}
