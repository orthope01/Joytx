package com.joystx.mod;

/**
 * Перечисление всех кнопок PS4 контроллера
 */
public enum ControllerButton {
    // Основные кнопки
    SQUARE,
    TRIANGLE,
    CIRCLE,
    CROSS,
    
    // Плечевые кнопки
    L1, L2, L3,
    R1, R2, R3,
    
    // Системные кнопки
    OPTIONS,
    SHARE,
    TOUCHPAD,
    PS,
    
    // D-Pad
    DPAD_UP,
    DPAD_DOWN,
    DPAD_LEFT,
    DPAD_RIGHT;
    
    public String getLocalizedName() {
        return switch (this) {
            case SQUARE -> "Square (□)";
            case TRIANGLE -> "Triangle (△)";
            case CIRCLE -> "Circle (○)";
            case CROSS -> "Cross (✕)";
            case L1 -> "L1";
            case L2 -> "L2";
            case L3 -> "L3 (Left Stick)";
            case R1 -> "R1";
            case R2 -> "R2";
            case R3 -> "R3 (Right Stick)";
            case OPTIONS -> "Options";
            case SHARE -> "Share";
            case TOUCHPAD -> "Touchpad";
            case PS -> "PS Button";
            case DPAD_UP -> "D-Pad ↑";
            case DPAD_DOWN -> "D-Pad ↓";
            case DPAD_LEFT -> "D-Pad ←";
            case DPAD_RIGHT -> "D-Pad →";
        };
    }
}
