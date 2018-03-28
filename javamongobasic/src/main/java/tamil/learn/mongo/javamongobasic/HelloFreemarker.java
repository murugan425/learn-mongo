/**
 * @author Murugan_Nagarajan
 * @date Mar 28, 2018
 */
package tamil.learn.mongo.javamongobasic;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class HelloFreemarker {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger(HelloFreemarker.class);
		Configuration config = new Configuration(Configuration.VERSION_2_3_27);
		config.setClassForTemplateLoading(HelloFreemarker.class, "/");
		
		try {
			Template helloTemplate = config.getTemplate("hello.ftl");
			StringWriter sw = new StringWriter(); 
			Map<String, Object> helloMap = new HashMap<String, Object>();
			helloMap.put("name", "Murugan Nagarajan");
			helloTemplate.process(helloMap, sw);
			logger.info(sw.toString());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

}
