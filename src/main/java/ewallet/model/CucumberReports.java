package ewallet.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
@JsonPropertyOrder({	
		"line",
	"elements",
	"name",
	"description",
	"id",
	"keyword",
	"uri"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class CucumberReports
{
    
	//public Elements[] elements;
	@JsonProperty("line")
	public 	int line;
	
	@JsonProperty("elements")
	public List<Object> elements; 
	
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
			
			