package dev.chaos.ezcoords.client.event;

import dev.chaos.ezcoords.client.commands.ConfigCommand;
import dev.chaos.ezcoords.client.commands.CoordsCommand;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;

public class ClientCommandHandler {
    public static void register() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
            CoordsCommand.register(dispatcher);
            ConfigCommand.register(dispatcher);
        });
    }
}
