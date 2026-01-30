package com.joystx.mod.controller;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;
import org.lwjgl.glfw.GLFW;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

public class ControllerManager {
    private static int activeId = -1;
    private static boolean welcomeSent = false;
    
    // Состояния для разовых нажатий
    private static boolean invWasDown = false;
    private static boolean crossWasDown = false;
    private static boolean dpadLeftWasDown = false;
    private static boolean dpadRightWasDown = false;

    private static final float DEADZONE = 0.18f;

    public static void init() { System.out.println("[JoystX] Исправленная версия: Хотбар + Клики."); }
    public static void shutdown() {}

    public static void update() {
        Minecraft client = Minecraft.getInstance();
        if (client == null || client.getWindow() == null) return;

        if (activeId == -1 || !GLFW.glfwJoystickPresent(activeId)) {
            findController();
            return;
        }

        ByteBuffer buttons = GLFW.glfwGetJoystickButtons(activeId);
        FloatBuffer axes = GLFW.glfwGetJoystickAxes(activeId);
        if (buttons == null || axes == null) return;

        if (client.screen != null) {
            handleMenu(client, axes, buttons);
        } else if (client.player != null) {
            handleGameplay(client, axes, buttons);
        }
    }

    private static void handleMenu(Minecraft client, FloatBuffer axes, ByteBuffer buttons) {
        long window = client.getWindow().getWindow();
        
        // 1. Курсор
        double speed = 14.0;
        if (Math.abs(axes.get(0)) > DEADZONE || Math.abs(axes.get(1)) > DEADZONE) {
            GLFW.glfwSetCursorPos(window, client.mouseHandler.xpos() + (axes.get(0) * speed), client.mouseHandler.ypos() + (axes.get(1) * speed));
        }

        // 2. КЛИК (Исправлено: теперь корректно отпускает кнопку)
        boolean cross = buttons.get(1) == GLFW.GLFW_PRESS;
        double guiX = client.mouseHandler.xpos() * (double)client.getWindow().getGuiScaledWidth() / (double)client.getWindow().getWidth();
        double guiY = client.mouseHandler.ypos() * (double)client.getWindow().getGuiScaledHeight() / (double)client.getWindow().getHeight();

        if (cross && !crossWasDown) {
            client.screen.mouseClicked(guiX, guiY, 0); // Нажал
        } else if (!cross && crossWasDown) {
            client.screen.mouseReleased(guiX, guiY, 0); // Отпустил (важно для фиксации вещи)
        }
        crossWasDown = cross;

        // 3. Закрыть (Треугольник)
        boolean inv = buttons.get(3) == GLFW.GLFW_PRESS;
        if (inv && !invWasDown) client.setScreen(null);
        invWasDown = inv;
    }

    private static void handleGameplay(Minecraft client, FloatBuffer axes, ByteBuffer buttons) {
        // --- КАМЕРА ---
        float rx = (axes.remaining() > 2) ? axes.get(2) : 0;
        float ry = (axes.remaining() > 5) ? axes.get(5) : axes.get(3);
        if (Math.abs(rx) > DEADZONE || Math.abs(ry) > DEADZONE) {
            float sens = client.options.sensitivity().get().floatValue() * 2.5f;
            client.player.setYRot(client.player.getYRot() + (float)(Math.pow(rx, 3) * sens));
            client.player.setXRot(Math.max(-90.0f, Math.min(90.0f, client.player.getXRot() + (float)(Math.pow(ry, 3) * sens))));
        }

        // --- ДВИЖЕНИЕ ---
        client.options.keyUp.setDown(axes.get(1) < -0.4f);
        client.options.keyDown.setDown(axes.get(1) > 0.4f);
        client.options.keyLeft.setDown(axes.get(0) < -0.4f);
        client.options.keyRight.setDown(axes.get(0) > 0.4f);

        // --- СКРОЛЛ ХОТБАРА (Стрелки влево/вправо) ---
        // Обычно D-pad на DS4: Влево - 14, Вправо - 12 (может зависеть от драйвера)
        boolean dLeft = buttons.get(Math.min(buttons.limit()-1, 14)) == GLFW.GLFW_PRESS;
        boolean dRight = buttons.get(Math.min(buttons.limit()-1, 12)) == GLFW.GLFW_PRESS;

        if (dLeft && !dpadLeftWasDown) client.player.getInventory().swapPaint(-1);
        if (dRight && !dpadRightWasDown) client.player.getInventory().swapPaint(1);
        
        dpadLeftWasDown = dLeft;
        dpadRightWasDown = dRight;

        // --- ДЕЙСТВИЯ (X) ---
        boolean cross = buttons.get(1) == GLFW.GLFW_PRESS;
        client.options.keyUse.setDown(cross); // Открыть сундук
        client.options.keyJump.setDown(cross); // Прыжок

        // --- УДАР (R2) ---
        boolean r2 = (axes.remaining() > 4 && axes.get(4) > -0.5f) || buttons.get(5) == GLFW.GLFW_PRESS;
        client.options.keyAttack.setDown(r2);

        // Инвентарь (Треугольник)
        boolean inv = buttons.get(3) == GLFW.GLFW_PRESS;
        if (inv && !invWasDown) client.setScreen(new InventoryScreen(client.player));
        invWasDown = inv;
        
        // Бег (L3)
        if (buttons.get(10) == GLFW.GLFW_PRESS) client.player.setSprinting(true);
    }

    private static void findController() {
        for (int i = 0; i <= 15; i++) if (GLFW.glfwJoystickPresent(i)) { activeId = i; break; }
    }
}