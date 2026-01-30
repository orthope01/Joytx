package com.joystx.mod;

/**
 * Состояние контроллера PS4 (кнопки и оси)
 */
public class PS4ControllerState {
    // Кнопки
    public boolean square, triangle, circle, cross;
    public boolean l1, l2, l3, r1, r2, r3;
    public boolean options, share, touchpad, psButton;
    public boolean dpadUp, dpadDown, dpadLeft, dpadRight;

    // Аналоговые оси (-1.0 до 1.0)
    public float leftStickX, leftStickY;
    public float rightStickX, rightStickY;
    public float l2Analog, r2Analog; // 0.0 до 1.0

    public PS4ControllerState() {
        reset();
    }

    public PS4ControllerState(PS4ControllerState other) {
        copy(other);
    }

    public void reset() {
        square = triangle = circle = cross = false;
        l1 = l2 = l3 = r1 = r2 = r3 = false;
        options = share = touchpad = psButton = false;
        dpadUp = dpadDown = dpadLeft = dpadRight = false;
        leftStickX = leftStickY = 0;
        rightStickX = rightStickY = 0;
        l2Analog = r2Analog = 0;
    }

    public void copy(PS4ControllerState other) {
        this.square = other.square;
        this.triangle = other.triangle;
        this.circle = other.circle;
        this.cross = other.cross;
        this.l1 = other.l1;
        this.l2 = other.l2;
        this.l3 = other.l3;
        this.r1 = other.r1;
        this.r2 = other.r2;
        this.r3 = other.r3;
        this.options = other.options;
        this.share = other.share;
        this.touchpad = other.touchpad;
        this.psButton = other.psButton;
        this.dpadUp = other.dpadUp;
        this.dpadDown = other.dpadDown;
        this.dpadLeft = other.dpadLeft;
        this.dpadRight = other.dpadRight;
        this.leftStickX = other.leftStickX;
        this.leftStickY = other.leftStickY;
        this.rightStickX = other.rightStickX;
        this.rightStickY = other.rightStickY;
        this.l2Analog = other.l2Analog;
        this.r2Analog = other.r2Analog;
    }
}
