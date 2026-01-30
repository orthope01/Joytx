package com.joystx.mod.controller;

import com.joystx.mod.ControllerButton;
import com.joystx.mod.ControllerInput;
import com.joystx.mod.PS4Controller;
import com.joystx.mod.JoystXMod;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.KeyMapping;
import net.minecraft.util.Mth;

@Environment(EnvType.CLIENT)
public class ControllerInputHandler {
    private final ControllerConfig config = ControllerConfig.getInstance();

    public ControllerInputHandler(PS4Controller controller) {
        // Если тут будет "Unknown", значит проблема в классе PS4Controller
        JoystXMod.LOGGER.info("[JoystX] Handler запущен. Статус контроллера: " + (controller != null ? "OK" : "ОТСУТСТВУЕТ"));
    }

    public void handleInput(ControllerInput input) {
        if (input == null) return;
        
        Minecraft client = Minecraft.getInstance();
        if (client.player == null) return;

        // Основные механики
        handleMovement(input, client);
        handleCamera(input, client);
        
        // Кнопки действий
        handleActions(input, client);
    }

    private void handleMovement(ControllerInput input, Minecraft client) {
        float x = applyDeadZone(input.getLeftStickX(), config.getDeadZoneLeft());
        float y = applyDeadZone(input.getLeftStickY(), config.getDeadZoneLeft());

        client.options.keyUp.setDown(y < -0.3f);
        client.options.keyDown.setDown(y > 0.3f);
        client.options.keyLeft.setDown(x < -0.3f);
        client.options.keyRight.setDown(x > 0.3f);
    }

    private void handleCamera(ControllerInput input, Minecraft client) {
        float rX = applyDeadZone(input.getRightStickX(), config.getDeadZoneRight());
        float rY = applyDeadZone(input.getRightStickY(), config.getDeadZoneRight());
        
        if (Math.abs(rX) > 0.01f || Math.abs(rY) > 0.01f) {
            double sens = client.options.sensitivity().get() * config.getCameraSensitivity();
            client.player.setYRot(client.player.getYRot() + (rX * 4.5f * (float)sens));
            client.player.setXRot(Mth.clamp(client.player.getXRot() - (rY * 3.5f * (float)sens), -90f, 90f));
        }
    }

    private void handleActions(ControllerInput input, Minecraft client) {
        // Прыжок на Крестик
        client.options.keyJump.setDown(input.isPressed(ControllerButton.CROSS));
        
        // Атака (R2) и использование (L2)
        client.options.keyAttack.setDown(input.isPressed(ControllerButton.R2));
        client.options.keyUse.setDown(input.isPressed(ControllerButton.L2));
        
        // Инвентарь на Треугольник
        if (input.isPressedNow(ControllerButton.TRIANGLE)) {
            client.options.keyInventory.setDown(true);
        } else if (!input.isPressed(ControllerButton.TRIANGLE)) {
            client.options.keyInventory.setDown(false);
        }
    }

    private float applyDeadZone(float value, float deadZone) {
        if (Math.abs(value) < deadZone) return 0f;
        return (value - (Math.signum(value) * deadZone)) / (1f - deadZone);
    }
}