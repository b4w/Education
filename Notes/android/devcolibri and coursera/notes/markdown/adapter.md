#Adapters
# ListView и Adapters. Ex 18
ArrayAdapter является простейшим адаптером, который специально предназначен для работы с элементами списка типа ListView, Spinner и им подобным.
ListView - отображение информации на view с помощью списка.
Пример стандартной реализации adapter:
private List<String> initData() {
        List<String> list = new ArrayList<String>();
        list.add("IPhone");
        list.add("HTC");
        list.add("Sumsung");
        list.add("LG");
        return list;
    }
List<String> items = initData();
ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, items);