#Fragment

Знакомимся с Fragment-ами. Ex 22
Возможность работы с фрагментами в android внесли с 3 версии. 
Фрагменты нельзя использовать без activity.
При создании класса для фрагмента необходимо унаследоваться от базового класса Fragment и переопределить метод onCreateView.
- import android.support.v4.app.Fragment; - для обратной совместимости до 3 версии
- import android.app.Fragment; - без обратной совместимости от 3 и выше
LayoutInflater - специальный менеджер компоновки, который позволяет получать доступ к нашим layout-ам с наших ресурсов.
Bundle savedInstanceState - сможем ли мы в будущем использовать данный фрагмент для подключения в activity через контейнер.
Пример:
@Override
public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.button_layout, container, false);
}
В layout xml fragment вставляется с помощью тега - <fragment/>
Пример:
<fragment android:name="com.mysuperfragment.app.ButtonFragment"
              android:id="@+id/button_fragment"
              android:layout_height="wrap_content"
              android:layout_width="match_parent"
              tools:layout="@layout/button_layout"/>

# Цикл жизни fragment-a. Ex 23
Жизненный цикл fragment-a:
1) onAttach(); - метод, вызывающийся после того как фрагмент свяжется с activity.
2) onCreate(); - метод похож на метод onCreate(); из activity, за тем исключением, что в нем нельзя использовать код, который зависит от ресурсов либо от layout-ов, потому что на этапе onCreate(); мы еще не знаем о ресурсах компонента, которые у нас находятся в нашем layout-е, мы даже не знаем какой именно layout использует данный фрагмент.
3) onCreateView(); - метод позволяет инициализировать наши компонеты, присвоить им какие-то значения с layout-a. На этом этапе мы уже имеем всю иерархию компонентов, с которыми мы можем работать.
4) onActivityCreated(); - метод вызывается после того как activity завершила свою обработку вызова метода onCreate(); В этом методе можно выполнять заключительные настройки пользовательского интерфейса до того как пользователь увидет его.
5) onStart(); - метод похож на onStart(); из activity, в момент вызова этого метода пользователь уже видет текущий фрагмент, но пока мы еще не начали взаимодействие с пользователем (уже иден наш фрагмент, но мы пока не можем с ним взаимодействовать)
6) onResume(); - выполняется после возвращения к нашему фрагменту, выполняет аналогичную логику что и в activity. После onResume(); пользователь видет наш фрагмент, он активен и с ним можно выполнять какие-то действия.
7) onPause(); - этот метод вызывается после того как пользователь уходит на какие-то другие фрагменты. onPause(); работает точно так же как и в activity - останавливает наш фрагмент. Пример применения метода - мы смотрим видео файл, уходим с фрагмента и в методе onPause(); пишем что бы проигрывание файла остановилось. 
8) onStop(); - метод по своей логике выполняет тоже самое, что и метод из activity - останавливает работу нашего фрагмента.
9) onDestroyView(); - если фрагмент находится на пути к уничтожению или сохранению, то будет вызван метод onDestroyView(); Так же в этом методе мы можем выполнить возрождение к нашему onCreateView(); вызвав в методе - setRetainInstance(true);
10) onDestroy(); - вызывается когда фрагмент больше не используется приложением. В этом методе мы можем почистить какие-то ресурсы либо удалить те объекты которые нам уже не нужно использовать в данном фрагменте.
11) onDetach(); - вызывается для того что бы отвязать фрагмент от activity и полностью завершить цикл жизни нашего фрагмента.

Сравнение жизненного цикла activity и fragment-а:
————————————————————————————————————————————————
- Created (activity) -> onAttach();
            onCreate();
            onCreateView();
            onActivityCreated();
————————————————————————————————————————————————
- Started (activity) -> onStart();
————————————————————————————————————————————————
- Resumed (activity) -> onResume();
————————————————————————————————————————————————
- Paused (activity)  -> onPause();
————————————————————————————————————————————————
- Stopped (activity) -> onStop();
————————————————————————————————————————————————
- Destroyed(activity)-> onDestroyView();
             -> onDestroy();
             -> onDetach(); 
————————————————————————————————————————————————

Для сохранения состояния нашего фрагмента (различные настройки и т.д.) используется метод onSaveInstanceState(Bundle outState); Для того что бы вернуться к сохраненному ранее состоянию необходимо вызвать метод setRetainInstance(true);               

# Динамическое добавление фрагментов. Ex 24
Для того что бы начать работать с фрагментами из MainActivity необходимо создать объект этого фрагмента.
Например:  
private OneFragment oneFragment = new OneFragment();
Для работы с фрагментами так же необходимы еще 2 класса - FragmentManager и FragmentTransaction.
Транзакции в fragment-ах очень похожи на транзакции из баз данных: открываем транзакцию -> помещаем какие-то данные в фрагменты, выполняем какие-то манипуляции -> коммит транзакции -> наши транзакции попадают в наш менеджер и пользователь видет отображение фрагмента.
Пример:
Нажимаем на кнопку и добавляется новый фрагмент.
public void onClickFragment(View view) {
        transaction = manager.beginTransaction();
        switch (view.getId()) {
            case R.id.btnAdd :
                transaction.add(R.id.container, oneFragment);
                break;
        }
        transaction.commit();
    }

# Динамическое удаление и замена фрагментов. Ex 25
Для того что бы работать с фрагментами (например проверить загружен он на данный момент или нет) необходимо использовать уникальную статическую строку в классе фрагмента.
Пример:
public static final String TAG = "TwoFragmentTag";
Метод удаления и добавления фрагмента с проверкой на существование фрагмента
public void onClickFragment(View view) {
        transaction = manager.beginTransaction();
        switch (view.getId()) {
            case R.id.btnAdd:
                if (manager.findFragmentByTag(OneFragment.TAG) == null) {
                    transaction.add(R.id.container, oneFragment, OneFragment.TAG);
                }
                break;
            case R.id.btnRemove:
                if (manager.findFragmentByTag(OneFragment.TAG) != null) {
                    transaction.remove(oneFragment);
                }
                if (manager.findFragmentByTag(TwoFragment.TAG) != null) {
                    transaction.remove(twoFragment);
                }
                break;
            case R.id.btnReplace:
                if (manager.findFragmentByTag(OneFragment.TAG) != null) {
                    transaction.replace(R.id.container, twoFragment, TwoFragment.TAG);
                }
                if (manager.findFragmentByTag(TwoFragment.TAG) != null) {
                    transaction.replace(R.id.container, oneFragment, OneFragment.TAG);
                }
                break;
        }
        if (isBackStack.isChecked()) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

# Передача данных с одного фрагмента другому. Ex 26
Для передачи данных между фрагментами используем FragmentTransaction и FragmentManager.
Пример:
1) Динамически добавляем в наш контейнер (ListLayout) фрагмент LastFragment 
private void initFragmentLast() {
        transaction = manager.beginTransaction();
        transaction.add(R.id.container, new LastFragment());
        transaction.commit();
    }

По событию «нажатие кнопки» передаем данные из одного фрагмента - другому. 
Это решение будет работать ТОЛЬКО в том случае если наш фрагмент успел инициализироваться, добавился в нашу транзакцию и пользователь его видеть у себя на дисплее.
    public void onClickButtonFragment(View view) {
        EditText editText = (EditText) findViewById(R.id.text);
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(editText.toString());
    }

2) 
Переопределяем в фрагменте метод onStart() и в нем производим все манипуляции. 
@Override
    public void onStart() {
        super.onStart();
        Button button = (Button) getActivity().findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText) getActivity().findViewById(R.id.text);
                TextView textView = (TextView) getActivity().findViewById(R.id.textView);
                textView.setText(editText.getText().toString());
            }
        });
    }