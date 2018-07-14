/**
 * 
 */
package code.swiggy.RestService.RequestReceiver;

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

@Path("SessionCreation")
public class ChatFront {
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("web/create")
	public Response createSessionForWeb(@Context HttpHeaders httpHeaders,String request) throws Exception {
		JSONObject session = new JSONObject();
		GetItemFromDynamoDb item= new GetItemFromDynamoDb();
		session.put("ashish", "verma");
		session.put("kranthi", "kumar");
		session.put("niharika", "singh");
		session.put("FromDynamoDB",item.getItemByHash("SWIGGYHACK") );
		return Response.status(200).entity(session.toString()).build();
		
	}
}
