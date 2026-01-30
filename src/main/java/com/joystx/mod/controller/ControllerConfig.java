package com.joystx.mod.controller;

import java.io.*;
import java.nio.file.*;
import java.util.Properties;

import com.joystx.mod.JoystXMod;

/**
 * Конфигурация и настройки контроллера
 */
public class ControllerConfig {
    private static final Path CONFIG_DIR = Paths.get("config/joystx");
    private static final Path CONFIG_FILE = CONFIG_DIR.resolve("controller-config.properties");
    private static ControllerConfig instance;

    private Properties props;

    private ControllerConfig() {
        props = new Properties();
        loadConfig();
    }

    public static ControllerConfig getInstance() {
        if (instance == null) {
            instance = new ControllerConfig();
        }
        return instance;
    }

    public static void load() {
        getInstance();
    }

    private void loadConfig() {
        try {
            Files.createDirectories(CONFIG_DIR);
            
            if (Files.exists(CONFIG_FILE)) {
                try (FileInputStream fis = new FileInputStream(CONFIG_FILE.toFile())) {
                    props.load(fis);
                }
                JoystXMod.LOGGER.info("Конфигурация контроллера загружена: " + CONFIG_FILE);
            } else {
                createDefaultConfig();
            }
        } catch (IOException e) {
            JoystXMod.LOGGER.error("Ошибка при загрузке конфигурации", e);
            createDefaultConfig();
        }
    }

    private void createDefaultConfig() {
        try {
            // Стандартная конфигурация
            props.setProperty("deadzone.left", "0.1");
            props.setProperty("deadzone.right", "0.15");
            props.setProperty("sensitivity.camera", "1.0");
            props.setProperty("sensitivity.movement", "1.0");
            props.setProperty("vibration.enabled", "true");
            props.setProperty("auto.detect", "true");

            // Сохранение конфигурации
            Files.createDirectories(CONFIG_DIR);
            try (FileOutputStream fos = new FileOutputStream(CONFIG_FILE.toFile())) {
                props.store(fos, "JoystX Controller Configuration\n# Настройки PS4 контроллера");
            }
            JoystXMod.LOGGER.info("Создана конфигурация по умолчанию: " + CONFIG_FILE);
        } catch (IOException e) {
            JoystXMod.LOGGER.error("Ошибка при создании конфигурации", e);
        }
    }

    public float getDeadZoneLeft() {
        String value = props.getProperty("deadzone.left", "0.1");
        try {
            return Float.parseFloat(value);
        } catch (NumberFormatException e) {
            return 0.1f;
        }
    }

    public float getDeadZoneRight() {
        String value = props.getProperty("deadzone.right", "0.15");
        try {
            return Float.parseFloat(value);
        } catch (NumberFormatException e) {
            return 0.15f;
        }
    }

    public float getCameraSensitivity() {
        String value = props.getProperty("sensitivity.camera", "1.0");
        try {
            return Float.parseFloat(value);
        } catch (NumberFormatException e) {
            return 1.0f;
        }
    }

    public float getMovementSensitivity() {
        String value = props.getProperty("sensitivity.movement", "1.0");
        try {
            return Float.parseFloat(value);
        } catch (NumberFormatException e) {
            return 1.0f;
        }
    }

    public boolean isVibrationEnabled() {
        return Boolean.parseBoolean(props.getProperty("vibration.enabled", "true"));
    }

    public boolean isAutoDetectEnabled() {
        return Boolean.parseBoolean(props.getProperty("auto.detect", "true"));
    }

    public String getConfig(String key, String defaultValue) {
        return props.getProperty(key, defaultValue);
    }

    public void setConfig(String key, String value) {
        props.setProperty(key, value);
        saveConfig();
    }

    private void saveConfig() {
        try {
            Files.createDirectories(CONFIG_DIR);
            try (FileOutputStream fos = new FileOutputStream(CONFIG_FILE.toFile())) {
                props.store(fos, "JoystX Controller Configuration");
            }
            JoystXMod.LOGGER.info("Конфигурация сохранена");
        } catch (IOException e) {
            JoystXMod.LOGGER.error("Ошибка при сохранении конфигурации", e);
        }
    }
}
