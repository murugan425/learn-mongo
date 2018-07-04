/**
 * @author Murugan_Nagarajan
 * @date Jul 2, 2018
 */
package tamil.learn.mongo.javamongobasic;

import static tamil.learn.mongo.javamongobasic.utilities.PrintDocument.printJson;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.util.Arrays.*;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
/**
 * 
 */
public class MongoRecap {
	static Logger logger = LoggerFactory.getLogger(MongoRecap.class);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		crudMethods();
		queryMovies();
	}
	
	private static void crudMethods() {
		
		//SIMPLE INSERTS & QUERIES
		MongoClient mc = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
		mc.listDatabaseNames().iterator().forEachRemaining(dbName -> logger.info(dbName));
		
		MongoDatabase db = mc.getDatabase("recap");
		MongoCollection<Document> coll = db.getCollection("insert");
		coll.drop();
		db.listCollectionNames().iterator().forEachRemaining(collName -> logger.info(collName));
		logger.info(""+coll.count());
		
		Document docInsert1 = new Document("name", "Murugan").append("yob", 1987);
		Document docInsert2 = new Document("name", "Mani").append("yob", 1988);
		Document docInsert3 = new Document("name", "Venni").append("yob", 1987);
		
		coll.insertMany(asList(docInsert1,docInsert2,docInsert3));
		//logger.info(printJson(docInsert1).getWriter().toString());
		
		logger.info("FIRST DOCUMENT");
		logger.info(printJson(coll.find().first()).getWriter().toString());
		
		logger.info("GET ALL DOCUMENTS");
		List<Document> allDocs = coll.find().into(new ArrayList<Document>());
		allDocs.stream().forEach(doc -> logger.info(printJson(doc).getWriter().toString()));
		
		logger.info("GET THE TOTAL COUNT");
		logger.info(String.valueOf(coll.count()));
		
		logger.info("APPLY JAVA FILTER");
		Integer yearOfInterest = 1987;		
		allDocs.stream().filter(doc -> yearOfInterest.equals(doc.getInteger("yob"))).forEach(doc -> logger.info(printJson(doc).getWriter().toString()));
		
		logger.info("APPLY MONGO FILTER");
		Bson filterByYear1 = new Document().append("yob", 1987);
		
		Bson filterByYear2 = Filters.and(Filters.eq("name", "Murugan"), Filters.gte("yob", 1987)); 
		
		List<Document> allFilteredDocs = coll.find(filterByYear2).into(new ArrayList<Document>());
		allFilteredDocs.stream().forEach(doc -> logger.info(printJson(doc).getWriter().toString()));
			
	}

	private static void queryMovies() {

		//USING QUERIES WITH A DIFFERENT COLLECTION
		MongoClient mc = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
		MongoDatabase videodb = mc.getDatabase("video");
		MongoCollection<Document> movies = videodb.getCollection("movieDetails");
		List<Document> allMoviesDocs = movies.find().into(new ArrayList<Document>());
		logger.info(String.valueOf(movies.count()));
		
		//db.movieDetails.find({year:2009})
		List<Document> all2009Movies = movies.find(Filters.eq("year", 2009)).into(new ArrayList<Document>());
		all2009Movies.stream().forEach(doc -> logger.info(printJson(doc).getWriter().toString()));
		logger.info(String.valueOf(all2009Movies.size()));
		
	}
}
