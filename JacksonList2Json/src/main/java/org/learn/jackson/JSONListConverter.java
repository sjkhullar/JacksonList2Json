package org.learn.jackson;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JSONListConverter 
{
    public static void main( String[] args ) throws IOException
    {
    	ObjectMapper objectMapper = new ObjectMapper();
    	//Set pretty printing of json
    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

    	//Define map which will be converted to JSON
    	List<Person> personList = Stream.of(
				new Person("Mike", "harvey", 34),
			  	new Person("Nick", "young", 75),
				new Person("Jack", "slater", 21 ),
				new Person("gary", "hudson", 55))
				.collect(Collectors.toList());
    	
    	//1. Convert List Person to JSON
    	String arrayToJson = objectMapper.writeValueAsString(personList);
    	System.out.println("1. Convert List of person objects to JSON :");
    	System.out.println(arrayToJson);
    	
    	//2. JSON to Map
    	//Define Custom Type reference for List<Person> type
    	TypeReference<List<Person>> mapType = new TypeReference<List<Person>>() {};
    	List<Person> jsonToPersonList = objectMapper.readValue(arrayToJson, mapType);
    	System.out.println("\n2. Convert JSON to List of person objects :");
    	
    	//Print list of person objects output using Java 8
    	jsonToPersonList.forEach(System.out::println);
    }
}
