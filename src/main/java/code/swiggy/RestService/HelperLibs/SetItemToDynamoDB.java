
package code.swiggy.RestService.HelperLibs;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.regions.Regions;
/**
 * @author Ashish Verma
 *
 */
import com.amazonaws.services.dynamodbv2.document.DynamoDB;

import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DeleteItemOutcome;
import com.amazonaws.services.dynamodbv2.document.UpdateItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.NameMap;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;

public class SetItemToDynamoDB {
	private static Logger logger = LogManager.getLogger("setItemToDynamoDB");
	AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
			.withRegion(Regions.US_EAST_2)
			.build(); 
	DynamoDB dynamoDB = new DynamoDB(client);
	
	public void setItemByHash(String itemKey, JSONObject thingsToPut, String tableName)
	{
	logger.info("putting data in to db");
	try {
	Table table = dynamoDB.getTable(tableName);
	Item item = new Item();
	Iterator<?> keys = thingsToPut.keys();

	while( keys.hasNext())
	{
	    String key = (String)keys.next();
	    try 
	    {
	      String value = thingsToPut.getString("key");
	      if(value.equalsIgnoreCase(itemKey))
	      {
	      item.withPrimaryKey(key,value);
	      }
	      else
	      {
	    	item.withString(key, value);
	       }
	    }
	    catch(Exception e)
	      {
	    	
	      }
     }
	  table.putItem(item);
	}
	catch(Exception e)
	{
		logger.info("error in inserting data to dynamodb",e);
	}
}
}

