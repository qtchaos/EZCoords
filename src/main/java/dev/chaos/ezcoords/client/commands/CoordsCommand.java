package dev.chaos.ezcoords.client.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.entity.Entity;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;

import static dev.chaos.ezcoords.client.EZCoordsClient.copyCoordsToClipboard;

public class CoordsCommand {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(ClientCommandManager.literal("coords")
            .executes(context -> {
                Vec3d coords = context.getSource().getPlayer().getPos();
                copyCoordsToClipboard(coords);
                return 1;
            })
        );
        dispatcher.register(ClientCommandManager.literal("coords")
            .then(ClientCommandManager.literal("block")
            .executes(context -> {
                Entity entity = context.getSource().getClient().getCameraEntity();
                assert entity != null;
                HitResult blockHit = entity.raycast(20.0, 0.0F, false);
                copyCoordsToClipboard(blockHit.getPos());
                return 1;
            }))
        );
    }
}
