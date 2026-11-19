package com.astryx.addon.hud;

import com.astryx.addon.Astryx;
import meteordevelopment.meteorclient.settings.ColorSetting;
import meteordevelopment.meteorclient.settings.Setting;
import meteordevelopment.meteorclient.settings.SettingGroup;
import meteordevelopment.meteorclient.settings.StringSetting;
import meteordevelopment.meteorclient.systems.hud.HudElement;
import meteordevelopment.meteorclient.systems.hud.HudElementInfo;
import meteordevelopment.meteorclient.systems.hud.HudRenderer;
import meteordevelopment.meteorclient.utils.render.color.SettingColor;

/**
 * A small orange-and-gray badge with the client name and version.
 */
public class WatermarkHud extends HudElement {
    // The name here ("watermark") must be kebab-case.
    public static final HudElementInfo<WatermarkHud> INFO = new HudElementInfo<>(
        Astryx.HUD_GROUP, "watermark", "Shows the client name and version.", WatermarkHud::new
    );

    private final SettingGroup sgGeneral = settings.getDefaultGroup();

    private final Setting<String> text = sgGeneral.add(new StringSetting.Builder()
        .name("text")
        .description("The name shown on screen.")
        .defaultValue(Astryx.NAME)
        .build()
    );

    private final Setting<SettingColor> accent = sgGeneral.add(new ColorSetting.Builder()
        .name("accent-color")
        .description("Color of the name and the side bar.")
        .defaultValue(new SettingColor(217, 119, 87))
        .build()
    );

    private final Setting<SettingColor> textColor = sgGeneral.add(new ColorSetting.Builder()
        .name("version-color")
        .description("Color of the version text.")
        .defaultValue(new SettingColor(154, 154, 154))
        .build()
    );

    private final Setting<SettingColor> background = sgGeneral.add(new ColorSetting.Builder()
        .name("background-color")
        .description("Color of the box behind the text.")
        .defaultValue(new SettingColor(46, 46, 46, 220))
        .build()
    );

    public WatermarkHud() {
        super(INFO);
    }

    @Override
    public void render(HudRenderer renderer) {
        String name = text.get();
        String version = "v" + Astryx.VERSION;

        double padding = 4;
        double bar = 2;
        double gap = 4;

        double nameWidth = renderer.textWidth(name, true);
        double versionWidth = renderer.textWidth(version, true);
        double textHeight = renderer.textHeight(true);

        // Tell Meteor how big this element is so it can be dragged around in the HUD editor.
        setSize(bar + padding * 2 + nameWidth + gap + versionWidth, textHeight + padding * 2);

        // Background box, then the orange bar on the left.
        renderer.quad(x, y, getWidth(), getHeight(), background.get());
        renderer.quad(x, y, bar, getHeight(), accent.get());

        // Name in orange, version in gray. renderer.text returns where the text ended.
        double textX = x + bar + padding;
        double textY = y + padding;
        double end = renderer.text(name, textX, textY, accent.get(), true);
        renderer.text(version, end + gap, textY, textColor.get(), true);
    }
}
