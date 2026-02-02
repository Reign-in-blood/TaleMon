package org.example.plugin;

import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;

import javax.annotation.Nonnull;
import java.lang.reflect.Method;

/**
 * This class serves as the entrypoint for your plugin. Use the setup method to register into game registries or add
 * event listeners.
 */
public class ExamplePlugin extends JavaPlugin {

    private static final HytaleLogger LOGGER = HytaleLogger.forEnclosingClass();

    public ExamplePlugin(@Nonnull JavaPluginInit init) {
        super(init);
    }

    @Override
    protected void setup() {
        LOGGER.atInfo().log("Setting up plugin " + this.getName());
    }

    @Override
    protected void start() {
        String loadedMessage = "TaleMon loaded";
        LOGGER.atInfo().log(loadedMessage);
        broadcastToPlayers(loadedMessage);
    }

    private void broadcastToPlayers(String message) {
        Message rawMessage = Message.raw(message);
        Object target = invokeNoArg(this, "getServer");
        if (target == null) {
            target = this;
        }
        Object playerManager = invokeNoArg(target, "getPlayerManager");
        if (playerManager == null) {
            LOGGER.atWarning().log("Unable to broadcast TaleMon startup message (player manager unavailable).");
            return;
        }
        try {
            Method broadcast = playerManager.getClass().getMethod("broadcast", Message.class);
            broadcast.invoke(playerManager, rawMessage);
        } catch (ReflectiveOperationException ex) {
            LOGGER.atWarning().log("Unable to broadcast TaleMon startup message (broadcast method unavailable).");
        }
    }

    private Object invokeNoArg(Object target, String methodName) {
        try {
            Method method = target.getClass().getMethod(methodName);
            return method.invoke(target);
        } catch (ReflectiveOperationException ex) {
            return null;
        }
    }
}
