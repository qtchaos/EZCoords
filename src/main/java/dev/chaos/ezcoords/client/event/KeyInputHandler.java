package dev.chaos.ezcoords.client.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.Entity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.glfw.GLFW;

import static dev.chaos.ezcoords.client.EZCoordsClient.copyCoordsToClipboard;

public class KeyInputHandler {
    private static final String KEY_CATEGORY_EZCOORDS = "key.ezcoords.main";
    private static final String KEY_COPY = "key.ezcoords.keyCopy";
    private static final String KEY_COPY_BLOCK = "key.ezcoords.keyCopyBlock";
    private static KeyBinding EZCoordsCopyKey;
    private static KeyBinding EZCoordsCopyBlockKey;

    public static void registerKeyInput() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player != null && client.getCameraEntity() != null) {
                if (EZCoordsCopyKey.wasPressed()) {
                    copyCoordsToClipboard(new double[]{client.player.getX(), client.player.getY(), client.player.getZ()});
                }
                if (EZCoordsCopyBlockKey.wasPressed()) {
                    Entity entity = client.getCameraEntity();
                    HitResult blockHit = entity.raycast(20.0, 0.0F, false);
                    BlockPos blockPos = ((BlockHitResult)blockHit).getBlockPos();
                    copyCoordsToClipboard(new double[]{blockPos.getX(), blockPos.getY(), blockPos.getZ()});
                }
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

        EZCoordsCopyBlockKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_COPY_BLOCK,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_I,
                KEY_CATEGORY_EZCOORDS
        ));

        registerKeyInput();
    }
}
