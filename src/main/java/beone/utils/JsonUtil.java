package beone.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {
	public static String toJson(Object obj)
	  {
	    GsonBuilder builder = new GsonBuilder();
	    builder.setPrettyPrinting();
	    Gson gson = builder.create();
	    return gson.toJson(obj);
	  }

	
	
}
