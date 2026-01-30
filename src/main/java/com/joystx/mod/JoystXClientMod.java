package com.joystx.mod; // Убрали .mixin, теперь соответствует структуре проекта

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.joystx.mod.controller.ControllerManager;

@Environment(EnvType.CLIENT)
public class JoystXClientMod implements ClientModInitializer {
    private static final Logger LOGGER = LoggerFactory.getLogger("joystx");

    @Override
    public void onInitializeClient() {
        LOGGER.info("JoystX Client Mod инициализирован!");
        
        // ВАЖНО: убедись, что в ControllerManager метод называется init()
        // Если там всё-таки initialize(), то верни как было.
        ControllerManager.init(); 
    }
}