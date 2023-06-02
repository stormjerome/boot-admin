package com.ywy.bootadmin.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***
 * 字符串工具类
 *
 * @author ywy
 * @date 2020/4/13
 */
public class StringUtil {
	private static Pattern linePattern = Pattern.compile("_(\\w)");
	private static Pattern humpPattern = Pattern.compile("[A-Z]");

	/**
	 * 首字母转大写
	 * @param str
	 * @return
	 */
	public static String toUpperCaseFirstChar(String str) {
		if (str == null || "".equals(str) || str.length() < 1) {
			return str;
		}
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	/**
	 * 首字母转小写
	 * @param str
	 * @return
	 */
	public static String toLowerCaseFirstChar(String str) {
		if (str == null || "".equals(str) || str.length() < 1) {
			return str;
		}
		return str.substring(0, 1).toLowerCase() + str.substring(1);
	}

	/**
	 * 下划线转驼峰
	 * @param str
	 * @return
	 */
	public static String lineToHump(String str) {
		// 正则表达式写法
		str = str.toLowerCase();
		Matcher matcher = linePattern.matcher(str);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
		}
		matcher.appendTail(sb);
		return sb.toString();
	}

	/**
	 * 驼峰转下划线
	 * @param str
	 * @return
	 */
	public static String humpToLine(String str) {
		// 简单写法，效率不高
//		return str.replaceAll("[A-Z]", "_$0").toLowerCase();

		// 正则表达式高效写法
		Matcher matcher = humpPattern.matcher(str);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
		}
		matcher.appendTail(sb);
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(lineToHump("user_name"));
	}
}