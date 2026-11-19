package com.astryx.addon.modules;

import com.astryx.addon.Astryx;
import meteordevelopment.meteorclient.settings.Setting;
import meteordevelopment.meteorclient.settings.SettingGroup;
import meteordevelopment.meteorclient.settings.StringSetting;
import meteordevelopment.meteorclient.systems.modules.Module;

/**
 * The simplest possible module: when you switch it on, it prints a message in chat.
 * Copy this file to start any new module, then change the name, settings and what it does.
 */
public class HelloModule extends Module {
    private final SettingGroup sgGeneral = settings.getDefaultGroup();

    // A setting shows up in the module's options in the GUI. The name is kebab-case.
    private final Setting<String> message = sgGeneral.add(new StringSetting.Builder()
        .name("message")
        .description("What to print when the module turns on.")
        .defaultValue("Hello from " + Astryx.NAME + "!")
        .build()
    );

    public HelloModule() {
        // category, name (kebab-case), description
        super(Astryx.CATEGORY, "hello", "Prints a message in chat when enabled.");
    }

    // Runs once each time the module is switched on.
    @Override
    public void onActivate() {
        info(message.get());
    }
}
