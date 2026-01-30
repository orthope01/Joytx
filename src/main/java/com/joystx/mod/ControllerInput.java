package com.joystx.mod;

/**
 * Входные данные контроллера - текущее и предыдущее состояние
 */
public class ControllerInput {
    private final PS4Controller controller;
    private final PS4ControllerState current;
    private final PS4ControllerState previous;

    public ControllerInput(PS4Controller controller, PS4ControllerState current, PS4ControllerState previous) {
        this.controller = controller;
        this.current = current;
        this.previous = previous;
    }

    public PS4Controller getController() {
        return controller;
    }

    public PS4ControllerState getCurrent() {
        return current;
    }

    public PS4ControllerState getPrevious() {
        return previous;
    }

    // Проверка нажата ли кнопка (текущее состояние)
    public boolean isPressed(ControllerButton button) {
        return getButtonState(current, button);
    }

    // Проверка была ли кнопка нажата в предыдущем состоянии
    public boolean wasPressed(ControllerButton button) {
        return getButtonState(previous, button);
    }

    // Проверка нажата ли кнопка ВПЕРВЫЕ (press event)
    public boolean isPressedNow(ControllerButton button) {
        return isPressed(button) && !wasPressed(button);
    }

    // Проверка была ли отпущена кнопка (release event)
    public boolean isReleasedNow(ControllerButton button) {
        return !isPressed(button) && wasPressed(button);
    }

    private boolean getButtonState(PS4ControllerState state, ControllerButton button) {
        switch (button) {
            case SQUARE -> {
                return state.square;
            }
            case TRIANGLE -> {
                return state.triangle;
            }
            case CIRCLE -> {
                return state.circle;
            }
            case CROSS -> {
                return state.cross;
            }
            case L1 -> {
                return state.l1;
            }
            case L2 -> {
                return state.l2;
            }
            case L3 -> {
                return state.l3;
            }
            case R1 -> {
                return state.r1;
            }
            case R2 -> {
                return state.r2;
            }
            case R3 -> {
                return state.r3;
            }
            case OPTIONS -> {
                return state.options;
            }
            case SHARE -> {
                return state.share;
            }
            case DPAD_UP -> {
                return state.dpadUp;
            }
            case DPAD_DOWN -> {
                return state.dpadDown;
            }
            case DPAD_LEFT -> {
                return state.dpadLeft;
            }
            case DPAD_RIGHT -> {
                return state.dpadRight;
            }
            default -> {
                return false;
            }
        }
    }

    public float getLeftStickX() {
        return current.leftStickX;
    }

    public float getLeftStickY() {
        return current.leftStickY;
    }

    public float getRightStickX() {
        return current.rightStickX;
    }

    public float getRightStickY() {
        return current.rightStickY;
    }

    public float getL2Analog() {
        return current.l2Analog;
    }

    public float getR2Analog() {
        return current.r2Analog;
    }
}
