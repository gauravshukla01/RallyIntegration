package ewallet.model;

import java.util.ArrayList;
import java.util.List;

import com.dfs.almrally.feature.pojo.Tag;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
@JsonPropertyOrder({
	
	"line",
	"name",
	"description",
	"id",
	"type",
	"keyword",
	"steps",
	"tags","before"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Elements
{
	@JsonProperty("tags")
	public List<Tag> tags = new ArrayList<>();
	
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
	

	@JsonProperty("before")
	public List<Before> before = new ArrayList<>();


	@JsonProperty("steps")
	public List<Steps> steps = new ArrayList<>();

  

    
}
			
