package com.dfs.almrally.testresults.pojo;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

		@JsonInclude(JsonInclude.Include.NON_NULL)
		@JsonPropertyOrder({
		"headers",
		"rows",
		"predefinedRows",
		"dataSetDescriptors"
		})
		
		
public class DataTable {

	@JsonProperty("headers")
		public List<String> headers = null;
		
		@JsonProperty("rows")
		public List<Rows> rows = null;
		
		@JsonProperty("predefinedRows")
		public Boolean predefinedRows;
		
		@JsonProperty("dataSetDescriptors")
		public List<DataSetDescriptor> dataSetDescriptors = null;

}
