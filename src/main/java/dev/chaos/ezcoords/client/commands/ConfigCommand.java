package dev.chaos.ezcoords.client.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;

import static dev.chaos.ezcoords.client.Config.setValue;
import static dev.chaos.ezcoords.client.Config.getValue;
import static dev.chaos.ezcoords.client.EZCoordsClient.sendChatMessage;

public class ConfigCommand {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(ClientCommandManager.literal("coords")
            .then(ClientCommandManager.literal("config")
                .then(ClientCommandManager.literal("command_block_mode")
                    .executes(context -> {
                        String value = getValue("command_block_mode");
                        setValue("command_block_mode", String.valueOf(!Boolean.parseBoolean(value)));
                        sendChatMessage(String.format("command_block_mode: %s", getValue("command_block_mode")));
                        return 1;
                    })
                )
            )
        );
    }
}
