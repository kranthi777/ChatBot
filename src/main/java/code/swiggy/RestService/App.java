package code.swiggy.RestService;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.management.ObjectName;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.NetworkListener;
import org.glassfish.grizzly.threadpool.GrizzlyExecutorService;
import org.glassfish.grizzly.threadpool.ThreadPoolConfig;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import com.netflix.servo.publish.BasicMetricFilter;
import com.netflix.servo.publish.JmxConnector;
import com.netflix.servo.publish.JmxMetricPoller;
import com.netflix.servo.publish.JvmMetricPoller;
import com.netflix.servo.publish.LocalJmxConnector;
import com.netflix.servo.publish.MetricObserver;
import com.netflix.servo.publish.MetricPoller;
import com.netflix.servo.publish.PollRunnable;
import com.netflix.servo.publish.PollScheduler;
import com.netflix.servo.publish.graphite.GraphiteMetricObserver;



/**
 * Hello world!
 *
 */
public class App {
	/// Base URI the Grizzly HTTP server will listen on
	public static final String BASE_URI = "http://0.0.0.0:5021/";
	private static Logger logger = LogManager.getLogger("service");

	/**
	 * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
	 * @return Grizzly HTTP server.
	 * @throws IOException 
	 */
	public static HttpServer startServer() throws IOException {

		final ResourceConfig rc = new ResourceConfig().packages("code.swiggy.RestService");

		// create and start a new instance of grizzly http server
		// exposing the Jersey application at BASE_URI
		HttpServer server = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
		//reconfigure the thread pool
		NetworkListener listener = server.getListeners().iterator().next();
		ThreadPoolConfig config2 = listener.getTransport().getWorkerThreadPoolConfig();
		config2.setCorePoolSize(10);
		config2.setMaxPoolSize(300);
		GrizzlyExecutorService threadPool = (GrizzlyExecutorService) listener.getTransport().getWorkerThreadPool();
		threadPool.reconfigure(config2);
		return server;
	}

	/**
	 * Main method.
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		
		final HttpServer server = startServer();
		server.start();
		logger.info(String.format("Jersey app started with WADL available at "
				+ "%sapplication.wadl\nHit enter to stop it...", BASE_URI));

	}
	

}
