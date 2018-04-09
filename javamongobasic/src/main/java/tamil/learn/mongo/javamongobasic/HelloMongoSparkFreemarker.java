/**
 * @author Murugan_Nagarajan
 * @date Apr 3, 2018
 */
package tamil.learn.mongo.javamongobasic;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

/**
 * 
 */
public class HelloMongoSparkFreemarker {
	static Logger logger = LoggerFactory.getLogger(HelloMongoSparkFreemarker.class);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		logger.info("Starting the Spark-Mongo-Freemarker application instance");

		//Freemarker Template Configuration
		Configuration config = new Configuration(Configuration.VERSION_2_3_27);
		config.setClassForTemplateLoading(HelloFreemarker.class, "/");
		
		//Mongodb Connection
		MongoClient client = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
		MongoDatabase db = client.getDatabase("employee");
		final MongoCollection<Document> docCollection = db.getCollection("hello");
		docCollection.drop();
		docCollection.insertOne(new Document("name", "MURUGAN NAGARAJAN - Mongo"));
		
		//Spark Request Handler
		Spark.get("/hellomongo", handleRequest(config, docCollection));
	}
	
	private static Route handleRequest(Configuration config,MongoCollection<Document> docCollection) {
		Route helloRoute = new Route() {
			@Override
			public Object handle(Request request, Response response) throws Exception {
				StringWriter sw = new StringWriter();				
				try {					
					Template helloTemplate = config.getTemplate("hello.ftl");
					helloTemplate.process(docCollection.find().first(), sw);
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
