package com.kr4.kr4lib.listrows;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

public interface ListRow {
	public View getView(Activity activity, int position, View convertView, ViewGroup parent);
	public String getSortName();
}
