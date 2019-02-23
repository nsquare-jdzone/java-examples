package com.javadeveloperzone;


import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonStreamingJacksonExample {

    public static final String DOC_LANGUAGE = "docLanguage";
    public static final String PARENT_DOC_ID = "parentDocId";
    public static final String DOC_TITLE = "docTitle";
    public static final String IS_PARENT = "isParent";
    public static final String DOC_AUTHOR = "docAuthor";
    public static final String DOC_TYPE = "docType";
    public static final String DOCUMENT_ID = "documentId";

    public static void main(String[] args) {
        JsonStreamingJacksonExample jsonStreamingJacksonExample = new JsonStreamingJacksonExample();
        String jsonFilePath = "H:\\Work\\Data\\sample.json";
        jsonStreamingJacksonExample.process(jsonFilePath);
    }

    public void process(String jsonFilePath){
        File jsonFile = new File(jsonFilePath);
        JsonFactory jsonfactory = new JsonFactory(); //init factory
        try {
            int numberOfRecords = 0;
            JsonParser jsonParser = jsonfactory.createJsonParser(jsonFile); //create JSON parser
            Document document = new Document();
            JsonToken jsonToken = jsonParser.nextToken();
            while (jsonToken!= JsonToken.END_ARRAY){ //Iterate all elements of array
                String fieldname = jsonParser.getCurrentName(); //get current name of token
                if (DOCUMENT_ID.equals(fieldname)) {
                    jsonToken = jsonParser.nextToken(); //read next token
                    document.setDocumentId(Integer.parseInt(jsonParser.getText()));
                }

                if (DOC_TYPE.equals(fieldname)) {
                    jsonToken = jsonParser.nextToken();
                    document.setDocType(jsonParser.getText());
                }

                if (DOC_AUTHOR.equals(fieldname)) {
                    jsonToken = jsonParser.nextToken();
                    document.setDocAuthor(jsonParser.getText());
                }

                if (DOC_TITLE.equals(fieldname)) {
                    jsonToken = jsonParser.nextToken();
                    document.setDocTitle(jsonParser.getText());
                }
                if (IS_PARENT.equals(fieldname)) {
                    jsonToken = jsonParser.nextToken();
                    document.setParent(jsonParser.getBooleanValue());
                }
                if (PARENT_DOC_ID.equals(fieldname)) {
                    jsonToken = jsonParser.nextToken();
                    document.setParentDocId(jsonParser.getIntValue());
                }

                if (DOC_LANGUAGE.equals(fieldname)) {  //array type field
                    jsonToken = jsonParser.nextToken();
                    List<String> docLangs = new ArrayList<>(); //read all elements and store into list
                    while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                        docLangs.add(jsonParser.getText());
                    }
                    document.setDocLanguage(docLangs);
                }
                if(jsonToken==JsonToken.END_OBJECT){

                    //do some processing, Indexing, saving in DB etc..
                    document = new Document();
                    numberOfRecords++;
                }
                jsonToken = jsonParser.nextToken();
            }

            System.out.println("Total Records Found : "+numberOfRecords);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
