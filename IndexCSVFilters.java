package com.solr.index;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.TimeZone;

public class IndexCSVFilters {

    private SolrClient solrClient;
    private static final TimeZone UTC = TimeZone.getTimeZone("UTC");
    private SimpleDateFormat solrCompatibleSdf,csvCompatibleSdf;
    private static final Logger logger = LoggerFactory.getLogger(IndexCSVFilters.class);

    private static final String TIME_STAMP = "T00:00:00Z";
    private static final String DATE_STAMP = "1970-01-01T";
    private static final String DATE_TIME_FIELD = "DateTime";
    private static final String TIME_FIELD = "Time";
    private static final String ID_FIELD = "id";
    private static final String DATE_PART_Z = "Z";
//    private static final String DATA_SET_NAME_FIELD = "DataSetName";
    public IndexCSVFilters(String solrUrl, String coreName){
        String urlString = solrUrl+"/"+coreName;
        solrClient = new HttpSolrClient.Builder(urlString)
                .withSocketTimeout(0)
                .withConnectionTimeout(0)
                .build();
        solrCompatibleSdf = new SimpleDateFormat("YYYY-MM-dd'T'HH:mm:ss'Z'");
        solrCompatibleSdf.setTimeZone(UTC);
        csvCompatibleSdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
        csvCompatibleSdf.setTimeZone(UTC);
    }
    public boolean indexCSVFile(File file){

        logger.info("Indexing Started for file : "+file.getName());
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {

            String line = null;
            line = bufferedReader.readLine();
            String sep = ";";
            if(line.contains(",")){
                sep = ",";
            }
            String fieldNames[] = line.split(sep);
            Collection<SolrInputDocument> docList = new ArrayList<>();
//            DataSetName,TimeStamp,First,Max,Min,Last,ValB,ValA,FilterS,FilterE,FilterT,ValS,ValC
//            E8374H231J#Type0,2017-01-09 09:31:00,66.2,71,66.2,71,66.2,71,35,2017-01-13,C,103.42,0
//            E8374H231J#Type0,2017-01-09 09:32:00,66.2,71,66.2,71,66.2,71,35,2017-01-13,C,103.57,0
            int cnt =0;
            int lineCnt =0;
            while ((line = bufferedReader.readLine())!=null){
                lineCnt++;
                if(line.trim().length()==0){
                    continue;
                }
                String data [] = line.trim().split(sep);
                if(data.length!=fieldNames.length){
                    System.out.println(lineCnt+"\t"+line);
                    continue;
                }
                SolrInputDocument solrInputDocument = new SolrInputDocument();
                for(int index = 0;index<fieldNames.length;index++){

                    if(index==1 || index==9){
                        continue;
                    }
                    solrInputDocument.addField(fieldNames[index],data[index]);
                }
                String time = data[1].substring(data[1].indexOf(" ")+1);

                solrInputDocument.addField(TIME_FIELD,DATE_STAMP+time+ DATE_PART_Z);
                solrInputDocument.addField(DATE_TIME_FIELD,data[1].replace(" ","T")+ DATE_PART_Z);
                solrInputDocument.addField(fieldNames[9],data[9]+TIME_STAMP);
                solrInputDocument.addField(ID_FIELD,data[1].replace(" ","_")+"_"+data[0]+"_"+data[8]+"_"+data[9]+"_"+data[10]);
                docList.add(solrInputDocument);

                if(cnt==100000){
                    cnt = 0;
                    solrClient.add(docList);
                    solrClient.commit();
                    docList.clear();
                }else{
                    cnt++;
                }
            }
            if(cnt!=0){
                solrClient.add(docList);
                solrClient.commit();
                docList.clear();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            logger.error("Error in Indexing file : "+file.getName(),e);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Error in Indexing file : "+file.getName(),e);
        } catch (SolrServerException e) {
            e.printStackTrace();
            logger.error("Error in Indexing file : "+file.getName(),e);
        }
        logger.info("Indexing Completed for file : "+file.getName());
        return true;
    }

    public void optimizeIndex(){
        try {
            logger.info("Index Optimization Process Start");
            solrClient.commit();
            solrClient.optimize();
            logger.info("Index Optimization Process Completed");
        } catch (SolrServerException e) {
            e.printStackTrace();
            logger.error("Error in Index optimization process",e);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Error in Index optimization process",e);
        }

    }

}
