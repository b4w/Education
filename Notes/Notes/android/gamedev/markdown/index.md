##LibGDX

Дерево проекта в IDE состоит из (может меняться в зависимости от выбранных элементов при генерации проекта):
- android - файлы для сборки проекта под андроид
- core - главная часть проекта
- desktop - файлы для сборки проекта под desktop персию
- html - файлы для сборки проекта для html версии
- ios - файлы для сборки проекта под ios версию

**Пример:**
```java
package com.testgame;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

//Обьявление класса. Обратите внимание, что он реализует интерфейс ApplicationListener. В 
//вашей игре должен быть по крайней игре один класс, который реализует такой интерфейс.
//Чуть позже я обьясню, где этот класс использовать
public class MyTestGame implements ApplicationListener {
    //Виртуальная камера, через которую мы "видим" нашу игру
    private OrthographicCamera camera;
    //Отрисовщик спрайтов. Это такая штука, которая умеет рисовать картинки в нужном размере и в нужных местах
    //Мы будем часто его использовать
    private SpriteBatch batch;
    //Текстура. Это картинка, база для графической части игры. Ваша игра может
    //содержать множество текстур
    private Texture texture;
    //Спрайт. Это обьект, который умеет себя рисовать, и имеет определенную позицию, размеры, и другие параметры
    private Sprite sprite;

    //Метод, который инициализирует нашу игру. Этот метод вызывается самым первым, его основная задача - 
    //подготовить необходимые ресурсы
    @Override
    public void create() {      
        //Ширина и высота экрана для вывода графики. Для android - это разрешение экрана. 
        // Для десктопа - ширина и высота окна (если в полноэкранном режиме - разрешение экрана)
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        //Инициализация камеры и отрисовщика спрайтов.
        camera = new OrthographicCamera(1, h/w);
        batch = new SpriteBatch();

        //Загружаем текстуру, устанавливаем режим сглаживания "билинейная фильтрация"
        texture = new Texture(Gdx.files.internal("data/libgdx.png"));
        texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

        //Из всей текстуры вырезаем лишь часть текстуры - назовем ее регионом текстуры
        TextureRegion region = new TextureRegion(texture, 0, 0, 512, 275);

        //Создаем спрайт, устанавливаем ему размеры почти в весь экран
        sprite = new Sprite(region);
        sprite.setSize(0.9f, 0.9f * sprite.getHeight() / sprite.getWidth());
        sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
        sprite.setPosition(-sprite.getWidth()/2, -sprite.getHeight()/2);
    }

    //Этот метод вызывается, когда игра закрывается - мы очищаем ресурсы, которые не собираються сборщиком мусора
    @Override
    public void dispose() {
        batch.dispose();
        texture.dispose();
    }

    //Этот метод вызывается каждый кадр отрисовки. Заметьте, частота его вызова непостоянна - FPS может "прыгать"
    @Override
    public void render() {      
        //Очистка экрана - вызов OpenGL функций 
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        //Указываем отрисовщику спрайтов перейти в систему координат камеры
        batch.setProjectionMatrix(camera.combined);
        //Мы должны вызвать сначала этот метод...
        batch.begin();
        //...потом указать что рисовать
        sprite.draw(batch);
        //...и в самом конце - этот метод. Отрисовщик упакует и отрисует их оптимальным способом
        batch.end();
    }

    //Этот метод вызывается, когда мы изменяем размеры окна - то есть, только в desktop режиме
    @Override
    public void resize(int width, int height) {
    }

    //Когда мы на телефоне сворачиваем приложение, или нам поступает звонок - вызывается этот метод.
    //Также он вызывается перед закрытием игры на всех платформах
    @Override
    public void pause() {
    }

    //Когда мы на телефоне открываем уже запущенное приложение - вызывается этот метод
    @Override
    public void resume() {
    }
}
```


