package dev.chaos.ezcoords.client.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
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
    }
}
