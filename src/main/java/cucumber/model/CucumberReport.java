package cucumber.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class CucumberReport {

	
	public ArrayList<Object> elements; 
	public String line, name,description,id,keyword,uri;

	
}
