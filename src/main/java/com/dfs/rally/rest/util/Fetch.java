package com.dfs.rally.rest.util;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * <b>Description:</b> Represents a list of fields to be returned in responses from the WSAPI.<br>
 * <b>Methods:</b> toString <br>
 * <b>Date:</b> August 24 2017
 * 
 *
 */
public class Fetch extends ArrayList<String> {

	

	/**
     *<b>Description:</b> Create a new fetch with the specified fields.
     * 
     * 
     */
    public Fetch(String... fetch) {
        this.addAll(Arrays.asList(fetch));    
    }

    /**
     *<b>Description:</b> Get the comma separated list of fields to be returned.<br>
     * If the list is empty true will be returned.
     * 
     * @return the comma separated list of fields to be returned
     */
    @Override
    public String toString() {
        if (size() == 0) {
            return "true";
        } else {
            StringBuilder s = new StringBuilder();
            for (String f : this) {
                s.append(f);
                s.append(",");
            }
            s.deleteCharAt(s.length() - 1);
            return s.toString();
        }
    }
}
