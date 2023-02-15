package dev.chaos.ezcoords.client;

import dev.chaos.ezcoords.client.event.ClientCommandHandler;
import dev.chaos.ezcoords.client.event.KeyInputHandler;
import static dev.chaos.ezcoords.client.Config.getValue;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Environment(EnvType.CLIENT)
public class EZCoordsClient implements ClientModInitializer {
    public static Logger LOGGER;
    @Override
    public void onInitializeClient() {
        LOGGER = LoggerFactory.getLogger("EZCoords");
        ClientCommandHandler.register();
        KeyInputHandler.register();
        Config.intialize();
    }
    public static void copyCoordsToClipboard(Vec3d pos) {
        String coordsString;
        if (getValue("command_block_mode").equals("true")) {
            coordsString = String.format("%.2f %.2f %.2f", pos.x, pos.y, pos.z);
        } else {
            coordsString = String.format("X: %.2f, Y: %.2f, Z: %.2f", pos.x, pos.y, pos.z);
        }

        Keyboard keyboard = MinecraftClient.getInstance().keyboard;
        keyboard.setClipboard(coordsString);
        sendChatMessage("Copied coordinates to clipboard.");
    }

    public static void sendChatMessage(String message) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player != null) {
            client.player.sendMessage(Text.of(message), false);
        }
    }
}
