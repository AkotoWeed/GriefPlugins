package net.inprogressing.System.utils;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.Arrays;

public class Rang {

    public static ArrayList<String> Range = new ArrayList<>();
    public static ArrayList<ArrayList<String>> RangCommands = new ArrayList<>();

    public static void read() {
        //Ränge
        CustomConfig c = new CustomConfig();
        c.setName("Rang");
        FileConfiguration cfg = c.getConfig();
        if (cfg.isSet("Rangs")) {
            String ranglist1 = cfg.getString("Rangs");
            if (ranglist1.contains(":")) {
                String[] ranglist2 = cfg.getString("Rangs").split(":");
                Range.addAll(Arrays.asList(ranglist2));
            } else {
                Range.add(ranglist1);
            }
        } else {
            cfg.set("Rangs", "Owner");
        }

        //Commands
        for (String r : Range) {
            if (cfg.isSet("Rang." + r)) {
                String commandlist1 = cfg.getString("Rang." + r);
                ArrayList<String> add = new ArrayList<>();
                add.add(r);
                if (commandlist1.contains(":")) {
                    String[] commandlist2 = cfg.getString("Rang." + r).split(":");
                    add.addAll(Arrays.asList(commandlist2));
                } else {
                    add.add(commandlist1);
                }
                RangCommands.add(add);
            } else {
                cfg.set("Rang." + r, "null");
            }
        }
        for (ArrayList<String> a1 : RangCommands) {
            if (!cfg.isSet("Prefix." + a1.get(0))) {
                cfg.set("Prefix." + a1.get(0), a1.get(0) + " ");
            }
        }

        c.saveConfig();
    }

    public static boolean checkCommand(String command, String Rang) {
        boolean data = false;
        int number = 0;
        for (String a1 : Range) {
            if(a1.equals(Rang)){
                if(RangCommands.get(number).contains(command)){
                    data = true;
                }
            }
            number++;
        }
        return data;
    }
}
