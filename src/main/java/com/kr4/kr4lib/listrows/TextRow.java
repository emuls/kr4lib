package com.kr4.kr4lib.listrows;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kr4.kr4lib.R;

public class TextRow implements ListRow {

	protected String title;
	public int layoutID;

	public TextRow(String title) {
		this.title = title;
		layoutID = R.layout.listrow_text_row;
	}

	public TextRow(String title, int layoutID) {
		this.layoutID = layoutID;
	}

	@Override
	public View getView(Activity activity, int position, View convertView, ViewGroup parent) {
		View rowView = convertView;
		if (rowView == null || rowView.getTag() instanceof TextRow.ViewHolder == false) {
			LayoutInflater inflater = activity.getLayoutInflater();
			rowView = inflater.inflate(getLayoutID(), null);
			ViewHolder viewHolder = new ViewHolder();
			viewHolder.titleText = (TextView) rowView.findViewById(R.id.textRowText);
			rowView.setTag(viewHolder);
		}
		ViewHolder holder = (ViewHolder) rowView.getTag();
		holder.titleText.setText(title);
		return rowView;
	}

	public int getLayoutID() {
		return layoutID;
	}

	public void setLayoutID(int layoutID) {
		this.layoutID = layoutID;
	}

	final class ViewHolder {
		TextView titleText;
	}

	@Override
	public String getSortName() {
		return title;
	}
}
