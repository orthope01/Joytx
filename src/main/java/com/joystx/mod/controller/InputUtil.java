package com.joystx.mod.controller;

import java.util.*;

import com.joystx.mod.JoystXMod;
import com.joystx.mod.PS4ControllerState;

/**
 * Утилиты для работы с входами контроллера на Windows
 * Использует XINPUT API через JNI
 */
public class InputUtil {
    private static final String XINPUT_LIB = "xinput1_4";
    private static boolean initialized = false;

    static {
        try {
            // Попытка загрузить встроенную библиотеку Windows XINPUT
            System.loadLibrary("xinput1_4");
            initialized = true;
        } catch (UnsatisfiedLinkError e) {
            // XINPUT недоступен - используем fallback
            JoystXMod.LOGGER.warn("XINPUT недоступен, используется fallback режим");
            initialized = false;
        }
    }

    /**
     * Получить количество подключённых контроллеров (max 4 в XINPUT)
     */
    public static int getConnectedControllerCount() {
        if (initialized) {
            return getXInputControllerCount();
        }
        // Fallback: попробуем обнаружить через Raw Input API
        return detectControllersFallback();
    }

    /**
     * Проверить подключен ли контроллер с определённым ID
     */
    public static boolean isControllerConnected(int controllerIndex) {
        if (controllerIndex < 0 || controllerIndex >= 4) {
            return false;
        }
        
        if (initialized) {
            return isXInputControllerConnected(controllerIndex);
        }
        return false;
    }

    /**
     * Прочитать состояние контроллера
     */
    public static PS4ControllerState readControllerState(int controllerIndex) {
        if (!isControllerConnected(controllerIndex)) {
            return null;
        }

        PS4ControllerState state = new PS4ControllerState();
        
        if (initialized) {
            readXInputState(controllerIndex, state);
        }
        
        return state;
    }

    // ============ XINPUT Native Methods ============
    
    private static native int getXInputControllerCount();
    private static native boolean isXInputControllerConnected(int index);
    private static native void readXInputState(int index, PS4ControllerState state);

    // ============ Fallback Methods ============
    
    private static int detectControllersFallback() {
        // Простой fallback - проверяем наличие контроллеров через WMI или другие методы
        try {
            // Windows - проверяем устройства через реестр или WMI
            ProcessBuilder pb = new ProcessBuilder("wmic", "path", "win32_pnpentity", "get", "name");
            Process process = pb.start();
            int count = 0;
            
            Scanner scanner = new Scanner(process.getInputStream());
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().toLowerCase();
                if (line.contains("controller") || line.contains("dualshock") || 
                    line.contains("ps4") || line.contains("gamepad")) {
                    count++;
                }
            }
            scanner.close();
            
            return Math.min(count, 4);
        } catch (Exception e) {
            return 0;
        }
    }
}
