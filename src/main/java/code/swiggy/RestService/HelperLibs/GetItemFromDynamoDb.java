/**
 * 
 */
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

public class GetItemFromDynamoDb {
	private static Logger logger = LogManager.getLogger("GetItemFromDynamoDB");
	AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
			.withRegion(Regions.US_EAST_2)
			.build(); 
	DynamoDB dynamoDB = new DynamoDB(client);
	
	public String getItemByHash(String itemKey, String tableName)
	{
	Table table = dynamoDB.getTable(tableName);
	Item item = table.getItem("sessionId",itemKey);
	logger.info("putting data in dynamodb");
	return item.toJSON();
	}
}
