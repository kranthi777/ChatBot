/**
 * 
 */
package code.swiggy.RestService.RequestReceiver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import code.swiggy.RestService.HelperLibs.GetItemFromDynamoDb;

/**
 * @author Ashish Verma
 *
 */
public class OrderManager {
	private static Logger logger = LogManager.getLogger("OrderManager");
	public  String provideResponse(String sessionId,String msg)
	{
		String response;
		String orderStatus="initial";
		GetItemFromDynamoDb item= new GetItemFromDynamoDb();
		try
		{
		JSONObject order= new JSONObject(item.getItemByHash(sessionId,"Session"));
		orderStatus=order.getString("orderStatus");
		}
		catch(Exception e)
		{
			orderStatus="initial";
		}
		switch(orderStatus)
		{
		case "initial":
			response = doIntialOrderProcessing(sessionId,msg);
			break;
		case "order_construction":
			response = doOrderConstructionProcessing(sessionId,msg);
         	break;
		case "order_completion":
			response = doOrderCompletion(sessionId,msg);
			 break;
		case "payment_processing":
			response = doPayment(sessionId,msg);
			 break;
		case "order_confirmation":
			response = confirmOrder(sessionId,msg);
		     break;
		}
		return null;
	}
	public String doIntialOrderProcessing(String sessionId,String msg)
	{
	logger.info("doing initial processing");
		return "Hi Human!";
	}
    public String doOrderConstructionProcessing(String sessionId,String msg)
    {
    	return "Hi Human!";
    }
    public String doOrderCompletion(String sessionId,String msg)
    {
    	return "Hi Human!";
    }
    public String doPayment(String sessionId,String msg)
    {
    	return "Hi Human!";
    }
    public String confirmOrder(String sessionId,String msg)
    {
    	return "Hi Human!";
    }
}
