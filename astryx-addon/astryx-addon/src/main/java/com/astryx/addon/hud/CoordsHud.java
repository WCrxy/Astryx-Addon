package com.astryx.addon.hud;

import com.astryx.addon.Astryx;
import meteordevelopment.meteorclient.settings.BoolSetting;
import meteordevelopment.meteorclient.settings.ColorSetting;
import meteordevelopment.meteorclient.settings.Setting;
import meteordevelopment.meteorclient.settings.SettingGroup;
import meteordevelopment.meteorclient.systems.hud.HudElement;
import meteordevelopment.meteorclient.systems.hud.HudElementInfo;
import meteordevelopment.meteorclient.systems.hud.HudRenderer;
import meteordevelopment.meteorclient.utils.render.color.SettingColor;

import static meteordevelopment.meteorclient.MeteorClient.mc;

/**
 * Shows your current coordinates in the same orange-and-gray style.
 */
public class CoordsHud extends HudElement {
    public static final HudElementInfo<CoordsHud> INFO = new HudElementInfo<>(
        Astryx.HUD_GROUP, "coords", "Shows your current coordinates.", CoordsHud::new
    );

    private final SettingGroup sgGeneral = settings.getDefaultGroup();

    private final Setting<Boolean> showBackground = sgGeneral.add(new BoolSetting.Builder()
        .name("background")
        .description("Draw a box behind the text.")
        .defaultValue(true)
        .build()
    );

    private final Setting<SettingColor> labelColor = sgGeneral.add(new ColorSetting.Builder()
        .name("label-color")
        .description("Color of the XYZ label.")
        .defaultValue(new SettingColor(217, 119, 87))
        .build()
    );

    private final Setting<SettingColor> valueColor = sgGeneral.add(new ColorSetting.Builder()
        .name("value-color")
        .description("Color of the numbers.")
        .defaultValue(new SettingColor(237, 237, 237))
        .build()
    );

    private final Setting<SettingColor> background = sgGeneral.add(new ColorSetting.Builder()
        .name("background-color")
        .description("Color of the box behind the text.")
        .defaultValue(new SettingColor(46, 46, 46, 220))
        .build()
    );

    public CoordsHud() {
        super(INFO);
    }

    @Override
    public void render(HudRenderer renderer) {
        String label = "XYZ";
        String value;

        if (mc.player == null) {
            // In the HUD editor (or the main menu) there may be no player, so show an example.
            value = isInEditor() ? "123 64 -456" : "-";
        } else {
            value = mc.player.getBlockX() + " " + mc.player.getBlockY() + " " + mc.player.getBlockZ();
        }

        double padding = 4;
        double gap = 4;

        double labelWidth = renderer.textWidth(label, true);
        double valueWidth = renderer.textWidth(value, true);
        double textHeight = renderer.textHeight(true);

        setSize(padding * 2 + labelWidth + gap + valueWidth, textHeight + padding * 2);

        if (showBackground.get()) {
            renderer.quad(x, y, getWidth(), getHeight(), background.get());
        }

        double end = renderer.text(label, x + padding, y + padding, labelColor.get(), true);
        renderer.text(value, end + gap, y + padding, valueColor.get(), true);
    }
}
