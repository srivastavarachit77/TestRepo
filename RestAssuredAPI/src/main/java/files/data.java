package files;

import io.restassured.path.json.JsonPath;

public class data {

	public static String AddPlaceBody()
	{
		return "{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383499,\r\n"
				+ "    \"lng\": 33.427399\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 30,\r\n"
				+ "  \"name\": \"SEPT25_Name\",\r\n"
				+ "  \"phone_number\": \"(+91) 8888880925\",\r\n"
				+ "  \"address\": \"Street 0925\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"Monitor\",\r\n"
				+ "    \"Headset\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://sept25com\",\r\n"
				+ "  \"language\": \"English\"\r\n"
				+ "}\r\n"
				+ "";
	}
	
	public static JsonPath rawToJson(String response)
	{
		JsonPath js=new JsonPath(response);
		return js;
	}
}
