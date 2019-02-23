package com.javadeveloperzone;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class JsonStreamingGsonExample {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        JsonStreamingGsonExample jsonStreamingGsonExample = new JsonStreamingGsonExample();
        String jsonFilePath = "H:\\Work\\Data\\sample.json";
        jsonStreamingGsonExample.parse(jsonFilePath);
        System.out.println("Total Time Taken : "+(System.currentTimeMillis() - start)/1000 + " secs");
    }

    public void parse(String jsonFilePath){

        //create JsonReader object and pass it the json file,json source or json text.
        try(JsonReader jsonReader = new JsonReader(
                new InputStreamReader(
                        new FileInputStream(jsonFilePath), StandardCharsets.UTF_8))) {

            Gson gson = new GsonBuilder().create();

            jsonReader.beginArray(); //start of json array
            int numberOfRecords = 0;
            while (jsonReader.hasNext()){ //next json array element
                Document document = gson.fromJson(jsonReader, Document.class);
                //do something real
//                System.out.println(document);
                numberOfRecords++;
            }
            jsonReader.endArray();
            System.out.println("Total Records Found : "+numberOfRecords);
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
