package com;

import model.fund;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/fund")
public class fundService {
	fund itemObj = new fund();

	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readfund() 
	 { 
	 return itemObj.readfund(); 
	 }
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertfund(@FormParam("name") String name, 
	 @FormParam("amount") String amount, 
	 @FormParam("pnumber") String pnumber, 
	 @FormParam("nic") String nic,
	@FormParam("city") String city,
	@FormParam("desc") String desc) 
	{ 
	 String output = itemObj.insertfund( name, amount, pnumber, nic , city, desc); 
	return output; 
	}
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updatefund(String itemData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject(); 
	//Read the values from the JSON object
	 String fundid = itemObject.get("fundid").getAsString(); 
	 String name = itemObject.get("name").getAsString(); 
	 String amount = itemObject.get("amount").getAsString(); 
	 String pnumber = itemObject.get("pnumber ").getAsString(); 
	 String nic = itemObject.get("nic").getAsString();
	 String city = itemObject.get("city ").getAsString(); 
	 String desc = itemObject.get("desc").getAsString();
	 String output = itemObj.updatefund(fundid,name, amount, pnumber, nic , city, desc ); 
	return output; 
	}
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deletefund(String itemData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(itemData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String fundid = doc.select("fundid").text(); 
	 String output = itemObj.deletefund(fundid); 
	return output; 
	}

}