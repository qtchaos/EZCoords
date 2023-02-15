package dev.chaos.ezcoords.client.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.util.hit.HitResult;

import static dev.chaos.ezcoords.client.EZCoordsClient.copyCoordsToClipboard;

public class CoordsCommand {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(ClientCommandManager.literal("coords")
            .executes(context -> {
                MinecraftClient client = context.getSource().getClient();
                assert client.player != null;
                copyCoordsToClipboard(new double[]{client.player.getX(), client.player.getY(), client.player.getZ()});
                return 1;
            })
        );
        dispatcher.register(ClientCommandManager.literal("coords")
            .then(ClientCommandManager.literal("block")
            .executes(context -> {
                Entity entity = context.getSource().getClient().getCameraEntity();
                assert entity != null;
                HitResult blockHit = entity.raycast(20.0, 0.0F, false);
                copyCoordsToClipboard(new double[]{blockHit.getPos().x, blockHit.getPos().y, blockHit.getPos().z});
                return 1;
            }))
        );
    }
}
