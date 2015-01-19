package com.kr4.kr4lib.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.lang.reflect.Type;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.google.gson.Gson;

public class FileUtil {
	public static String readStringFileFromDisk(String filename, Context context) {
		BufferedReader br = null;
		try {
			FileInputStream fis = context.openFileInput(filename);
			InputStreamReader isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			String scheduleString = "";
			String nextLine = null;
			while ((nextLine = br.readLine()) != null) {
				scheduleString += nextLine;
			}
			return scheduleString;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				br.close();
			} catch (Exception e) {
				// don't care
			}
		}
	}
	
	public static Object readJsonObjectFromDisk(String filename, Context context, Type type) {
		FileInputStream fis = null;
		InputStreamReader isr = null;
		try {
			fis = context.openFileInput(filename);
			isr = new InputStreamReader(fis);
			Gson gson = new Gson();
			Object result = gson.fromJson(isr, type);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				fis.close();
			} catch (Exception e) {
				// don't care
			}
			try {
				isr.close();
			} catch (Exception e) {
				// don't care
			}			
		}
	}	

	public static byte[] readBytesFromFile(String filename, Context context) {
		try {
			FileInputStream fis = context.openFileInput(filename);
			byte[] bytes = org.apache.commons.io.IOUtils.toByteArray(fis);
			return bytes;
		} catch (Exception e) {
			return null;
		}
	}

	public static void writeStringToDisk(String filename, Context context, String string) {
		FileOutputStream fos = null;
		PrintStream out = null;
		try {
			fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
			out = new PrintStream(fos);
			out.print(string);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (Exception e) {
				// don't care
			}
			try {
				fos.close();
			} catch (Exception e) {
				// don't care
			}
		}
	}

	public static void writeJsonObjectToDisk(String filename, Context context, Object jsonObject) {
		FileOutputStream fos = null;
		OutputStreamWriter writer = null;
		try {
			Gson gson = new Gson();
			fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
			writer = new OutputStreamWriter(fos);
			gson.toJson(jsonObject, writer);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (Exception e) {
				// don't care
			}
			try {
				fos.close();
			} catch (Exception e) {
				// don't care
			}
		}
	}

	public static void writeBytesToDisk(String filename, Context context, byte[] bytes) {
		FileOutputStream fos = null;
		try {
			fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
			fos.write(bytes);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close();
			} catch (Exception e) {
				// don't care
			}
		}
	}

	public static void writeBitmapToDisk(String filename, Context context, Bitmap bitmap) {
		FileOutputStream fos = null;
		try {
			fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close();
			} catch (Exception e) {
				// don't care
			}
		}
	}

	public static Bitmap readBitmapFromDisk(String filename) {
		Bitmap bitmap = BitmapFactory.decodeFile(filename);
		return bitmap;
	}

	public static void deleteFile(String myScheduleFilename, Context context) {
		context.deleteFile(myScheduleFilename);
	}
}
