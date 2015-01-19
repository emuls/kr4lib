package com.kr4.kr4lib.adapters;

import java.util.List;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.kr4.kr4lib.listrows.ListRow;

public class ListRowAdapter extends BaseAdapter{

	protected List<ListRow> rows;
	protected Activity context;
	
	public ListRowAdapter(Activity context){
		this.context = context;
	}
	
	@Override
	public int getCount() {
		return rows == null ? 0 : rows.size();
	}

	@Override
	public ListRow getItem(int position) {
		if(rows.size()>position){
			return rows.get(position);
		}else{
			return null;
		}
		
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return getItem(position).getView(context, position, convertView, parent);
	}

	public List<ListRow> getRows() {
		return rows;
	}

	public void setRows(List<ListRow> rows) {
		this.rows = rows;
		notifyDataSetChanged();
	}

}
