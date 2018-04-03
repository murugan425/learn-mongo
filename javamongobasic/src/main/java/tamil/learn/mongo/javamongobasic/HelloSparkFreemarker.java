/**
 * @author Murugan_Nagarajan
 * @date Mar 28, 2018
 */
package tamil.learn.mongo.javamongobasic;

import spark.Spark.*;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class HelloSparkFreemarker {
	static Logger logger = LoggerFactory.getLogger(HelloSpark.class);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		logger.info("Starting the Spark-Freemarker application instance");
		Configuration config = new Configuration(Configuration.VERSION_2_3_27);
		config.setClassForTemplateLoading(HelloFreemarker.class, "/");
		
		Spark.get("/", (req, res) -> "Welcome To Spark & Freemarker lightweight framework");
		
		Spark.get("/hello", (request, response) -> {
			StringWriter writer = new StringWriter();
			try {
				Template helloTemplate = config.getTemplate("hello.ftl");
				Map<String, Object> helloMap = new HashMap<String, Object>();
				helloMap.put("name", "Murugan Nagarajan");
				helloTemplate.process(helloMap, writer);
			}
			catch (Exception e) {
				Spark.halt(500);
				logger.error(e.getMessage());
			}
			return writer;
		});
		
		Spark.get("/test", handleRequest(config));
	}
	
	private static Route handleRequest(Configuration config) {
		Route helloRoute = new Route() {
			@Override
			public Object handle(Request request, Response response) throws Exception {
				StringWriter sw = new StringWriter();				
				try {					
					Template helloTemplate = config.getTemplate("hello.ftl");
					Map<String, Object> helloMap = new HashMap<String, Object>();
					helloMap.put("name", "Test User");
					helloTemplate.process(helloMap, sw);
					logger.info(sw.toString());	
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
				return sw.toString();
			}			
		};
		return helloRoute;
	}
}
