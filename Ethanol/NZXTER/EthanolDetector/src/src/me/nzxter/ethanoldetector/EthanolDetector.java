package me.nzxter.ethanoldetector;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

public class EthanolDetector {
	private static final String DARK_RED = "\033[31m";
	private static final String RESET = "\033[0m";
	private static int foundCount = 0;

	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("https://pastebin.com/raw/5P4XpkfS");
			System.out.println("\nETHANOL DETECTOR by NZXTER");
			System.out.println("\nhttps://discord.gg/RwYpk34nsC");
			System.out.printf("%n%sUSAGE: java -jar %s folder/file%s%n", DARK_RED,
					new File(EthanolDetector.class.getProtectionDomain().getCodeSource().getLocation().getFile())
							.getName(),
					RESET);
			return;
		} else {
			File path = new File(args[0]);
			if (path.isDirectory()) {
				searchDirectory(path);
			} else if (path.isFile() && path.getName().endsWith(".jar")) {
				searchJar(path);
			} else {
				System.out.println("The provided path is not a valid JAR file or directory.");
			}

			if (foundCount > 0) {
				System.out.printf(
						"%n--> (( %s%d%s )) <-- infected plugins found and moved into the 'infected' folder %n",
						DARK_RED, foundCount, RESET);
			}
			return;
		}
	}

	private static void searchDirectory(File directory) {
		File[] files = directory.listFiles();
		if (files != null) {
			for (File file : files) {
				if (file.isDirectory()) {
					searchDirectory(file);
				} else if (file.isFile() && file.getName().endsWith(".jar")) {
					searchJar(file);
				}
			}
		}
	}

	private static void searchJar(final File jarFile) {
		boolean foundInJar = false;

		try (ZipFile zipFile = new ZipFile(jarFile)) {
			Enumeration<? extends ZipEntry> entries = zipFile.entries();
			while (entries.hasMoreElements()) {
				ZipEntry entry = entries.nextElement();
				if (entry.getName().endsWith(".class")) {
					foundInJar |= processClassEntry(zipFile, entry, jarFile);
				}
			}
		} catch (IOException e) {
			System.err.println("Error reading JAR file: " + jarFile.getName());
			e.printStackTrace();
		}

		moveToInfectedFolder(jarFile, foundInJar);
	}

	private static void moveToInfectedFolder(File jarFile, boolean foundInJar) {
		if (foundInJar) {
			File infectedFolder = new File("infected");
			if (!infectedFolder.exists() && !infectedFolder.mkdir()) {
				System.err.println("Failed to create 'infected' folder.");
				return;
			}

			File destinationFile = new File(infectedFolder, jarFile.getName());
			try {
				Files.move(jarFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				System.err.println("Failed to move " + jarFile.getName() + " to 'infected' folder.");
				e.printStackTrace();
			}
		}
	}

	private static boolean processClassEntry(ZipFile zipFile, ZipEntry entry, File jarFile) {
		try {
			InputStream inputStream = zipFile.getInputStream(entry);
			ClassReader classReader = new ClassReader(inputStream);
			final boolean[] foundInClass = { false };

			classReader.accept(new ClassVisitor(589824) {
				@Override
				public MethodVisitor visitMethod(int access, String name, String descriptor, String signature,
						String[] exceptions) {
					checkForSignature(name, jarFile, entry, foundInClass);
					return super.visitMethod(access, name, descriptor, signature, exceptions);
				}

				@Override
				public FieldVisitor visitField(int access, String name, String descriptor, String signature,
						Object value) {
					checkForSignature(name, jarFile, entry, foundInClass);
					return super.visitField(access, name, descriptor, signature, value);
				}
			}, 0);

			inputStream.close();
			return foundInClass[0];
		} catch (IOException e) {
			System.err.println("Error processing class entry: " + entry.getName());
			e.printStackTrace();
			return false;
		}
	}

	private static void checkForSignature(String name, File jarFile, ZipEntry entry, boolean[] foundInJar) {
		if ("lambda$onEnable$$0".equals(name) || "clazz_44ee".equals(name)) {
			System.out.printf("%nFound in JAR: %s | Class: %s%n", jarFile.getName(), entry.getName());
			foundCount++;
			foundInJar[0] = true;
		}
	}

}
