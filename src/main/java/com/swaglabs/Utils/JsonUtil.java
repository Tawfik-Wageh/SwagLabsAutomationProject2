package com.swaglabs.Utils;

import com.jayway.jsonpath.JsonPath;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;


public class JsonUtil {

    private static final String JSON_FILE_PATH = "src/test/resources/";
    String jsonReader;
    String jsonFileName;


    // Constructor to initialize the JSON file name
    public JsonUtil(String jsonFileName) {
        this.jsonFileName = jsonFileName;
        try{
            JSONObject data = (JSONObject) new JSONParser().parse(new FileReader(JSON_FILE_PATH + jsonFileName + ".json") );
            jsonReader = data.toJSONString();
        } catch (Exception e) {
            LogsUtil.error("Error reading JSON file: " + e.getMessage());
        }
    }

    // Method to read JSON data using JsonPath
    public String getJsonData(String jsonPath){
        String testData = "";
        try {
            testData = JsonPath.read(jsonReader, jsonPath);
        } catch (Exception e) {
            LogsUtil.error( e.getMessage() , "No result found for the given JSON path: ' " + jsonPath + "'in the file: " + jsonFileName + ".json");
        }
        LogsUtil.info("json path: '" + jsonPath + "' in the file: " + jsonFileName + ".json" + " has value: " + testData);

        return testData;

    }

}
