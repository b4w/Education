*Основные команды*
```
show dbs
use newdb
show collections
```

*Для импорта в mongodb можно использовать сторонние JSON файлы*

Используем команду 
-d - указываем имя базы данных (importdb)
-c - указываем имя коллекции (import)
< - указываем ввод на файл
... - путь и название файла

mongoimport -dimportdb -cimport < /...

--

*Удаление документов*
удаляем данные из коллекции где поле "fild_name" равно 2

> db.{имя_коллекции}.remove({"fild_name" : 2})

--

*Некоторые пояснения по БД Mongo*
db - объект на текущую БД
Коллекция - свойство БД
Операции - методы коллекции
Все храниться в JSON 
В mongo shell можно использовать код JS

-- 

*Команды MongoDB*
- Вставка
```
doc={city:"Minsk", population:1837000}
db.populations.insert(doc)
db.populations.find()
{
	_id:Object("234h23"),
	city:"Minsk",
	population:183700
}
```l

Можно создавать "переменные" JSON и затем вставлять их в БД
```
newDoc={
	"_id": 1,
	"title": "test"
}

db.posts.insert(newDoc)
```
- Чтение 
```
db.collections.find(selector, fields) // возвращает массив записей cursor
db.collections.fidOne(selector, fields)  // возвращает одну запись
```

Примеры:
```
db.posts.find()
...
db.posts.findOne({_id: 1}, {title: true, _id: false})
```

Селекторы:
```
db.collection.find({field:{$gt: value}}) // больше чем
db.collection.find({field:{$lt: value}}) // меньше чем
db.collection.find({field:{$regex: regex}}) // можно использовать regex - но они сильно влияют на производительность 
```

Оператор "or"
```
db.collection.find({
    $or:[ // 
        condition_1,
        condition_2,
        ...
        condition_n
    ]
})
```

Операторы $exists $type
```
db.collection.find{field: {$exists: true/false}}
db.collection.find{field: {$type: number}}
```

Так же есть возможность поиска по массиву (смотреть документацию), по типу объекта.

Показать количество записей в коллекции
```
db.collection.count(selector) // в selector можно подставлять сложные параметры
```

- Курсоры
Так же на JS можно написать итератор, который возвратит все значения.
+ есть и другие методы у cursor
```
cursor = db.collection.find()
cursor.hasNext()
cursor.next()
```

- Обновление
нак же можно изменять только определенный индекс массива
```
db.collection.update(selector, doc, options)

db.collection.update(selector, {$inc: {field: 1}}) // увеличиваем значение поля на определенное число
db.collection.update(selector, {$set: {field: value}}) // добавляет значение, не изменяя полностью документ
db.collection.update(selector, {$unset: {field: 1}}) // удаляем указанное поле
```

- Удаление 
```
db.collection.remove(selector) // удаляет все записи, которые соответствуют данному селектору
db.collection.drop() // удаляет коллекцию целиком 
```

