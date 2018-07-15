/**
 * 
 */
package code.swiggy.RestService.RequestReceiver;

import java.util.Random;

/**
 * @author Ashish Verma
 *
 */
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import code.swiggy.RestService.HelperLibs.GetItemFromDynamoDb;
import code.swiggy.RestService.HelperLibs.SetItemToDynamoDB;

@Path("session")
public class ChatFront {
	private static Logger logger = LogManager.getLogger("chatFront");
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("create")
	public Response createSessionForWeb(@Context HttpHeaders httpHeaders,String request) throws Exception {
		JSONObject input = new JSONObject(request);
		logger.info("input received is"+input);
		JSONObject output = new JSONObject();
		String userId = input.getString("userId");
		String sessionId=getSessionId();
		output.put("userId", userId);
		output.put("sessionId", sessionId);
		output.put("orderStatus","initial");
		SetItemToDynamoDB setItem = new SetItemToDynamoDB();
		setItem.setItemByHash(sessionId, output, "Sessions");
		OrderManager OM = new OrderManager();
		String processedResponse = OM.provideResponse(sessionId,null);
		return Response.status(200).entity(processedResponse).build();
		
	}
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("getResponse")
	public Response receiveMessage(@Context HttpHeaders httpHeaders,String request) throws Exception {
		JSONObject input = new JSONObject(request);
		GetItemFromDynamoDb item= new GetItemFromDynamoDb();
		String sessionId=input.getString("sessionId");
		String msg=input.getString("msg");
		OrderManager OM = new OrderManager();
		String processedResponse = OM.provideResponse(sessionId,msg);
		return Response.status(200).entity(processedResponse).build();
		
	}
	public String getSessionId()
	{
		int leftLimit = 97; // letter 'a'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 10;
	    Random random = new Random();
	    StringBuilder buffer = new StringBuilder(targetStringLength);
	    for (int i = 0; i < targetStringLength; i++) {
	        int randomLimitedInt = leftLimit + (int) 
	          (random.nextFloat() * (rightLimit - leftLimit + 1));
	        buffer.append((char) randomLimitedInt);
	    }
	    String generatedString = buffer.toString();
	    return generatedString;
	}
}
