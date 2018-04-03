/**
 * @author Murugan_Nagarajan
 * @date Apr 2, 2018
 */
package tamil.learn.mongo.javamongobasic.utilities;

import java.io.StringWriter;

import org.bson.Document;
import org.bson.codecs.DocumentCodec;
import org.bson.codecs.EncoderContext;
import org.bson.json.JsonMode;
import org.bson.json.JsonWriter;
import org.bson.json.JsonWriterSettings;

/**
 * 
 */
public class PrintDocument {

	public static JsonWriter printJson(Document document) {
        JsonWriter jsonWriter = new JsonWriter(new StringWriter(),
                                               new JsonWriterSettings(JsonMode.SHELL, true));
        new DocumentCodec().encode(jsonWriter, document, 
        		EncoderContext.builder().isEncodingCollectibleDocument(true).build());
        return jsonWriter;
    }
}
