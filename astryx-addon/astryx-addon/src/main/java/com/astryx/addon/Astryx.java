package com.astryx.addon;

import com.astryx.addon.hud.CoordsHud;
import com.astryx.addon.hud.WatermarkHud;
import com.astryx.addon.modules.HelloModule;
import com.mojang.logging.LogUtils;
import meteordevelopment.meteorclient.addons.MeteorAddon;
import meteordevelopment.meteorclient.systems.hud.Hud;
import meteordevelopment.meteorclient.systems.hud.HudGroup;
import meteordevelopment.meteorclient.systems.modules.Category;
import meteordevelopment.meteorclient.systems.modules.Modules;
import org.slf4j.Logger;

/**
 * The main class of the addon. Meteor finds it through the "meteor" entrypoint in fabric.mod.json.
 */
public class Astryx extends MeteorAddon {
    // Change these two lines to rename your client everywhere it shows up on screen.
    public static final String NAME = "Astryx";
    public static final String VERSION = "0.1.0";

    public static final Logger LOG = LogUtils.getLogger();

    // A category is a tab in the Meteor GUI. A HUD group is a heading in the HUD editor.
    public static final Category CATEGORY = new Category(NAME);
    public static final HudGroup HUD_GROUP = new HudGroup(NAME);

    @Override
    public void onInitialize() {
        LOG.info("Loading {} {}", NAME, VERSION);

        // Modules
        Modules.get().add(new HelloModule());

        // HUD elements
        Hud.get().register(WatermarkHud.INFO);
        Hud.get().register(CoordsHud.INFO);
    }

    @Override
    public void onRegisterCategories() {
        Modules.registerCategory(CATEGORY);
    }

    /**
     * Must match the package your classes live in. If it doesn't, the game crashes when a module is enabled.
     */
    @Override
    public String getPackage() {
        return "com.astryx.addon";
    }
}
