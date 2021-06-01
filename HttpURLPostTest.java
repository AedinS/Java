package afterMid;

import java.io.*;
import java.net.*;

public class HttpURLPostTest {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		String site = "https://localhost:8080/test";
		URL url = new URL(site);
		
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.setDoOutput(true);
		con.setRequestMethod("post");
		con.setRequestProperty("content-type", "application/x-www-fore-urlencoded");
		
		// id- scpark
		
		OutputStream stream = con.getOutputStream();
		
		OutputStreamWriter ouriter = new OutputStreamerItem(extream, "UTF-8");
		
		PrintWriter
	}

}
