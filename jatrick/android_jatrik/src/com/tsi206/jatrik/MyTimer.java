package com.tsi206.jatrik;

import java.util.ArrayList;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

public class MyTimer extends TimerTask {
	
	private AsyncDelegate delegate;
	private ArrayList<String> data;
	private ArrayList<String> dataId;
	private String urlString;
	private int nroComentario;
	
	public MyTimer(AsyncDelegate delegate, ArrayList<String> data, ArrayList<String> dataId, String urlString) {
		//super();
		this.delegate = delegate;
		this.data = data;
		this.dataId = dataId;
		this.urlString = urlString;
		this.nroComentario = -1;
	}
	
	@Override
	public void run() {
		try {
			String result = (String) new UpdateListComentarios(delegate).execute(urlString, data, dataId, String.valueOf(nroComentario)).get();
			nroComentario = Integer.parseInt(result);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
