package mx.hnust.sellvegetable.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IsCellPhone {

	public IsCellPhone() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 验证电话号码格式是否正确
	 * @param str
	 * @return
	 */
	
	 public static boolean isCellphone(String str) {
		 Pattern pattern = Pattern.compile("1[0-9]{10}");
		 Matcher matcher = pattern.matcher(str); 
		 if (matcher.matches()) {
		 return true;
		 }else {
		 return false;
		 }  
		    }
	 
}
