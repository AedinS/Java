package afterMid;

import java.net.*;

public class Host2InetAddr {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String hostName = "www.naver.com";
		
		try {
			InetAddress address = InetAddress.getByName(hostName);
			System.out.println("IP 주소: " + address.getHostAddress());
		} catch(UnknownHostException e) {
			System.err.println(e.getMessage());
		}
	}

}
