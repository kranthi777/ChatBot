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
	AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
			.withRegion(Regions.US_EAST_2)
			.build(); 
	DynamoDB dynamoDB = new DynamoDB(client);
	Table table = dynamoDB.getTable("ProductCatalog");
	Item item = table.getItem("Id", 101);
}