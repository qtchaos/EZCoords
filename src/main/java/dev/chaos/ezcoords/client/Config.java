package dev.chaos.ezcoords.client;

import net.fabricmc.loader.api.FabricLoader;
import static dev.chaos.ezcoords.client.EZCoordsClient.LOGGER;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Scanner;

public class Config {
    private static final String[][] AllOptions = new String[5][];
    private static final String ConfigPath = FabricLoader.getInstance().getConfigDir().toString();

    public static void intialize() {
        AllOptions[0] = new String[]{"command_block_mode", String.valueOf(false)};
        if (!FabricLoader.getInstance().getConfigDir().resolve("EZCoords.properties").toFile().exists()) {
            writeConfig();
        }
    }
    public static void setValue(String Key, String Value) {
        for (int i = 0; i < Array.getLength(AllOptions); i++) {
            if (AllOptions[i] != null && AllOptions[i][0].equals(Key)) {
                AllOptions[i][1] = Value;
            }
        }
        writeConfig();
    }
    public static String getValue(String Key) {
        FileReader reader;
        try {
            reader = new FileReader(ConfigPath + "/EZCoords.properties");
        } catch (Exception e) {
            LOGGER.error(String.valueOf(e));
            return "none";
        }
        try (Scanner scanner = new Scanner(reader)) {
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                if (data.contains(Key)) {
                    return data.split("=")[1];
                }
            }
        } catch (Exception e) {
           LOGGER.error(String.valueOf(e));
        }
        return "none";
    }

    public static void writeConfig() {
        try {
            FileWriter writer = new FileWriter(ConfigPath + "/EZCoords.properties");
            for (int i = 0; i < Array.getLength(AllOptions); i++) {
                if (AllOptions[i] != null) {
                    writer.write(AllOptions[i][0] + "=" + AllOptions[i][1]);
                }
            }
            writer.close();
        } catch (IOException e) {
            LOGGER.info("An error occurred while writing the config file.");
        }
    }
}
