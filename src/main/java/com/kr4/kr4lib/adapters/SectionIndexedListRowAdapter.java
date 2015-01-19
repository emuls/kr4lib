package com.kr4.kr4lib.adapters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import android.app.Activity;
import android.util.Log;
import android.widget.SectionIndexer;

import com.kr4.kr4lib.listrows.ListRow;

public class SectionIndexedListRowAdapter extends ListRowAdapter implements SectionIndexer{
	HashMap<String, Integer> azIndexer;
    List<String> sections;	
	
	public SectionIndexedListRowAdapter(Activity context) {
		super(context);
	}
	
	public void setRows(List<ListRow> rows) {
		this.rows = rows;
		setupSections();
		notifyDataSetChanged();
	}
	
	private void setupSections() {
		azIndexer = new HashMap<String, Integer>();
		int size = rows.size();
        for (int i = size - 1; i >= 0; i--) {
            ListRow row = rows.get(i);
            //We store the first letter of the word, and its index.
            azIndexer.put(row.getSortName().substring(0, 1).toUpperCase(), i); 
        } 

        Set<String> keys = azIndexer.keySet(); // set of letters 

        Iterator<String> it = keys.iterator();
        sections = new ArrayList<String>(); 

        while (it.hasNext()) {
            String key = it.next();
            sections.add(key);
        }
        Collections.sort(sections);
        
	}	

	@Override
	public int getPositionForSection(int section) {
		if(azIndexer==null || sections == null){
			return 0;
		}
		if(section > sections.size()){
			Log.i("wtf?", "??? : " + section);
			return 0;
		}
		String letter = sections.get(section);
        return azIndexer.get(letter);
	}

	@Override
	public int getSectionForPosition(int position) {
		if(sections==null){
			return 0;
		}
		ListRow row = getItem(position);
		if(row==null){
			return 0;
		}else{
			String index = row.getSortName().substring(0,1).toUpperCase();
			return sections.indexOf(index);
		}
	}

	@Override
	public Object[] getSections() {
		if(sections==null){
			return null;
		}
		String[] result = new String[sections.size()];
		result = sections.toArray(result);
		return result;
	}	


}
