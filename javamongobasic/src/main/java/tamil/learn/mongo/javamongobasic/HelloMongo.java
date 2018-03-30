/**
 * @author Murugan_Nagarajan
 * @date Mar 30, 2018
 */
package tamil.learn.mongo.javamongobasic;

import org.bson.BsonDocument;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class HelloMongo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger(HelloSpark.class);
		logger.info("Starting the app instance to test local mongodb connection");
		
		MongoClient client = new MongoClient("localhost", 27017);		 
		MongoDatabase db = client.getDatabase("test");
		MongoCollection<BsonDocument> bsonCollection = db.getCollection("test", BsonDocument.class);
		MongoCollection<Document> docCollection = db.getCollection("test");
		logger.info(client.getDatabaseNames().toString());
	}

}
