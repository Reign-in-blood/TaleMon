package com.reigninblood.TaleMon;

import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;

import javax.annotation.Nonnull;
import java.util.logging.Level;

public class TaleMon extends JavaPlugin {

    private static TaleMon instance;

    public TaleMon(@Nonnull JavaPluginInit init) {
        super(init);
        instance = this;
        getLogger().at(Level.INFO).log("[TaleMon] Plugin loaded!");
    }

    public static TaleMon getInstance() {
        return instance;
    }

    @Override
    protected void setup() {
        getLogger().at(Level.INFO).log("[TaleMon] Plugin setup!");

        registerEvents();
        registerCommands();
    }

    @Override
    protected void start() {
        getLogger().at(Level.INFO).log("[TaleMon] Plugin enabled!");
    }

    @Override
    public void shutdown() {
        getLogger().at(Level.INFO).log("[TaleMon] Plugin disabled!");
    }

    private void registerEvents() {
    }

    private void registerCommands() {
    }
}