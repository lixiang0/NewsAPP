package pub.string.http.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
	public static String getMd5(String originStr){
		try {
		return	MessageDigest.getInstance("md5").digest(originStr.getBytes("utf-8")).toString();
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "error";		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
