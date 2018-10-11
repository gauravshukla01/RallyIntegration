package com.dfs.almrally.feature.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"line",
"value"
})
public class Comment {
@JsonProperty("line")
public Integer line;
@JsonProperty("value")
public String value;
}
