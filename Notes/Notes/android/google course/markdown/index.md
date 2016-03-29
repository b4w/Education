Android course Udacity by Google
====
####Command Line Tool Commands
Использование этих команд не обязательно. Результат тот же, что и нажатие на кнопку Run в Android Studio.
- ```chmod +x gradlew``` - с помощью этой команды мы помечаем файл gradlew как исполняемый (права на исполнение)
- ```./gradlew assembleDebug``` - компилируем код приложения
- ```adb install -r app/build/outputs/apk/app-debug-unaligned.apk``` - команда устанавливает файл APK.
    * флаг **-r** - перепишет все предыдущие установленные версии
    * флаг **-s** - если у нас более одного устройства, нам необходимо использовать этот флаг сразу после **adb** для указания серийного номера предпологаемого устройства.
- ```adb shell am start -n com.example.android.sunshine.app/com.example.android.sunshine.app.MainActivity``` - запуск приложения

Дополнительные ссылки:
1. [Android Debug Bridge (ADB)](http://developer.android.com/tools/help/adb.html?utm_source=udacity&utm_medium=mooc&utm_term=android&utm_content=adb&utm_campaign=training)
2. [Building with Gradle](http://developer.android.com/sdk/installing/studio-build.html?utm_source=udacity&utm_medium=mooc&utm_term=android&utm_content=studio_gradle&utm_campaign=training)
