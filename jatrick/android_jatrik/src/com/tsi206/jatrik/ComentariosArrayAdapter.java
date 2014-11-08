package com.tsi206.jatrik;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ComentariosArrayAdapter extends ArrayAdapter<String> {

	private final Context context;
	private final ArrayList<String> values;
	private final ArrayList<String> ids;
	
	public ComentariosArrayAdapter(Context context, ArrayList<String> arrayComentarios, ArrayList<String> arrayMinutos) {
		super(context, R.layout.list_comentarios, arrayComentarios);
		this.context = context;
		this.values = arrayComentarios;
		this.ids = arrayMinutos;
	}
	
	@SuppressLint("ViewHolder")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View v = inflater.inflate(R.layout.list_comentarios, parent, false);
		TextView labelTextView = (TextView) v.findViewById(R.id.comentario);
		labelTextView.setText(values.get(position));
		
		TextView idTextView = (TextView) v.findViewById(R.id.minutoComentario);
		idTextView.setText(ids.get(position));
		
		return v;
	}

}
