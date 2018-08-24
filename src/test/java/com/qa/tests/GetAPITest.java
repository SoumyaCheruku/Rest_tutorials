package com.qa.tests;

import java.io.IOException;

import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;

public class GetAPITest extends TestBase {
	TestBase testBase;
	String serviceUrl;
	String apiUrl;
	String url;
	RestClient restClient;
	CloseableHttpResponse closeableHttpResponse;
	
	
	@BeforeMethod
	public void setUp() throws ClientProtocolException, IOException{
		
		testBase=new TestBase();
		serviceUrl = prop.getProperty("URL");
		apiUrl = prop.getProperty("serviceURL");
		
		url = serviceUrl + apiUrl;
		
	}
	
	@Test(priority = 1)
	public void getAPITestWithoutHeaders() throws ClientProtocolException, IOException{
		
		restClient = new RestClient();
		closeableHttpResponse = restClient.get(url);
		//a.Status Code:
				int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
				System.out.println("Status code --->" + statusCode);
				
				Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200,"Status code is not 200");
				
				//b. JSON String:
				String responseString =  EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");//to get the response body
				JSONObject responseJsonOject = new JSONObject(responseString); //To get Json object response
				System.out.println("Respose JSON API --->" + responseJsonOject);
				
				//1. Single value Assertion
				//Per_page :
				String perpageValue = TestUtil.getValueByJPath(responseJsonOject,"/per_page");
				System.out.println("value of per page is this --->" + perpageValue);
				
				Assert.assertEquals(Integer.parseInt(perpageValue), 3,"per page value not matched");
				
				//Total: 
				String totalValue = TestUtil.getValueByJPath(responseJsonOject,"/total");
				System.out.println("value of total is this --->" + totalValue);
				
				Assert.assertEquals(Integer.parseInt(totalValue), 12,"total value not matched");
				
				//get the value JSON Array
				String lastName = TestUtil.getValueByJPath(responseJsonOject, "/data[0]/last_name");
				String id = TestUtil.getValueByJPath(responseJsonOject, "/data[0]/id");
				String avatar = TestUtil.getValueByJPath(responseJsonOject, "/data[0]/avatar");
				String firstName = TestUtil.getValueByJPath(responseJsonOject, "/data[0]/first_name");
				
				System.out.println(lastName);
				System.out.println(id);
				System.out.println(avatar);
				System.out.println(firstName);				
				
				//c.All Headers
				Header[] headersArray  = closeableHttpResponse.getAllHeaders();
				HashMap<String, String> allHeaders = new HashMap<String, String>();
				for(Header header : headersArray){
					allHeaders.put(header.getName(), header.getValue());			
				}
				
				System.out.println("Headers Array ---->" + allHeaders);
			
		
		
	}
	
	@Test(priority=2)
	public void getAPITestWithHeaders() throws ClientProtocolException, IOException{
		
		restClient = new RestClient();
		
		HashMap<String,String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
//		headerMap.put("username", "test@amazon.com");
//		headerMap.put("password", "test213");
//		headerMap.put("Auth Token", "12345");
		
		closeableHttpResponse = restClient.get(url);
		//a.Status Code:
				int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
				System.out.println("Status code --->" + statusCode);
				
				Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200,"Status code is not 200");
				
				//b. JSON String:
				String responseString =  EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");//to get the response body
				JSONObject responseJsonOject = new JSONObject(responseString); //To get Json object response
				System.out.println("Respose JSON API --->" + responseJsonOject);
				
				//1. Single value Assertion
				//Per_page :
				String perpageValue = TestUtil.getValueByJPath(responseJsonOject,"/per_page");
				System.out.println("value of per page is this --->" + perpageValue);
				
				Assert.assertEquals(Integer.parseInt(perpageValue), 3,"per page value not matched");
				
				//Total: 
				String totalValue = TestUtil.getValueByJPath(responseJsonOject,"/total");
				System.out.println("value of total is this --->" + totalValue);
				
				Assert.assertEquals(Integer.parseInt(totalValue), 12,"total value not matched");
				
				//get the value JSON Array
				String lastName = TestUtil.getValueByJPath(responseJsonOject, "/data[0]/last_name");
				String id = TestUtil.getValueByJPath(responseJsonOject, "/data[0]/id");
				String avatar = TestUtil.getValueByJPath(responseJsonOject, "/data[0]/avatar");
				String firstName = TestUtil.getValueByJPath(responseJsonOject, "/data[0]/first_name");
				
				System.out.println(lastName);
				System.out.println(id);
				System.out.println(avatar);
				System.out.println(firstName);				
				
				//c.All Headers
				Header[] headersArray  = closeableHttpResponse.getAllHeaders();
				HashMap<String, String> allHeaders = new HashMap<String, String>();
				for(Header header : headersArray){
					allHeaders.put(header.getName(), header.getValue());			
				}
				
				System.out.println("Headers Array ---->" + allHeaders);
			
		
		
	}


}
