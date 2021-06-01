package afterMid;
import java.io.IOException;
import java.net.*;
import java.io.*;

public class URLConnectionReader {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		URL site = new URL("https://www.naver.com");

		URLConnection con = site.openConnection();
		
		InputStream stream = con.getInputStream();
		
		InputStreamReader isreader = new InputStreamReader(stream);
		
		BufferedReader reader = new BufferedReader(isreader);
		String line = reader.readLine();
		
		while((line = reader.readLine())!=null) {
			System.out.println(line);
		}
	}

}
