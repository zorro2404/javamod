package jsonbuilder;

import java.util.Properties;

import com.google.gson.Gson;

import CommunictionManager.ConfigMaster;

public class JsonUtil {

	private static Gson GsonControl= new Gson();
	
	
	public JsonUtil() {
		//this.GsonControl= new Gson();
	}
	
	
	
	public static Object ParsetoJson(String jsonStr, Class classToParse) {
		return  GsonControl.fromJson(jsonStr,classToParse);
	}
	
	
    //parseo a gson dado instancia 
	public String Parsetojson(tag taginstance) {
		return this.GsonControl.toJson(taginstance);
		
	} 
	
	public String ParseTagstoJson(Tags Instance) {
		return this.GsonControl.toJson(Instance);
	}

    public tag ParseFromJson(String JsonInput) {
    	return this.GsonControl.fromJson(JsonInput, tag.class);
    }
	
    
    public Properties GetProperties(String InJson) {
    	return this.GsonControl.fromJson(InJson, Properties.class);
    }
    
    
}
