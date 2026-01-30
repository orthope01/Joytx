package com.joystx.mod;

import com.joystx.mod.controller.InputUtil;

/**
 * Класс для представления PS4 контроллера
 */
public class PS4Controller {
    private final int deviceId;
    private boolean connected;
    private PS4ControllerState lastState;
    private PS4ControllerState currentState;

    public PS4Controller(int deviceId) {
        this.deviceId = deviceId;
        this.lastState = new PS4ControllerState();
        this.currentState = new PS4ControllerState();
        this.connected = testConnection();
    }

    private boolean testConnection() {
        try {
            // Попытка проверить наличие контроллера через Windows API
            return InputUtil.isControllerConnected(deviceId);
        } catch (Exception e) {
            return false;
        }
    }

    public synchronized ControllerInput readInput() {
        if (!connected) {
            connected = testConnection();
            if (!connected) return null;
        }

        lastState = new PS4ControllerState(currentState);
        currentState = InputUtil.readControllerState(deviceId);

        if (currentState == null) {
            connected = false;
            return null;
        }

        return new ControllerInput(this, currentState, lastState);
    }

    public boolean isConnected() {
        return connected;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public String getName() {
        return "PS4 Controller #" + (deviceId + 1);
    }

    public PS4ControllerState getCurrentState() {
        return currentState;
    }

    public void close() {
        connected = false;
    }
}
