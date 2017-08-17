package com.vip.sensitive;

import java.io.UnsupportedEncodingException;

public class CharUtils {
	@SuppressWarnings("unused")
	public static String toUtf8(String str){ 
		if (str ==null) {
			return null;
		} else {
			try {
				return new String(str.getBytes("UTF-8"),"UTF-8");
			} catch (UnsupportedEncodingException e) {			
				e.printStackTrace();
				return null;
			}
		}      
	}
}
