package com.roporter.feature.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeType;

public class myJson {
	// ROOT ELEMENT
	public static Map<String,String> getRootElementAsMapString(String json, String rootElement) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode node = objectMapper.readValue(json, JsonNode.class);
		JsonNode element = node.get(rootElement);
		Iterator<String> fieldNames = element.fieldNames();
		Map<String, String> result = new HashMap<String,String>();
        while (fieldNames.hasNext()) {
            String fieldName = fieldNames.next();
            result.put(fieldName, element.get(fieldName).asText());
        }
		return result;
	}
	public static String[] getRootElementAsArrayString(String json, String rootElement) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode node = objectMapper.readValue(json, JsonNode.class);
		JsonNode array = node.get(rootElement);
		String[] tmp = new String[array.size()];
		for( int i = 0; i < array.size(); i++ ) {
			tmp[i] = array.get(i).asText();
		}
		return tmp;
	}
	public static String getRootElementAsSingleString(String json, String rootElement) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode node = objectMapper.readValue(json, JsonNode.class);
		JsonNode array = node.get(rootElement);
		return array.asText();
	}
	
	// ELEMENT
	public static String getArrayElementAsSingleString(String json, String rootElement, int arraylocation, String needle) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode node = objectMapper.readValue(json, JsonNode.class);
		JsonNode array = node.get(rootElement);
		JsonNode element = array.get(arraylocation);
		JsonNode result = element.get(needle);
		return result.asText();
	}
	public static int getElementCountAsInt(String results, String location) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode node = objectMapper.readValue(results, JsonNode.class);
		String[] locationSplit = location.split("\\.");
		JsonNode element = node.get(locationSplit[0]);
		if( locationSplit.length > 1 ) {
			for( int i = 1; i < locationSplit.length; i++ ) {
				if( isInteger(locationSplit[i])) {
					element = element.get(Integer.parseInt(locationSplit[i]));
				} else {
					element = element.get(locationSplit[i]);
				}
			}
		}
		return element.size();
	}
	public static Map<String,String> getArrayElementAsMapString(String json, String rootElement, int location) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode node = objectMapper.readValue(json, JsonNode.class);
		Map<String,String> results = new HashMap<String,String>();
		String[] locationSplit = rootElement.split("\\.");
		JsonNode element = node.get(locationSplit[0]);
		for( int j = 1; j < locationSplit.length; j++ ) {
			element = element.get(locationSplit[j]);
		}
		element = element.get(location);
		Iterator<String> fieldNames = element.fieldNames();
        while (fieldNames.hasNext()) {
            String fieldName = fieldNames.next();
            if( element.get(fieldName).getNodeType() == JsonNodeType.ARRAY ) {
            	JsonNode tmp = element.get(fieldName);
            	List<String> arr = new ArrayList<String>();
                for( int i = 0; i < tmp.size(); i++ ) {
                	JsonNode tmp2 = tmp.get(i);
            		Iterator<String> fieldNames2 = tmp2.fieldNames();
        			Map<String,String> results2 = new HashMap<String,String>();
            		while (fieldNames2.hasNext()) {
                        String fieldName2 = fieldNames2.next();
                        results2.put(fieldName2, tmp2.get(fieldName2).asText());
            		}
            		arr.add(results2.toString());
                }
            	results.put( fieldName, arr.toString());
            } else {
            	results.put( fieldName, element.get(fieldName).asText());
            }
        }
		return results;
	}
	public static Map<String,String> getElementAsMapString(String json, String rootElement, String location) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode node = objectMapper.readValue(json, JsonNode.class);
		JsonNode array = node.get(rootElement);
		Map<String,String> results = new HashMap<String,String>();
		String[] locationSplit = location.split("\\.");
		JsonNode element = array.get(locationSplit[0]);
		if( locationSplit.length > 1) {
			for( int j = 1; j < locationSplit.length; j++ ) {
				element = element.get(locationSplit[j]);
			}
		}
		Iterator<String> fieldNames = element.fieldNames();
        while (fieldNames.hasNext()) {
            String fieldName = fieldNames.next();
            if( element.get(fieldName).getNodeType() == JsonNodeType.ARRAY ) {
            	JsonNode tmp = array.get(fieldName);
            	List<String> arr = new ArrayList<String>();
                for( int i = 0; i < tmp.size(); i++ ) {
                	JsonNode tmp2 = tmp.get(i);
            		Iterator<String> fieldNames2 = tmp2.fieldNames();
        			Map<String,String> results2 = new HashMap<String,String>();
            		while (fieldNames2.hasNext()) {
                        String fieldName2 = fieldNames2.next();
                        results2.put(fieldName2, tmp2.get(fieldName2).asText());
            		}
            		arr.add(results2.toString());
                }
            	results.put( fieldName, arr.toString());
            } else {
            	results.put( fieldName, element.get(fieldName).asText());
            }
        }
		return results;
	}
	public static List<String> getElementAsArrayString(String json, String rootElement, String needle ) throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode node = objectMapper.readValue(json, JsonNode.class);
		JsonNode array = node.get(rootElement);
		List<String> results = new ArrayList<String>();
		for( int i = 0; i < array.size(); i++ ) {
			JsonNode element = array.get(i);
			results.add(element.get(needle).asText());
		}
		return results;
	}
	public static Map<String, String> getMapFromElementList(List<String> subset2, int position) {
		Map<String, String> result = new HashMap<String,String>();
		if( subset2.size() >= position ) {
			String tmp = subset2.get(position);
			result = getSplitOfStringToMap(tmp);
		}
		return result;
	}
	
	// HELPER FUNCTIONS
	private static Map<String, String> getSplitOfStringToMap(String tmp) {
		Map<String, String> results = new HashMap<String,String>();
		String[] tmp1 = tmp.split(",");
		for( int i = 0; i < tmp1.length; i++ ) {
			tmp1[i] = tmp1[i].replaceAll("\\s+", "");
			String[] tmp2 = tmp1[i].split("=");
			results.put(tmp2[0], tmp2[1]);
		}
		return results;
	}

	public static List<String> getBreakArrayToElements(String string) {
		List<String> tmp = new ArrayList<String>();
		String[] subset2 = string.split("}");
		for( int i = 0; i < subset2.length; i++ ) {
			if(subset2[i].startsWith(", {")) {
				subset2[i] = subset2[i].substring(3);
			} else if( subset2[i].startsWith("[{")) {
				subset2[i] = subset2[i].substring(2);
			} else if( subset2[i].startsWith("]")) {
				subset2[i] = null;
			}
			if( subset2[i] != null ) {
				tmp.add(subset2[i]);
			}
		}
		return tmp;
	}
	public static Map<String,String> getExactArrayItemsAsMapString(String json, String rootElement, String location, String[] elements) throws JsonParseException, JsonMappingException, IOException {
		Map<String, String> results = new HashMap<String,String>();
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode node = objectMapper.readValue(json, JsonNode.class);
		JsonNode array = node.get(rootElement);
		String[] locationSplit = location.split("\\.");
		JsonNode element = array;

		if( locationSplit.length > 0 ) {
			for( int i = 0; i < locationSplit.length; i++ ) {
				if( i < locationSplit.length - 1 ) {
					if( isInteger(locationSplit[i])) {
						element = element.get(Integer.parseInt(locationSplit[i]));
					} else {
						element = element.get(locationSplit[i]);
					}
				} else if( i == locationSplit.length - 1 ) {
					if( isInteger(locationSplit[i])) {
						element = element.get(Integer.parseInt(locationSplit[i]));
					} else {
						element = element.get(locationSplit[i]);
					}
					for( int j = 0; j < elements.length; j++ ) {
						results.put(elements[j], element.get(elements[j]).asText());
					}
				}
			}
		}
		return results;
	}
	public static String getExactSingleItemAsString(String json, String rootElement, String location) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode node = objectMapper.readValue(json, JsonNode.class);
		JsonNode array = node.get(rootElement);
		String[] locationSplit = location.split("\\.");
		JsonNode element = array;
		String result = "";

		if( locationSplit.length > 1 ) {
			for( int i = 0; i < locationSplit.length; i++ ) {
				if( i < locationSplit.length - 1 ) {
					if( isInteger(locationSplit[i])) {
						element = element.get(Integer.parseInt(locationSplit[i]));
					} else {
						element = element.get(locationSplit[i]);
					}
				} else if( i == locationSplit.length - 1 ) {
					result = element.get(locationSplit[i]).asText();
				}
			}
		} else {
			result = array.get(locationSplit[0]).asText();
		}
		return result;
	}
	private static boolean isInteger(String string) {
		try{
			@SuppressWarnings("unused")
			int num = Integer.parseInt(string);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	public static String formatJSON(List<String> tmp) {
		String send = "";
		if(tmp.size() >1 ) {
        	send = "{";
            for( int i = 0; i < tmp.size() -1 ; i++) {
            	send += tmp.get(i) + ",";
            }
            send += tmp.get(tmp.size()-1);
            send += "}";
        } else if( tmp.size() == 1) {
        	send = "{" + tmp.get(0) + "}";
        }
		return send;
	}
	public static String getElementArrayAsString(String json, String rootElement, String location) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode node = objectMapper.readValue(json, JsonNode.class);
		JsonNode array = node.get(rootElement);
		String[] locationSplit = location.split("\\.");
		JsonNode element = array;
		String result = "";

		if( locationSplit.length > 1 ) {
			for( int i = 0; i < locationSplit.length; i++ ) {
				if( i < locationSplit.length - 1 ) {
					if( isInteger(locationSplit[i])) {
						element = element.get(Integer.parseInt(locationSplit[i]));
					} else {
						element = element.get(locationSplit[i]);
					}
				} else if( i == locationSplit.length - 1 ) {
					result = element.get(locationSplit[i]).toString();
				}
			}
		} else {
			result = array.get(locationSplit[0]).asText();
		}
		return result;
	}
}
