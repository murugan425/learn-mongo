/**
 * @author Murugan_Nagarajan
 * @date Mar 31, 2018
 */
package tamil.learn.mongo.javamongobasic;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static tamil.learn.mongo.javamongobasic.utilities.PrintDocument.*;
/**
 * 
 */
public class HelloMongoDocument {
	static Logger logger = LoggerFactory.getLogger(HelloMongoDocument.class);
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {		
		logger.info("Starting the app instance to try mongodb documents");
		//Created a empty document
		Document doc = new Document();
		//Adding different type of objects along with a key attribute.
		//doc.append(key, value);
		doc.append("name", "Murugan");
		doc.append("age", 30).append("interest", 10.05);
		doc.append("long", 857495L);
		doc.append("bflag", true).append("empty", null);
		doc.append("objectKeyId", new ObjectId());
		doc.append("embeddeddoc", 
				new Document("p1", "tamil").append("p2", "teju"));	
		
		//Then use this return type and define the lambda expression
		//Like implementing a interface class
		SimpleLambda ageCalcLambda = year ->  2018 - year;
		
		//Then pass this lambda variable inside the code expression
		logger.info("Your age is :" + ageCalcLambda.getAge(1987));
		logger.info("Your age is :" + returnAge(1987));
		
		logger.info(printJson(doc).getWriter().toString());
		//TODO - Implement printJson functionality as a Lambda expression
	}
		
	//Replace this method with Lambda expression
	public static int returnAge(int yearOfBirth) {
		int age = 2018 - yearOfBirth;
		logger.info("Your age is :" + age);
		return age;
	};
	
	//Functional interface created for defining Fucntional/LambdaType
	interface SimpleLambda {
		int getAge(int year); //Only return & input matters not the name
	}
	
	
}


