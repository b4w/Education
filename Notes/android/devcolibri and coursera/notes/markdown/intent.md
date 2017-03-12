##Intent

**Intent** - предназначены для вызова компонентов в android. Компонентами в android являются activity, сервисы, либо какие-то другие приложения.

**Пример:**
Для того что бы перейти с одного activity на другой - мы можем использовать intent-ы. С помощью intent-ов так же можно запускать сервисы. Например мы из нашего приложения хотим отправить письмо с помощью приложения gmail - переход будет осуществляться с помощью intent-a.
Если кратко, то intent - это механизм вызова каких-то компонентов (нашего либо другого приложения).

Для того что бы *передать с помощью intent какие-либо параметры другой activity* - необходимо использовать метод ```intent.putExtra()```. 
**Пример:**
```intent.putExtra("login", login.getText().toString())```

Для того что бы *принять какие-либо параметры из "входного" intent-а*, необходимо воспользоваться методом - ```getIntent().getStringExtra("%fildName%")```. 
**Пример** 
```getIntent.getStringExtra("login")```

###Открытие другой активности, передача параметров, возврат результата
RequestCode - код запроса, который мы передаем в методе вызова другой activity.Кодом может быть любое уникальное число, по которому в последующем будет определяться ответ activity.

```startActivityForResulted()``` - метод, который запустит нам activity и после выполнения там определенного действия мы получим результат этого activity.

**Пример:**
- cоздали отдельный класс со статическими константами
```public static final int REQUEST_CODE_PRESENTED = 1;```
- передаем константу в метод вызова 
```activitystartActivityForResult(intent, );```
```setResult(RESULT_OK, intent)``` (RESULT_OK/RESULT_CANCELED/RESULT_FIRST_USER и т.д.) - метод указывает результат, который будет передаваться в ответе на вызов метода startActivityForResulted().

#НЕ ОТСОРТИРОВАННО!

# Intent Filter, явный и неявный вызов Activity. Ex 17
Intent - механизм для описания одной операции. Например - сделай звонок на определенный номер.
Явный вызов activity - когда мы вызываем activity через intent - Intent intent = new Intent (getApplicationContext(), DateActivity.class); startActivity(intent);
Неявный вызов activity - когда мы при создании intent не указываем activity на который хотим перейти, а все настройки производим в файле manifest для activity куда будем переходить. Пример: Intent intent = new Intent("devcolibri"); startActivity(intent);
В manifest - <intent-filter>
                <action android:name="devcolibri"/>
                <category android:name="android.intent.category.DEFAULT"/>
            </intent-filter>
<action> - указывает на действие которое мы хотим выполнить. Помимо кастомной action c именем "devcolibri", существуют стандартные action.
Actions:
- ACTION_VIEW
- ACTION_EDIT
- ACTION_PICK
- ACTION_DIAI
- и т.д.
Именовать action стоит полным путем к той activity на которую мы будем переходить ("com.devcolibri.intentfilterexam.app.SiteActivity")
Имя action не обязательно должно быть уникальным. Если будут одинаковые вызовы у двух activity, то будет выведено info-окно с предложением выбора нужной activity. Пример: когда мы на телефоне запускаем видео-файл, то android показывает информационное окно с выбором нужного плеера для этого файла.
Так же можно вызывать системные activity, например - контакты ->
Intent intent = new Intent();
intent.setComponent(new ComponentName("com.android.contacts", "com.android.contacts.DialtactsContactsEntryActivity")); // указываем пакет и полный путь к activity
startActivity(intent);