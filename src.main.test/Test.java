
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/***测试专用
 * **/
public class Test {
	
	/**
	 * @param args
	 */
	public static void main(String[] args){
		String sql_date = "Wed Jan 15 21:08:49 CST 2020";
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			@SuppressWarnings("deprecation")
			java.util.Date time = new java.util.Date(sql_date);
			String date = sFormat.format(time);
			System.out.println(date);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
	}
	
	@org.junit.Test
	public void dateConvert() throws ParseException{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		String d = "2020-01-16";
		Date date = df.parse(d);
		System.out.println("date="+date);
		System.out.println(d.length());
		
	}

	
}
