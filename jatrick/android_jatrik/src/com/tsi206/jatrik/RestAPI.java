package com.tsi206.jatrik;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;

public class RestAPI extends AsyncTask<String, String, String> {

	@Override
    protected String doInBackground(String... params) {
 
		String opcion = params[0];
    	String urlString = params[1]; // URL to call
	    
	    System.out.print(urlString);
	    
	    switch (opcion) {
		case "GetEquipo":
			return callGetEquipoManager(urlString);
		case "GetPartidos":
			return callGetPartidosManager(urlString);
		default:
			break;
		}	    
	    return "";
    }
 
    private String callGetEquipoManager(String urlString) {
    	String result = "";	    
	    HttpClient httpclient = new DefaultHttpClient();  
	    HttpGet request = new HttpGet(urlString);
	    try {
			//HttpResponse response = httpclient.execute(request);
		    ResponseHandler<String> handler = new BasicResponseHandler();
		    result =  httpclient.execute(request, handler);
		    System.out.println(result);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
	    return result;		
	}
    
    private String callGetPartidosManager(String urlString) {
    	String result = "";	    
	    HttpClient httpclient = new DefaultHttpClient();  
	    HttpGet request = new HttpGet(urlString);
	    try {
			//HttpResponse response = httpclient.execute(request);
		    ResponseHandler<String> handler = new BasicResponseHandler();
		    result =  httpclient.execute(request, handler);
		    System.out.println(result);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
	    return result;		
	}

	protected void onPostExecute(String result) {
 
    }
}
