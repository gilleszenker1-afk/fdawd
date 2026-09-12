package net.kelptweaks;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KelpTweaks implements ModInitializer {
    public static final String MOD_ID = "kelptweaks";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Kelp Tweaks wird geladen 🌿");

        RedstoneTweaks.init();
        MobTweaks.init();
        KelpNameTag.init();
    }
}
