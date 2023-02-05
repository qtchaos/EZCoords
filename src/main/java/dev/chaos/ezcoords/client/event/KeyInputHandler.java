package dev.chaos.ezcoords.client.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

import static dev.chaos.ezcoords.client.EZCoordsClient.copyCoordsToClipboard;

public class KeyInputHandler {
    private static final String KEY_CATEGORY_EZCOORDS = "key.ezcoords.main";
    private static final String KEY_COPY = "key.ezcoords.keyCopy";
    private static KeyBinding EZCoordsCopyKey;

    public static void registerKeyInput() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (EZCoordsCopyKey.wasPressed() && client.player != null) {
                copyCoordsToClipboard(client.player.getBlockPos().toCenterPos());
            }
        });
    }

    public static void register() {
        EZCoordsCopyKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_COPY,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_O,
                KEY_CATEGORY_EZCOORDS
        ));

        registerKeyInput();
    }
}
