package com.kr4.kr4lib.utils;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Typeface;
import android.widget.TextView;

public class KR4Utils {

	private static Pattern pattern;
	private static Matcher matcher;

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	static {
		pattern = Pattern.compile(EMAIL_PATTERN);
	}

	public static String formatDoubleAsCurrency(Double d) {
		String currencyString = NumberFormat.getCurrencyInstance(Locale.US)
				.format(d);
		currencyString = currencyString.replace(".00", "");
		return currencyString;
	}

	public static boolean isValidEmail(String address) {
		if (address == null || address.trim().equals("")) {
			return false;
		}
		matcher = pattern.matcher(address);
		return matcher.matches();
	}

	public static boolean intentIsAvailable(Context ctx, Intent intent) {
		final PackageManager mgr = ctx.getPackageManager();
		List<ResolveInfo> list = mgr.queryIntentActivities(intent,
				PackageManager.MATCH_DEFAULT_ONLY);
		return list.size() > 0;
	}

	public static String getOrdinalSuffix(int value) {
		int hunRem = value % 100;
		int tenRem = value % 10;

		if (hunRem - tenRem == 10) {
			return "th";
		}
		switch (tenRem) {
		case 1:
			return "st";
		case 2:
			return "nd";
		case 3:
			return "rd";
		default:
			return "th";
		}
	}

	public static void applyCustomFont(Context context, TextView textView,
			String fontPath) {
		if (fontPath.endsWith(".ttf") == false) {
			fontPath = fontPath + ".ttf";
		}
		Typeface font = Typeface.createFromAsset(context.getAssets(), fontPath);
		textView.setTypeface(font);
	}

}
