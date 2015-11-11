package com.aturhelp;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

@Path("/test1")
public class Test1 {
	
	private Person person = new Person(1, "Sample", "Person", "sample_person@jerseyrest.com");

	  @GET
	  @Produces("application/json")
	  public Response convertFtoC() throws JSONException {

		JSONObject jsonObject = new JSONObject();
		Double fahrenheit = 98.24;
		Double celsius;
		celsius = (fahrenheit - 32)*5/9; 
		jsonObject.put("F Value", fahrenheit); 
		jsonObject.put("C Value", celsius);

		String result = "@Produces(\"application/json\") Output: \n\nF to D Converter Output: \n\n" + jsonObject;
		return Response.status(200).entity(result).build();
	  }

	  @Path("{f}")
	  @GET
	  @Produces("application/json")
	  public Response convertFtoCfromInput(@PathParam("f") float f) throws JSONException {

		JSONObject jsonObject = new JSONObject();
		float celsius;
		celsius =  (f - 32)*5/9; 
		jsonObject.put("F Value", f); 
		jsonObject.put("C Value", celsius);

		String result = "@Produces(\"application/json\") Output: \n\nF to C Converter Output: \n\n" + jsonObject;
		return Response.status(200).entity(result).build();
	  }
	  
	  @POST
	  @Path("sample")
	  @Consumes({ MediaType.APPLICATION_JSON })
		@Produces(MediaType.APPLICATION_JSON)
		public Person postPerson(Person person) {
		     System.out.println("test"+person.getFirstName());
		     System.out.println("test"+person.getLastName());
		     System.out.println("test"+person.getEmail());
		     return person;
		}
}