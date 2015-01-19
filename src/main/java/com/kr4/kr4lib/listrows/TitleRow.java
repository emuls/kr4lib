package com.kr4.kr4lib.listrows;

import com.kr4.kr4lib.R;

public class TitleRow extends TextRow{	
	
	public TitleRow(String title) {
		super(title);
	}

	public int getLayoutID() {
		return R.layout.listrow_title_row;
	}
}


