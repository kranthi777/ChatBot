package code.swiggy.RestService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Root resource (exposed at "health" path)
 * program --- Checking for instance to be Inservice from load-balancer
 * @author ashish
 */

@Path("health")
public class Health {
	private static final Logger logger = LogManager.getLogger(Health.class);
	/**
	 * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
	 * @return healthy
	 */
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String health(){
		return "healthy";
	}
}
