package com.joystx.mod;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JoystXMod implements ModInitializer {
    public static final String MOD_ID = "joystx";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("JoystX Mod инициализирован!"); // Тут никаких ControllerConfig или ControllerManager
    }
}
