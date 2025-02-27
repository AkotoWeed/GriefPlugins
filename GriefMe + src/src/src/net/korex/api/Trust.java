package net.korex.api;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;

public class Trust {

	private static File file;
	private static YamlConfiguration cfg;

	static {
		file = getFile();
		cfg = getCfg();
	}

	public static void add(String name) {
		List<String> l = getTrusted();

		if (!l.contains(name)) {
			l.add(name);
			cfg.set("trusted", l);
			saveCfg(cfg);
		}

	}

	private static List<String> getTrusted() {
		List<String> l = cfg.getStringList("trusted");
		if (l == null || l.size() == 0) {
			l = new ArrayList<>();
		}
		return l;
	}

	private static YamlConfiguration getCfg() {
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		return cfg;
	}

	private static File getFile() {
		return new File("logs" + File.separator + "trusted.yml");
	}

	private static void saveCfg(YamlConfiguration cfg) {
		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean contains(String name) {
		return getTrusted().contains(name);
	}

}
