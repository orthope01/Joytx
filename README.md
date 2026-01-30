# JoystX - PS4 Controller Mod для Minecraft

Полнофункциональный Fabric мод, позволяющий использовать PS4 контроллер (DualShock 4) в Minecraft 1.21.1.

## 🎮 Функции

- ✅ **Полная поддержка PS4 контроллера** - все кнопки и оси
- ✅ **Оптимальная чувствительность** - настраивается через конфиг
- ✅ **Интеллектуальные мёртвые зоны** - не реагирует на дрифт стика
- ✅ **Полный маппинг действий** - прыжок, спринт, атака, использование
- ✅ **Управление камерой** - правый стик для поворота
- ✅ **D-Pad для быстрого доступа** - выбор слотов инвентаря
- ✅ **Автоматическое обнаружение** - мод находит контроллер автоматически
- ✅ **Поддержка DS4Windows** - работает с программой DS4Windows

## 📋 Требования

- Java 21+
- Minecraft 1.21.1
- Fabric Loader 0.15.11+
- PS4/DualShock 4 контроллер или DS4Windows

## 🚀 Установка

1. **Скачайте модифицированный JAR файл** из папки `build/libs/`
2. **Поместите JAR** в папку `mods` вашего Minecraft клиента
3. **Запустите Minecraft** с Fabric профилем
4. **Подключите PS4 контроллер** (USB или Bluetooth)

## ⚙️ Конфигурация

После первого запуска мода создастся файл конфигурации:
```
config/joystx/controller-config.properties
```

### Основные настройки:

```properties
# Мёртвые зоны (0.0 - 1.0)
deadzone.left=0.1
deadzone.right=0.15

# Чувствительность камеры (множитель)
sensitivity.camera=1.0

# Чувствительность движения (множитель)
sensitivity.movement=1.0

# Вибрация контроллера
vibration.enabled=true

# Автоматическое обнаружение контроллера
auto.detect=true
```

## 🎯 Маппинг кнопок

| Кнопка PS4 | Действие Minecraft |
|-----------|------------------|
| **CROSS (✕)** | Прыжок |
| **SQUARE (□)** | Спринт |
| **CIRCLE (○)** | Присед (Sneak) |
| **TRIANGLE (△)** | Выброс предмета |
| **L1** | Атака/Разрушение |
| **R1** | Использование/Размещение |
| **L2 (аналог)** | Альт-использование (90%+) |
| **R2 (аналог)** | Усиленная атака (90%+) |
| **L3** | Переключение перспективы |
| **R3** | Альтернативный вид |
| **OPTIONS** | Пауза/Меню |
| **SHARE** | Инвентарь |
| **D-Pad ↑** | Слот 1 |
| **D-Pad ←** | Слот 0 |
| **D-Pad →** | Слот 2 |
| **D-Pad ↓** | Слот 3 |
| **Левый стик** | Движение (W/A/S/D) |
| **Правый стик** | Поворот камеры |

## 🔧 Разработка

### Требования для сборки

- JDK 21+
- Gradle 8.0+

### Сборка мода

```bash
# Клонировать репозиторий
git clone <repo-url>
cd joystx

# Собрать мод
./gradlew build

# JAR файл будет в build/libs/joystx-mod-1.0.0.jar
```

## 🐛 Поддержка контроллеров

Мод поддерживает:
- **Sony DualShock 4** (PS4)
- **Sony DualSense** (PS5) - частичная поддержка
- **Совместимые контроллеры** (с поддержкой XINPUT)

### DS4Windows интеграция

Для лучшей совместимости используйте [DS4Windows](https://ds4-windows.com/):

1. Скачайте DS4Windows
2. Запустите приложение
3. Подключите PS4 контроллер
4. DS4Windows автоматически эмулирует XINPUT
5. Мод автоматически обнаружит контроллер

## 📝 Логи и отладка

Логи мода находятся в:
- Linux/Mac: `~/.minecraft/logs/latest.log`
- Windows: `%APPDATA%\.minecraft\logs\latest.log`

Поиск строк содержащих "JoystX" или "joystx" для отладки.

## 🤝 Вклад

Если вы нашли баг или хотите добавить функцию:

1. Создайте issue с описанием проблемы
2. Сделайте fork репозитория
3. Создайте ветку для вашей функции
4. Отправьте pull request

## 📄 Лицензия

MIT License - смотри [LICENSE](LICENSE)

## 💡 Советы

- Если контроллер не обнаруживается, проверьте подключение
- Отрегулируйте мёртвые зоны если чувствуете дрифт стика
- Используйте DS4Windows для лучшей совместимости на Windows
- Перезагрузитесь если контроллер подключен при запуске игры

## 🙏 Спасибо

Спасибо всем, кто помогает в развитии мода!

---

**JoystX Team** | Minecraft 1.21.1 | Fabric Mod
English ver - 

---

# JoystX - PS4 Controller Mod for Minecraft

A fully featured Fabric mod that enables seamless PS4 controller (DualShock 4) support for Minecraft 1.21.1.

## 🎮 Features

* ✅ **Full PS4 Controller Support** – All buttons and axes mapped.
* ✅ **Optimal Sensitivity** – Fully adjustable via config file.
* ✅ **Smart Deadzones** – Eliminates stick drift issues.
* ✅ **Complete Action Mapping** – Jump, sprint, attack, and interact.
* ✅ **Camera Control** – Smooth right-stick rotation.
* ✅ **D-Pad Quick Access** – Easy inventory slot selection.
* ✅ **Auto-Detection** – Plug-and-play controller recognition.
* ✅ **DS4Windows Support** – Fully compatible with DS4Windows mapper.

## 📋 Requirements

* Java 21+
* Minecraft 1.21.1
* Fabric Loader 0.15.11+
* PS4/DualShock 4 Controller or DS4Windows

## 🚀 Installation

1. **Download the mod JAR** from the `build/libs/` folder.
2. **Place the JAR** into your Minecraft client's `mods` folder.
3. **Launch Minecraft** using the Fabric profile.
4. **Connect your PS4 controller** via USB or Bluetooth.

## ⚙️ Configuration

The configuration file is created after the first launch at:

```
config/joystx/controller-config.properties

```

### Main Settings:

```properties
# Deadzones (0.0 - 1.0)
deadzone.left=0.1
deadzone.right=0.15

# Camera Sensitivity (Multiplier)
sensitivity.camera=1.0

# Movement Sensitivity (Multiplier)
sensitivity.movement=1.0

# Controller Vibration
vibration.enabled=true

# Auto-detect Controller
auto.detect=true

```

## 🎯 Button Mapping

| PS4 Button | Minecraft Action |
| --- | --- |
| **CROSS (✕)** | Jump |
| **SQUARE (□)** | Sprint |
| **CIRCLE (○)** | Sneak |
| **TRIANGLE (△)** | Drop Item |
| **L1** | Attack / Destroy |
| **R1** | Use / Place |
| **L2 (Analog)** | Alt-Use (90%+) |
| **R2 (Analog)** | Power Attack (90%+) |
| **L3** | Toggle Perspective |
| **R3** | Alt View |
| **OPTIONS** | Pause / Menu |
| **SHARE** | Inventory |
| **D-Pad ↑** | Slot 1 |
| **D-Pad ←** | Slot 0 |
| **D-Pad →** | Slot 2 |
| **D-Pad ↓** | Slot 3 |
| **Left Stick** | Movement (W/A/S/D) |
| **Right Stick** | Camera Look |

## 🔧 Development

### Build Requirements

* JDK 21+
* Gradle 8.0+

### How to Build

```bash
# Clone the repository
git clone <repo-url>
cd joystx

# Build the mod
./gradlew build

# The JAR file will be in build/libs/joystx-mod-1.0.0.jar

```

## 🐛 Controller Support

Supported devices:

* **Sony DualShock 4** (PS4)
* **Sony DualSense** (PS5) – Partial support
* **Compatible Controllers** (with XINPUT support)

### DS4Windows Integration

For best compatibility on Windows, use [DS4Windows](https://ds4-windows.com/):

1. Run DS4Windows.
2. Connect your controller.
3. The mod will automatically detect the emulated XINPUT device.

## 📝 Logs & Debugging

Log files can be found at:

* Windows: `%APPDATA%\.minecraft\logs\latest.log`
* Linux/Mac: `~/.minecraft/logs/latest.log`

Search for "JoystX" or "joystx" for debug info.

## 🤝 Contributing

1. Open an issue describing the bug or feature.
2. Fork the repository.
3. Create a feature branch.
4. Submit a pull request.

## 📄 License

MIT License - see [LICENSE]()

---

**JoystX Team** | Minecraft 1.21.1 | Fabric Mod


