/**
 * @author Murugan_Nagarajan
 * @date Mar 28, 2018
 */
package tamil.learn.mongo.javamongobasic;

import static spark.Spark.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HelloSpark {
	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger(HelloSpark.class);
		logger.info("Starting the Spark application instance");
		get("/hello", (req, res) -> "Hello Murugan, This is Spark");
	}
}
