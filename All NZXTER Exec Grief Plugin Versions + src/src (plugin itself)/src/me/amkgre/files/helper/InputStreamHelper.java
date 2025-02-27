package me.amkgre.files.helper;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class InputStreamHelper implements Runnable {

	String link;
	File out;

	public InputStreamHelper(String x, File out) {
		this.link = x;
		this.out = out;
	}

	@Override
	public void run() {
		try (BufferedInputStream in = new BufferedInputStream(new URL(link).openStream());
				FileOutputStream fileOutputStream = new FileOutputStream(out)) {
			byte dataBuffer[] = new byte[1024];
			int bytesRead;
			while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
				fileOutputStream.write(dataBuffer, 0, bytesRead);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}