package com.tsi206.jatrik;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MinutoAminutoArrayAdapter extends ArrayAdapter<String> {

	private final Context context;
	private final ArrayList<String> values;
	private final ArrayList<String> ids;
	
	public MinutoAminutoArrayAdapter(Context context, ArrayList<String> arrayComentarios, ArrayList<String> arrayMinutos) {
		super(context, R.layout.list_minuto_a_minuto, arrayComentarios);
		this.context = context;
		this.values = arrayComentarios;
		this.ids = arrayMinutos;
	}
	
	@SuppressLint("ViewHolder")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View v = inflater.inflate(R.layout.list_minuto_a_minuto, parent, false);
		TextView labelTextView = (TextView) v.findViewById(R.id.comentarioMinutoAminuto);
		if (values != null && values.size() > 0)
			labelTextView.setText(values.get(position));
		
		TextView idTextView = (TextView) v.findViewById(R.id.minutoAminutoComentario);
		if(ids != null && ids.size() > 0)
			idTextView.setText(ids.get(position));
		
		return v;
	}
}
