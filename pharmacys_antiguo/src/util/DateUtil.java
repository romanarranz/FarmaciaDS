package util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DateUtil {
	
	public static String getCurrentDateTime(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");		
		java.util.Date date = new java.util.Date();
		
		return dateFormat.format(date);
	}
	
	public static java.sql.Date toSQLDate(String date){
		java.util.Date myDate = null;
		java.sql.Date result = null;
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
     	try {
     		myDate = formatter.parse(date);
     	}
     	catch (Exception e) {
     		e.printStackTrace();
     	}
     	result = new java.sql.Date(myDate.getTime());
     	
     	return result;
	}
	
	public static java.util.Date toUtilDate(String date){
		java.util.Date myDate = null;
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		try{
			myDate = formatter.parse(date);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return myDate;
	}
}