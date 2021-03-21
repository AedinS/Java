//package ch12;


import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Translator_JSON extends JFrame {

	private JButton converter;
	private JButton canceler;
	private final String client_ID = "yRpjSQsuNmyiGmSlcdlD";
	private final String client_PS = "cSIZxfzduX";
	
	private JTextArea textIn;
	private JTextArea textOut; 
	
	public Translator_JSON() {
		this.setTitle("텍스트 변환");
		
		textIn = new JTextArea(10,14);
		textOut = new JTextArea(10,14);
		textIn.setLineWrap(true); // 줄바꿈
		textOut.setLineWrap(true);
		textOut.setEnabled(false); // 편집 false
		
		JPanel textAreaPanel = new JPanel(new GridLayout(1,2,20,20));
		textAreaPanel.add(textIn);
		textAreaPanel.add(textOut);
		
		converter = new JButton("반환");
		canceler = new JButton("취소");
		converter.addActionListener(new ButtonActionListener());
		canceler.addActionListener(new ButtonActionListener());
		
		JPanel buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.add(converter);
		buttonPanel.add(canceler);
		
		JPanel mainPanel = new JPanel(new BorderLayout(10,10));
		mainPanel.add(BorderLayout.CENTER, textAreaPanel);
		mainPanel.add(BorderLayout.SOUTH, buttonPanel);
		
		setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
		add(mainPanel);
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private class ButtonActionListener implements  ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == converter) {
				String str = textIn.getText();
				textOut.setText(""); // 결과창 초기화
				String result = toEnglish(str); // input 창의 텍스트들을 구해 toEnglish()에 넣은 후 result에 삽
				textOut.append(result); // 결과창에 result를 append
			}
			if (e.getSource() == canceler) {
				textOut.setText("");
			}
		}
		
		private String toEnglish(String korean) {

			String response = null;
			String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
	        String text;
	        
	        JSONParser jsonParser = new JSONParser();
	        
	        try {
	            text = URLEncoder.encode(korean, "UTF-8");
	        } catch (UnsupportedEncodingException e) {
	            throw new RuntimeException("인코딩 실패", e);
	        }
	        

	        Map<String, String> requestHeaders = new HashMap<>();
	        requestHeaders.put("X-Naver-Client-Id", client_ID);
	        requestHeaders.put("X-Naver-Client-Secret", client_PS);

	        response = post(apiURL, requestHeaders, text);
	        
	        try {
	        	JSONObject obj = (JSONObject) jsonParser.parse(response);
	        	JSONObject msg = (JSONObject) obj.get("message");
	        	JSONObject result = (JSONObject) msg.get("result");
	        	String retmessage = result.get("translatedText").toString();
	        	
	        	return retmessage;
	        	
	        } catch (ParseException e) {
	        	e.printStackTrace();
	        }
	        
	        return response;

		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Translator_JSON t = new Translator_JSON();
	}

	private static String post(String apiUrl, Map<String, String> requestHeaders, String text){
        HttpURLConnection con = connect(apiUrl);
        String postParams = "source=ko&target=en&text=" + text; //원본언어: 한국어 (ko) -> 목적언어: 영어 (en)
        try {
            con.setRequestMethod("POST");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            con.setDoOutput(true);
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.write(postParams.getBytes());
                wr.flush();
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 응답
                return readBody(con.getInputStream());
            } else {  // 에러 응답
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }

    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }

    private static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }
}
