# Paylance

**Paylance** — это кроссплатформенное мобильное приложение, разработанное с использованием Kotlin Multiplatform Mobile (KMM). Проект ориентирован на платформы Android и iOS, с использованием Jetpack Compose и SwiftUI для соответствующих интерфейсов.

## 📦 Структура проекта

Проект организован следующим образом:

- `composeApp/` — общий код для Android и iOS, включая бизнес-логику и UI-компоненты на Compose Multiplatform.
- `iosApp/` — специфичный для iOS код и настройки.
- `core/` — модуль с общей бизнес-логикой и моделями данных.
- `core-ui/` — общая UI логика с запросами permissions и подобным.
- `uikit/` — общие UI-компоненты и темы.
- `buildSrc/` — скрипты сборки и зависимости Gradle.

## 🚀 Быстрый старт

### 📱 Android

1. Откройте проект в Android Studio.
2. Соберите и запустите `composeApp` на эмуляторе или физическом устройстве Android.

### 🍏 iOS

1. Откройте `iosApp` в Xcode.
2. Соберите и запустите приложение на симуляторе или физическом устройстве iOS.

> Убедитесь, что у вас установлены необходимые инструменты для разработки под iOS, включая Xcode и соответствующие SDK.
