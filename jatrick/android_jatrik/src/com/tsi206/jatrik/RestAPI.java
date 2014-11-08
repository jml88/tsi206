package com.tsi206.jatrik;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.ProgressDialog;
import android.os.AsyncTask;

public class RestAPI extends AsyncTask<String, String, String> {

	ProgressDialog progress;
	
	public RestAPI(ProgressDialog progress) {
	    this.progress = progress;
	  }
	
	public void onPreExecute() {
	    progress.show();
	  }
	
	@Override
    protected String doInBackground(String... params) {
 
		String opcion = params[0];
    	String urlString = params[1]; // URL to call
	    
	    System.out.print(urlString);
	    
	    switch (opcion) {
	    case "Login":
			return login(urlString);
		case "GetEquipo":
			return callGetEquipoManager(urlString);
		case "GetPartidos":
			return callGetPartidosManager(urlString);
		case "GetComentariosPartido":
			return callGetComentariosPartido(urlString);
		case "GetResultadoPartido":
			return callGetResultadoPartido(urlString);
		default:
			break;
		}	    
	    return "";
    }
	
	private String login(String urlString){
		try {
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));
			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}
	 
			conn.disconnect();
			
			return "LoginOK";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "LoginFAIL";
		}
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
    
    private String callGetComentariosPartido(String urlString) {
		
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
    
    private String callGetResultadoPartido(String urlString) {
		
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
		progress.dismiss();
    }
}
