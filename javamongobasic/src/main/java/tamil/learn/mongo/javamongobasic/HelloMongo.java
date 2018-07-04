/**
 * @author Murugan_Nagarajan
 * @date Mar 30, 2018
 */
package tamil.learn.mongo.javamongobasic;

import static java.util.Arrays.*;

import org.bson.BsonDocument;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class HelloMongo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger(HelloSpark.class);
		logger.info("Starting the app instance to test local mongodb connection");
		
		//Create a connection to single db address
		MongoClient client1 = new MongoClient("localhost", 27017);	
		//Create a connection to  multiple db addresses as a list of server address (primary, replicas, etc..)
		MongoClient client2 = new MongoClient(asList(new ServerAddress("localhost", 27017)));
		//Create a connection using connection URL
		MongoClient client3 = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
		//Create a connection configuring different client options 
		MongoClientOptions clientOptions = MongoClientOptions.builder().connectTimeout(10000).build();
		MongoClient client4 = new MongoClient(new ServerAddress("localhost", 27017), clientOptions);
		
		client4.listDatabaseNames().iterator().forEachRemaining(dbName -> logger.info("DB Names: "+ dbName));

		MongoDatabase db = client4.getDatabase("test");
		db.listCollectionNames().iterator().forEachRemaining(collectionName -> logger.info("Test DB Collection: "+ collectionName));
		
		client4.listDatabaseNames().iterator().forEachRemaining(
			dbName -> {logger.info("\n===============\nDB: " + dbName);
						client4.getDatabase(dbName).listCollectionNames().iterator().forEachRemaining(
								collectionName -> logger.info("COLLECTION: "+ collectionName));}
		);
		
		MongoCollection<BsonDocument> bsonCollection = db.getCollection("test", BsonDocument.class);
		MongoCollection<Document> docCollection = db.getCollection("test");
	}
}
