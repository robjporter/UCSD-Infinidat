package com.roporter.feature.format;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class myFormat {
	public static String humanReadableFromString(String strBytes, boolean si) {
		long bytes = Long.parseLong(strBytes);
	    int unit = si ? 1000 : 1024;
	    if (bytes < unit) return bytes + " B";
	    int exp = (int) (Math.log(bytes) / Math.log(unit));
	    String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp-1) + (si ? "" : "i");
	    return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
	}
	public static String humanReadableFromLong(long bytes, boolean si) {
	    int unit = si ? 1000 : 1024;
	    if (bytes < unit) return bytes + " B";
	    int exp = (int) (Math.log(bytes) / Math.log(unit));
	    String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp-1) + (si ? "" : "i");
	    return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
	}	
	public static int humanReadableIntFromLong(long bytes, boolean si) {
	    int unit = si ? 1000 : 1024;
	    if (bytes < unit) return safeLongToInt(bytes);
	    int exp = (int) (Math.log(bytes) / Math.log(unit));
	    return safeLongToInt(Math.round(bytes / Math.pow(unit, exp)));
	}
	public static String[] makeSmallerNumberRelativeToLargerWithMetric(long smaller, long larger) {
		if(smaller > 0 && larger > 0) {
			return makeSmallerNumberRelativeToLargerWithMetric(smaller,larger,true);
		} else {
			return new String[]{"1","1","N/A"};
		}
	}
	public static String[] makeSmallerNumberRelativeToLargerWithMetric(long value1, long value2, boolean si) {
        int pos1 = getMetricPosition(value1);
        int pos2 = getMetricPosition(value2);
		double format1 = removeNumbers(value1,pos1);
		double format2 = removeNumbers(value2,pos2);
        if(pos1 == pos2) {
        	return new String[]{String.valueOf(format1), String.valueOf(format2), getMetric(pos1)};
        //} else if( pos1 > pos2 ) {
        //	return matchValuesWithMetric(value2,value1, si);
        } else {
        	return matchValuesWithMetric(value1,value2, si);
        }
	}
	private static double removeNumbers(long value, int pos) {
		int precision = 3;
		int trail = (pos * 3) - precision;
		String tmp = String.valueOf(value);
		String tmp1 = tmp.substring(0, tmp.length()-trail);
		String tmp2 = tmp1.substring(0,tmp1.length()-precision);
		String tmp3 = tmp1.substring(tmp1.length()-precision,tmp1.length());
		return Double.parseDouble(tmp2 + "." + tmp3);
	}
	private static int getMetricPosition(long number) {
		System.out.println("getMetricPosition: " + number);
        int[] lengths = {4,7,10,13,16,19,22,25,28,31};
    	int length = getNumberLength(number);
        for( int i = 0; i < lengths.length; i++ ) {
        	if(length < lengths[i]) {
        		return i;
        	}
        }
        return 0;
	}
	private static int getNumberLength(long number) {
		return (int)(Math.log10(number)+1);
	}
	private static String getMetric(long number) {
        String[] dictionary = { "bytes", "KB", "MB", "GB", "TB", "PB", "EB", "ZB", "YB" };
		return dictionary[(int) number];
	}
	public static String[] matchValuesWithMetric(long smaller, long larger, boolean si) {
		String results[] = new String[3];
		String[] dictionary = { "bytes", "KB", "MB", "GB", "TB", "PB", "EB", "ZB", "YB" };
	    final int unit=si ? 1000 : 1024;
        int largerPos = getMetricPosition(larger);
        double result = Math.pow(unit, largerPos);
        double result1 = smaller/result;
        double result2 = larger/result;
		results[0] = String.valueOf(round(result1));
		results[1] = String.valueOf(round(result2));
		results[2] = dictionary[largerPos];
		return results;
	}
	public static double round(double value) {
		int precision = 3;
	    if (precision < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, precision);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	
	
	
	
	
	public static long reFormat(String size) {
        return reFormat(size, false);
    }
	public static long reFormat(String size, boolean si) {
        String[] dictionary = { "bytes", "KB", "MB", "GB", "TB", "PB", "EB", "ZB", "YB" };
		String elements[] = size.split(" ");
	    final int unit=si ? 1000 : 1024;
		int pos = Arrays.asList(dictionary).indexOf(elements[1].trim());

        int index = 0;
        double number = Double.parseDouble(elements[0].trim());
        double count = number * unit;
		for(index = 1; index < pos; index++) {
			count += count * unit;
		}
		return (long)count;
	}
	public static String format(double bytes, int digits) {
        return format(bytes, digits, false);
    }
	public static String format(double bytes, int digits, boolean si) {
        String[] dictionary = { "bytes", "KB", "MB", "GB", "TB", "PB", "EB", "ZB", "YB" };
	    final int unit=si ? 1000 : 1024;
        int index = 0;
        for (index = 0; index < dictionary.length; index++) {
            if (bytes < unit) {
                break;
            }
            bytes = bytes / unit;
        }
        return String.format("%." + digits + "f", bytes) + " " + dictionary[index];
    }
	public static long longFromHumanReadable(String unit, long vsize, boolean si) {
	    int units = si ? 1000 : 1024;
		switch(unit) {
		case "KB":
			return vsize * units;
		case "MB":
			return (vsize * units) * units;
		case "GB":
			return ((vsize * units) * units) * units;
		case "TB":
			return (((vsize * units) * units) * units) * units;
		case "PB":
			return ((((vsize * units) * units) * units) * units) * units;
		}
		return 0;
	}
	public static long longFromHumanReadable(String unit, String size, boolean si) {
	    int units = si ? 1000 : 1024;
		switch(unit) {
		case "KB":
			return safeStringToLong(size) * units;
		case "MB":
			return (safeStringToLong(size) * units) * units;
		case "GB":
			return ((safeStringToLong(size) * units) * units) * units;
		case "TB":
			return (((safeStringToLong(size) * units) * units) * units) * units;
		case "PB":
			return ((((safeStringToLong(size) * units) * units) * units) * units) * units;
		}
		return 0;
	}
	public static int safeLongToInt(long l) {
	    return (int) Math.max(Math.min(Integer.MAX_VALUE, l), Integer.MIN_VALUE);
	}
	public static int safeStringToInt(String s) {
		 try {
			 return Integer.valueOf(s);
	    } catch(NumberFormatException e) {
	         // return -1;
	         throw e;
	    }
	}
	public static Double safeStringToDouble(String d) {
		return Double.parseDouble(d);
	}
	public static String safeDoubleToString(double d) {
		return Double.toString(d);
	}
	public static String safeIntToString(int i) {
		return String.valueOf(i);
	}
	public static String safeLongToString(Long l) {
		return String.valueOf(l);
	}
	public static String safeBooleanToString(Boolean b) {
		return Boolean.toString(b);
	}
	public static Boolean safeStringToBoolean(String s) {
		if( s.equals( "ACTIVE" )) { return true; }
		else if( s.equals( "INACTIVE" )) { return false; }
		else { return Boolean.valueOf(s); }
	}
	public static long safeStringToLong(String s) {
		return Long.valueOf(s).longValue();
	}
	public static String stringToCamelCase(String s) {
		return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
	}
	public static String stringToUpperCase(String s) {
		return s.toUpperCase();
	}
	public static int getDateTimeDays(long uptime) {
		int days = safeLongToInt(uptime / (24 * 60 * 60 * 1000));
		return days;
	}
	public static int getDateTimeHours(long uptime) {
		uptime %= (24 * 60 * 60 * 1000);
		int hours = safeLongToInt(uptime / (60 * 60 * 1000));
		return hours;
	}
	public static int getDateTimeMinutes(long uptime) {
		uptime %= (24 * 60 * 60 * 1000);
		uptime %= (60 * 60 * 1000);
		int minutes = safeLongToInt(uptime / (60 * 1000));
		return minutes;
	}
	public static int getDateTimeSeconds(long uptime) {
		uptime %= (24 * 60 * 60 * 1000);
		uptime %= (60 * 60 * 1000);
		uptime %= (60 * 1000);
		int seconds = safeLongToInt(uptime / (1000));
		return seconds;
	}
	public static String getDateFromTimestamp(long time){
	    Date date = new Date(time);
	    Format format = new SimpleDateFormat("dd/MM/yyyy");
	    return format.format(date);
	}
	public static String getTimeFromTimestamp(long time){
	    Date date = new Date(time);
	    Format format = new SimpleDateFormat("HH:mm:ss");
	    return format.format(date);
	}
}
