import java.util.Map;
import java.util.ArrayList;
import java.util.List;

import org.cloudfoundry.runtime.env.CloudEnvironment;

import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.Database;
import com.cloudant.client.api.model.Response;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.codec.binary.Base64;



public class CloudantClientClass{

  private String urlStr;
  private String username;
  private String password;

  public CloudantClientClass(){

    try{
      setCredentials();
    }catch(Exception e){
      e.printStackTrace();
    }

  }

  public int addEntry(JSONObject entry) throws Exception{

    int result = 0;

    try{
      //create Cloudant client connection
      CloudantClient client = getClientConn();

      //get database. it will be autonamitcally created if not existing yet
      Database db = client.database("users", true);

      Response rs = db.save(entry);

      result = rs.getStatusCode();

    }catch(Exception e){

      e.printStackTrace();

    }

    return result;

  }


  public String getAll() throws Exception{

    String cred = username+":"+password;

    //encode username:password string for authentication
    String encodedCred = new String(Base64.encodeBase64(cred.getBytes()));
    String authorization = "Basic "+encodedCred;

    //accessing all the documents in the books database
    URL obj = new URL("https://"+urlStr+"/books/_all_docs?include_docs=true");
    HttpURLConnection con = (HttpURLConnection) obj.openConnection();

    //create a GET request with Authorization header
    con.setRequestMethod("GET");
    con.setRequestProperty("Authorization", authorization);
    con.setRequestProperty("Content-Type", "application/json");

    

    int responseCode = con.getResponseCode();

    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

    String inputLine;
    StringBuffer response = new StringBuffer();

    //getting the response from the server in json String format
    while((inputLine = in.readLine()) != null){
      response.append(inputLine);
    }

    in.close();

    return response.toString();

  }

  public void setCredentials() throws Exception{
    /*
    CloudEnvironment environment = new CloudEnvironment();
    if ( environment.getServiceDataByLabels("cloudantNoSQLDB").size() == 0 ) {
      throw new Exception( "No Cloudant service is bound to this app!!" );
    } 

    Map credential = (Map)((Map)environment.getServiceDataByLabels("cloudantNoSQLDB").get(0)).get( "credentials" );
    
    this.urlStr = (String)credential.get("host");
    this.username = (String)credential.get("username");
    this.password = (String)credential.get("password");
    */

    this.urlStr = "8c951f27-ca6d-451c-8e1e-114bc0be5ccf-bluemix.cloudant.com";
    this.username = "8c951f27-ca6d-451c-8e1e-114bc0be5ccf-bluemix";
    this.password = "a32368c9f093150f45a4e1ec7a5ba4cd054ab2ff550f13fcc5b55003a5502988";

  }


  protected CloudantClient getClientConn() throws Exception {

    /*
    CloudEnvironment environment = new CloudEnvironment();
    if ( environment.getServiceDataByLabels("cloudantNoSQLDB").size() == 0 ) {
      throw new Exception( "No Cloudant service is bound to this app!!" );
    } 

    Map credential = (Map)((Map)environment.getServiceDataByLabels("cloudantNoSQLDB").get(0)).get( "credentials" );

    CloudantClient client = (CloudantClient) ClientBuilder.account((String)credential.get("username"))
                                         .username((String)credential.get("username"))
                                         .password((String)credential.get("password"))
                                         .build();
    */

    CloudantClient client = (CloudantClient) ClientBuilder.account("8c951f27-ca6d-451c-8e1e-114bc0be5ccf-bluemix")
                                          .username("8c951f27-ca6d-451c-8e1e-114bc0be5ccf-bluemix")
                                          .password("a32368c9f093150f45a4e1ec7a5ba4cd054ab2ff550f13fcc5b55003a5502988")
                                          .build();
    return client;
  }

}