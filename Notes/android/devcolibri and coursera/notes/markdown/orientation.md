##Orientation

####Landscape layout
Для того что бы переопределить позиционирование объектов на layout, необходимо добавить папку в res/ -> layout-land из которой будут браться настройки отображения activity при горизонтальном разположении экрана.
Важно помнить, что при повороте экрана и соответственно при применении нового layout-а, у нас заново создается наш layout, т.е. он не хранит сострояние.

Для того что бы узнать ориентацию экрана или другие свойства - необходимо воспользоваться (сделать сравнение) методами:
```java
Configuration configuration = getResources().getConfiguration();
if(configuration.orientation == Configuration.ORIENTATION_PORTRAIT) { 
    ... // Configuration.ORIENTATION.LANDSCAPE 
} 
```