package example;

import jdk.incubator.http.HttpClient;
import jdk.incubator.http.HttpRequest;
import jdk.incubator.http.HttpResponse;

import javax.net.ssl.SSLParameters;
import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created by nitin on 4/9/2017.
 */
public class HttpApiDemo {

    public void saveHttpResponseAsFile(){
        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://javadeveloperzone.com/java-basic/java-9-features/java-9-module-example/"))
                    .GET()
                    .build();

            //String body handler
            HttpResponse<String> strResponse = client.send(request, HttpResponse.BodyHandler.asString());
            //Path tempFile = Files.createFile("test", ".html");
            Path tempFile = Paths.get("G:\\study\\Blogs\\Applications\\Sample.html");

            HttpResponse<Path> response = client.send(request, HttpResponse.BodyHandler.asFile(tempFile));

            System.out.println(response.statusCode());
            //System.out.println(response.body());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getHttpResponseAsString(){
        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://javadeveloperzone.com/java-basic/java-9-features/java-9-module-example/"))
                    .GET()
                    .build();

            //String body handler
            HttpResponse<String> strResponse = client.send(request, HttpResponse.BodyHandler.asString());

            System.out.println(strResponse.statusCode());
            SSLParameters sslParameters = strResponse.sslParameters();
            System.out.println("Maximum packet size : "+sslParameters.getMaximumPacketSize());

            //System.out.println(response.body());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendAsynchronousRequest(){
        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://javadeveloperzone.com/java-basic/java-9-features/java-9-module-example/"))
                    .GET()
                    .build();

            //String body handler
            CompletableFuture<HttpResponse<String>> strResponse = client.sendAsync(request, HttpResponse.BodyHandler.asString());

            Thread.sleep(200);
            if(strResponse.isDone()){
                System.out.println(strResponse.get().statusCode());
            }else{
                System.out.println("Request take more than 200 millisecons...");
                strResponse.cancel(true);
                if(strResponse.isCancelled()){
                    System.out.println("request cancelled !!!");
                }
            }
            //System.out.println(response.body());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void setBasicAuth(){
        try {
            HttpClient client = HttpClient.newBuilder()
                    .authenticator(new Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication("username", "password".toCharArray());
                        }
                    })
                    .build();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://javadeveloperzone.com/java-basic/java-9-features/java-9-module-example/"))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandler.asString());
            System.out.println(response.statusCode());
            System.out.println(response.body());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {

        HttpApiDemo apiDemo = new HttpApiDemo();
        apiDemo.sendAsynchronousRequest();

    }
}
