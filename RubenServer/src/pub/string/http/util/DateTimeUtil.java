package pub.string.http.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateTimeUtil {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		
		String toTimeZone = "GMT";
		String fromTimeZone = "UTC";
		String stingvalue = "2014-10-14 03:05:39";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		dateFormat.setTimeZone(TimeZone.getTimeZone(fromTimeZone));
//		Date parsedDate = dateFormat.parse(stingvalue);
		dateFormat.setTimeZone(TimeZone.getTimeZone(toTimeZone));
//		String newDate = dateFormat.format(parsedDate);	
		 Date dNow = new Date( );
	      SimpleDateFormat ft = 
	      new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
	      System.out.println("Current Date: " + ft.format(dNow));	      
	      //Thu, 11 Aug 2016 00:33:30 GMT
	      String originalString = "Thu, 11 Aug 2016 00:33:30 GMT";
	      Date date = null;
		SimpleDateFormat sd = new SimpleDateFormat("E,dd MM yyyy HH:mm:ss");
		sd.setTimeZone(TimeZone.getTimeZone(toTimeZone));
//	sd.parse(originalString);
//	    LogUtil.show(date.toGMTString());	    
	    String inputText = "Thu, 11 Aug 2016 00:33:30 GMT";
        SimpleDateFormat inputFormat = new SimpleDateFormat
            ("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.ENGLISH);
        inputFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        SimpleDateFormat outputFormat =
            new SimpleDateFormat("hh时mm分");
        // Adjust locale and zone appropriately
        Date date1 = null;
		try {
			date1 = inputFormat.parse(inputText);
			  System.out.println(date1.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String outputText = outputFormat.format(date1);
        System.out.println(outputText);
	}

}
