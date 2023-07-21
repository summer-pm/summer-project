
## API 

## Swagger Hub


[Beercode API](https://app.swaggerhub.com/apis/MARIESDENISOVA_1/BeerCode/v1 "Beercode API") 

## OpenAPI spec

[OpenAPI ↓](#openapi)


## Version 
Текущая версия `v1`


### Получение списка задач

## `` GET /tasks ``

Параметры 
| Параметр | Обязательность | Описание | Тип данных |
| ------------- | ------------- |  ------------- | ------------- |
| level | Не обязательно | Отображение задач с определённым уровнем сложности: "easy/medium/hard" | string |
| status | Не обязательно | Отображение задач с определённым статусом: "решено/не решено" | string |
| title | Не обязательно | Отображение задачи с определённым названием | string |
| limit | Не обязательно | Максимальное количество возвращаемых элементов. По умолчанию 20 | integer |
| sort | Не обязательно | Сортировка по возрастанию номеров задач по умолчанию | integer |

Пример запроса
`` GET "<baseurl>/v1/tasks?level=easy&status=0&title=taskname&limit=20&sort=asc" ``

Ответ

```bash
{
    "tasks": [
               {
                "taskID": 1,   
                "title": "Название задачи 1",
                "level": "Уровень сложности задачи",
                "status": "решено" 
               },
               {
                "taskID": 2,
                "title": "Название задачи 2",
                "level": "Уровень сложности задачи",
                "status": "не решено"
                },
              ...
              ]
}
```

| Параметр | Описание | Тип данных |
| ------------- | ------------- |  ------------- |  
| taskID | Номер задачи, отображаемый в списке задач | integer |
| title | Название задачи, отображаемое в списке задач | string |
| level | Уровень сложности задачи, отображаемый в списке задач | string |
| status | Факт того, решена ли задача пользователем. Статусы: "решено/не решено" | string |

Коды ответа

| Код | Описание |
| ------------- | ------------- |
| 200 | ОК |
| 404 | Элемент не существует |















### Просмотр задачи

## `` GET /tasks/{id} ``


Параметры 
| Параметр | Обязательность | Описание | Тип данных |
| ------------- | ------------- |  ------------- | ------------- |
| id | Обязательно | ID задачи | int64 |

Пример запроса
`` GET "<baseurl>/v1/tasks/1111" ``

Ответ

```bash
{
  "taskID": 1,
  "title": "Задача 1",
  "description": "Описание условия задачи 1",
   "level": "Уровень сложности задачи",
  "examples": [
    {
      "input": "Входные данные примера 1",
      "output": "Ожидаемый результат примера 1",
       "explanation": "Объяснения"
    },
    {
      "input": "Входные данные примера 2",
      "output": "Ожидаемый результат примера 2",
       "explanation": "Объяснения"
    },
    {
      "input": "Входные данные примера 3",
      "output": "Ожидаемый результат примера 3",
       "explanation": "Объяснения"
    }
  ],
  "status": "не решено",
   "languages":[
    {
    "language": "name",
     "solutionTemplate": "class Solution {...}",
     },
    {
    "language": "name",
     "solutionTemplate": "class Solution {...}",
     }
     ...
   ]
   "timeRestrict": "ограничения по времени",
   "volumeRestrict": "ограничения по объёму занимаемой памяти"
}

```

| Параметр | Описание | Тип данных |
| ------------- | ------------- |  ------------- |  
| taskID | Номер задачи, отображаемый в списке задач | int64 |
| title | Название задачи, отображаемое в списке задач | string |
| description | Описание условий задачи | string |
| level | Уровень сложности задачи, отображаемый в списке задач. Уровни сложности: "easy/medium/hard" | string |
| input | Строка с входными данными тестового кейса | string |
| output | Строка с выходными данными тестового кейса | string |
| explanation | Строка с пояснением данных тестового кейса | string |
| language | Названия возможного ЯП задачи | string |
| status | Факт того, решена ли задача пользователем/ Статусы: "решено/не решено" | string |
| solutionTemplate | Скелет кода для пользователя | string |
| timeRestrict | Ограничение по времени | string |
| volumeRestrict | Ограничение по памяти | string |


Коды ответа

| Код | Описание |
| ------------- | ------------- |
| 200 | ОК |
| 403 | Нет прав доступа |
| 404 | Элемент не существует |







### Отправка попытки решения

## `` POST /tasks/{id}/attempts ``


Параметры 
| Параметр | Обязательность | Описание | Тип данных |
| ------------- | ------------- |  ------------- | ------------- |
| id | Обязательно | ID попытки | int64 |

Пример запроса
`` POST "<baseurl>/v1/tasks/1111/attempts" ``

Тело запроса

```bash
{
  "userID": 0,
  "language": "Java",
  "code": "class Solution{...}"
}
```
| Параметр | Описание | Тип данных |
| ------------- | ------------- |  ------------- |  
| userID | Идентификатор пользователя | int64 |
| language | Язык программирования | string |
| code | Решение пользователя | string |

Ответ

```bash
{
  "attemptID": 1111,
  "userID": 3333,
  "language": "Java"
}
```

| Параметр | Описание | Тип данных |
| ------------- | ------------- |  ------------- |  
| attemptID | Идентификатор пользователя | int64 |
| userID | Идентификатор пользователя | int64 |
| language | Язык программирования | string |

Коды ответа

| Код | Описание |
| ------------- | ------------- |
| 201 | Попытка создана |
| 400 | Некорректный запрос |
| 404 | Элемент не существует |





### Просмотр попыток решения

## `` GET /tasks/{id}/attempts ``

Параметры 
| Параметр | Обязательность | Описание | Тип данных |
| ------------- | ------------- |  ------------- | ------------- |
| id | Обязательно | ID задачи | int64 |
| limit | Не обязательно | Максимальное количество возвращаемых элементов. По умолчанию 10 | integer |
| sort | Не обязательно | Сортировка по возрастанию. Сначала новые решения | integer |

Пример запроса
`` GET "<baseurl>/v1/tasks/1111/attempts/1111?limit=10&sort=asc"  ``

Ответ

```bash
{
    "taskID": 1,
    "title": "Название задачи",
    "level": "Уровень сложности задачи",
    "attempts": [
               {
                "status": "решено",
                "datetime": "дата и время",
                "language": "Java",
                "code": "class Solution {...}",
                "timeResult": "результат попытки по времени",
                "volumeResult": "результат попытки по объёму занимаемой памяти"
               },
               {
                "status": "решено",
                "datetime": "дата и время",
                "language": "Python",
                "code": "class Solution {...}",
                "timeResult": "результат попытки по времени",
                 "volumeResult": "результат попытки по объёму занимаемой памяти"
                },
              ...
              ]
}
```
| Параметр | Описание | Тип данных |
| ------------- | ------------- |  ------------- |  
| taskID | Номер задачи, отображаемый в списке задач | int64 |
| title | Название задачи, отображаемое в списке задач | string |
| level | Уровень сложности задачи, отображаемый в списке задач. Уровни сложности: "easy/medium/hard" | string |
| language | Название ЯП задачи | string |
| status | Факт того, решена ли задача пользователем/ Статусы: "решено/не решено" | string |
| datetime | Дата и время попытки решения | string |
| code | Код пользователя | string |
| timeResult | Результат попытки по времени | string |
| volumeResult | Результат попытки по объёму занимаемой памяти | string |


Коды ответа

| Код | Описание |
| ------------- | ------------- |
| 200 | ОК |
| 403 | Нет прав доступа |
| 404 | Элемент не существует |






### Просмотр попытки решения

## `` GET /attempts/{id} ``

Параметры 
| Параметр | Обязательность | Описание | Тип данных |
| ------------- | ------------- |  ------------- | ------------- |
| id | Обязательно | ID попытки решения | int64 |


Пример запроса
`` GET "<baseurl>/v1/attempts/1111"  ``

Ответ

```bash
{
    "userID": 1,
    "taskID": 1,
    "title": "Название задачи",
    "level": "Уровень сложности задачи",
     "status": "решено",
     "datetime": "дата и время",
     "language": "Java",
     "code": "class Solution {...}",
     "timeResult": "результат попытки по времени",
     "volumeResult": "результат попытки по объёму занимаемой памяти"
}
```
| Параметр | Описание | Тип данных |
| ------------- | ------------- |  ------------- |  
| userID | ID пользователя | int64 |
| taskID | Номер задачи, отображаемый в списке задач | int64 |
| title | Название задачи, отображаемое в списке задач | string |
| level | Уровень сложности задачи, отображаемый в списке задач. Уровни сложности: "easy/medium/hard" | string |
| language | Название ЯП задачи | string |
| status | Факт того, решена ли задача пользователем/ Статусы: "решено/не решено" | string |
| datetime | Дата и время попытки решения | string |
| code | Код пользователя | string |
| timeResult | Результат попытки по времени | string |
| volumeResult | Результат попытки по объёму занимаемой памяти | string |


Коды ответа

| Код | Описание |
| ------------- | ------------- |
| 200 | ОК |
| 403 | Нет прав доступа |
| 404 | Элемент не существует |







### Общая лента

## `` GET /feed ``

Параметры 
| Параметр | Обязательность | Описание | Тип данных |
| ------------- | ------------- |  ------------- | ------------- |
| limit | Не обязательно | Максимальное количество возвращаемых элементов. По умолчанию 20 | integer |
| sort | Не обязательно | Сортировка по возрастанию. Сначала новые посты | integer |
| title | Не обязательно | Отображение задачи с определённым названием | string |

Пример запроса
`` GET "<baseurl>/v1/feed?limit=20&sort=asc&title=taskname"  ``


Ответ

```bash
{
    "posts": [
               {
                "username": "Имя пользователя",
                "userImage": "ссылка на фото",
                "timeOfPost": "дата и вермя поста",
                "commentary": "Комментарий пользователя",
                "title": "Название задачи 1",
                "level": "Уровень сложности задачи"
               },
               {
                "username": "Имя пользователя",
                "timeOfPost": "дата и вермя поста",
                 "commentary": "Комментарий пользователя",
                "title": "Название задачи 2",
                "level": "Уровень сложности задачи"
                },
              ...
              ]
}
```


| Параметр | Описание | Тип данных |
| ------------- | ------------- |  ------------- |  
| username | Имя пользователя | string |
| userImage | Фотография пользователя | string |
| timeOfPost | Время публикации поста | string |
| commentary | Комментарий пользователя к посту | string |
| title | Название задачи | string |
| level | Уровень сложности задачи, отображаемый в списке постов. Уровни сложности: "easy/medium/hard" | string |



Коды ответа

| Код | Описание |
| ------------- | ------------- |
| 200 | ОК |
| 403 | Нет прав доступа |
| 404 | Элемент не существует |







### Пост из ленты

## `` GET /posts/{id} ``

Параметры 
| Параметр | Обязательность | Описание | Тип данных |
| ------------- | ------------- |  ------------- | ------------- |
| id | Обязательно | ID поста | int64 |

Пример запроса
`` GET "<baseurl>/v1/posts/1111"  ``

Ответ

```bash
{
    "posts": [
               {
                "username": "Имя пользователя",
                "userImage": "ссылка на фото",
                "timeOfPost": "дата и вермя поста",
                "commentary": "Комментарий пользователя",
                "title": "Название задачи 1",
                "level": "Уровень сложности задачи",
                "code": "текст решения",
                 "timeResult": "время выполнения попытки",
                "volumeResult": "объём попытки"
               },
               {
                "username": "Имя пользователя",
                "userImage": "ссылка на фото",
                "timeOfPost": "дата и вермя поста",
                 "commentary": "Комментарий пользователя",
                "title": "Название задачи 2",
                "level": "Уровень сложности задачи",
                 "code": "текст решения",
                 "timeResult": "время выполнения попытки",
                "volumeResult": "объём попытки"
                },
              ...
              ]
}
```
| Параметр | Описание | Тип данных |
| ------------- | ------------- |  ------------- |  
| username | Имя пользователя | string |
| userImage | Фотография пользователя | string |
| timeOfPost | Время публикации поста | string |
| commentary | Комментарий пользователя к посту | string |
| title | Название задачи | string |
| level | Уровень сложности задачи, отображаемый в списке постов. Уровни сложности: "easy/medium/hard" | string |
| code | Код пользователя | string |
| timeResult | Результат попытки по времени | string |
| volumeResult | Результат попытки по объёму занимаемой памяти | string |

Коды ответа

| Код | Описание |
| ------------- | ------------- |
| 200 | ОК |
| 404 | Пост не существует |




### Отправка поста в ленту

## `` POST /posts ``


Пример запроса
`` POST "<baseurl>/v1/posts"  ``

Тело запроса

```bash
{
   
  "attemptID": 0,
  "postID": 0,
  "userID": 0,
  "timeOfPost": "время создания поcта",
  "commentary": "комментарий к посту",

}
```

| Параметр | Описание | Тип данных |
| ------------- | ------------- |  ------------- | 
| attemptID | Идентификатор комнаты | int64 |
| postID | Идентификатор поста | int64 |
| userID | Идентификатор пользователя | int64 |
| timeOfPost | Время публикации поста | string |
| commentary | Комментарий пользователя к посту | string |


Ответ

```bash
{
  "attemptID": 0,
  "postID": 0,
  "userID": 0,
  "timeOfPost": "время создания поcта",
  "commentary": "комментарий к посту",
}
```

| Параметр | Описание | Тип данных |
| ------------- | ------------- |  ------------- | 
| attemptID | Идентификатор комнаты | int64 |
| postID | Идентификатор поста | int64 |
| userID | Идентификатор пользователя | int64 |
| timeOfPost | Время публикации поста | string |
| commentary | Комментарий пользователя к посту | string |


Коды ответа

| Код | Описание |
| ------------- | ------------- |
| 201 | Пост отправлен |
| 400 | Некорректные данные |
| 401 | Необходима авторизация |







### Список чатов

## `` GET /rooms ``

Параметры 
| Параметр | Обязательность | Описание | Тип данных |
| ------------- | ------------- |  ------------- | ------------- |
| limit | Не обязательно | Максимальное количество возвращаемых элементов. По умолчанию 10 | integer |
| sort | Не обязательно | Сортировка по возрастанию. Сначала комнаты с новыми сообщениями | integer |
| interlocutor | Не обязательно | Отображение в списке диалога с определённым собеседником | string |

Пример запроса
`` GET "<baseurl>/v1/rooms?limit=20&sort=asc&interlocutor=username"  ``


Ответ

```bash
{
    "rooms": [
               {
                "interlocutor": "Имя пользователя",
                 "userImage": "ссылка на фото",
                "lastMessage": "дата и вермя поста",
                "dateTime": "Комментарий пользователя"
                 "status": "прочитано",
                 "notifications": 0
               },
               {
                "interlocutor": "Имя пользователя",
                 "userImage": "ссылка на фото",
                "lastMessage": "дата и вермя поста",
                "dateTime": "Комментарий пользователя",
                "status": "прочитано",
                 "notifications": 0
                },
              ...
              ]
}
```


| Параметр | Описание | Тип данных |
| ------------- | ------------- |  ------------- |  
| interlocutor | Юзернейм собеседника | string |
| userImage | Фотография профиля собеседника | string |
| lastMessage | Текст последнего сообщения в диалоге | string |
| dateTime | Дата или время последнего сообщения | string |
| status | Статус сообщения (доставлено/не прочитано/прочитано) | string |
| notifications | Дата или время последнего сообщения | integer |

Коды ответа

| Код | Описание |
| ------------- | ------------- |
| 200 | ОК |
| 403 | Нет прав доступа |





### Создание и хранение чата

## `` POST /rooms ``


Пример запроса
`` POST "<baseurl>/v1/rooms"  ``


Тело запроса

```bash
{
   "roomID": 1111,
    "users": ["userID", "userID"]
}
```

| Параметр | Описание | Тип данных |
| ------------- | ------------- |  ------------- | 
| roomID | Идентификатор комнаты | int64 |
| userID | Идентификатор пользователя | string |


Ответ

```bash
{
   "roomID": 1111
}
```

| Параметр | Описание | Тип данных |
| ------------- | ------------- |  ------------- |  
| roomID | Идентификатор комнаты | int64 |


Коды ответа

| Код | Описание |
| ------------- | ------------- |
| 201 | Чат создан |
| 400 | Некорректный запрос |





### Показ определённого чата

## `` GET /rooms/{id} ``


Параметры 
| Параметр | Обязательность | Описание | Тип данных |
| ------------- | ------------- |  ------------- | ------------- |
| id | Обязательно | ID чата | string |

Пример запроса
`` GET "<baseurl>/v1/rooms/1111"  ``

Ответ

```bash
{
    "users": ["userID", "userID"],
     "messages"[
      "message_ID": "111",
     "room_ID": "1111",
     "sender": "1111",
     "userImage": "ссылка на фото",
     "content [
           "message": "message text"
     ]",
    "dateTime": "дата и время отправки сообщения",
    "status": "прочитано"
     ]
}
```

| Параметр | Описание | Тип данных |
| ------------- | ------------- |  ------------- |  
| userID | Идентификатор пользователя | string |
| message_ID | ID сообщения | string  | 
| room_ID | ID комнаты | string  |
| sender | ID отправителя | string  |
| userImage | Фотография профиля отправителя | string |
| message | Текст сообщения | string |
| dateTime | Дата и время отправки сообщения | string  |
| status | Статус сообщения: "доставлено/не прочитано/прочитано" |  string |


Коды ответа

| Код | Описание |
| ------------- | ------------- |
| 200 | ОК |
| 404 | Нельзя отобразить чат, которого нет |
















### Удаление чата

## `` DELETE /rooms/{id} ``


Параметры 
| Параметр | Обязательность | Описание | Тип данных |
| ------------- | ------------- |  ------------- | ------------- |
| id | Обязательно | ID комнаты | string |

Ответ

```bash
{
   "isDeleted": true
}
```

| Параметр | Описание | Тип данных |
| ------------- | ------------- |  ------------- |  
| isCreated | Флаг, что комната удалена | boolean |


Коды ответа

| Код | Описание |
| ------------- | ------------- |
| 200 | ОК |
| 404 | Несуществующая комната не может быть удалена |




### Создание сообщения

## `` POST room/{id}/messages ``
Параметры 
| Параметр | Обязательность | Описание | Тип данных |
| ------------- | ------------- |  ------------- | ------------- |
| id | Обязательно | ID чата | int64 |

Тело запроса

```bash
{
   "message_ID": "1111",
   "sender": "1111",
   "content [
           "message": "message text"
    ]",
   "dateTime": "дата и время отправки сообщения",
   "status": "прочитано"
}
```

| Параметр | Описание | Тип данных |
| ------------- | ------------- |  ------------- | 
| message_ID | ID сообщения | string  | 
| sender | ID отправителя | string  |
| message | Текст сообщения | string |
| dateTime | Дата и время отправки сообщения | string  |
| status | Статус сообщения: "доставлено/не прочитано/прочитано" |  string |



Ответ

```bash
{
   "message_ID": "111",
   "room_ID": "1111",
   "sender": "1111",
   "content [
           "message": "message text"
    ]",
   "dateTime": "дата и время отправки сообщения",
   "status": "прочитано"
}
```

| Параметр | Описание | Тип данных |
| ------------- | ------------- |  ------------- | 
| message_ID | ID сообщения | string  | 
| room_ID | ID комнаты | string  |
| sender | ID отправителя | string  |
| message | Текст сообщения | string |
| dateTime | Дата и время отправки сообщения | string  |
| status | Статус сообщения: "доставлено/не прочитано/прочитано" |  string |



Коды ответа

| Код | Описание |
| ------------- | ------------- |
| 201 | Сообщение создано и сохранено |
| 404 | Сообщение нельзя отправить в несуществующей комнате или несуществующим пользователем |




### Отображение сообщений

## `` GET /messages ``

Параметры 
| Параметр | Обязательность | Описание | Тип данных |
| ------------- | ------------- |  ------------- | ------------- |
| room | Не обязательно | ID комнаты | string |
| user | Не обязательно | ID пользователя | string |

Пример запроса
`` GET "<baseurl>/v1/messages?room=chatRoomId&user=userID"  ``

Ответ

```bash
{
  "messages"[
     "message_ID": 1111,
     "room_ID": "1111",
     "sender": "1111",
     "userImage": "ссылка на фото",
     "content [
           "message": "message text"
     ]",
    "dateTime": "дата и время отправки сообщения",
    "status": "прочитано"
     ]
}
```

| Параметр | Описание | Тип данных |
| ------------- | ------------- |  ------------- | 
| message_ID | ID сообщения | int64 | 
| room_ID | ID комнаты | string  |
| sender | ID отправителя | string  |
| userImage | Фотография профиля отправителя | string |
| message | Текст сообщения | string |
| dateTime | Дата и время отправки сообщения | string  |
| status | Статус сообщения: "доставлено/не прочитано/прочитано" |  string |

Коды ответа

| Код | Описание |
| ------------- | ------------- |
| 200 | ОК |
| 404 | Нельзя отобразить сообщения, если чат или собеседник не существует |






### Показ определённого сообщения

## `` GET /messages/{id} ``

Параметры 
| Параметр | Обязательность | Описание | Тип данных |
| ------------- | ------------- |  ------------- | ------------- |
| id | Обязательно | ID сообщения | int64 |


Пример запроса
`` GET "<baseurl>/v1/messages/1111"  ``

Ответ

```bash
{
     "message_ID": 1111,
     "room_ID": "1111",
     "sender": 1111,
     "userImage": "ссылка на фото",
     "content" [
           "message": "message text"
     ],
    "dateTime": "дата и время отправки сообщения",
    "status": "прочитано"
}
```

| Параметр | Описание | Тип данных |
| ------------- | ------------- |  ------------- | 
| message_ID | ID сообщения | int64  | 
| room_ID | ID комнаты | string  |
| sender | ID отправителя | int64  |
| userImage | Фотография профиля отправителя | string |
| message | Текст сообщения | string |
| dateTime | Дата и время отправки сообщения | string  |
| status | Статус сообщения: "доставлено/не прочитано/прочитано" |  string |

Коды ответа

| Код | Описание |
| ------------- | ------------- |
| 200 | ОК |
| 404 | Нельзя отобразить сообщения, если оно не существует |










### Редактирование сообщения

## `` PUT /messages/{id} ``

Параметры 
| Параметр | Обязательность | Описание | Тип данных |
| ------------- | ------------- |  ------------- | ------------- |
| id | Обязательно | ID сообщения | int64 |


Пример запроса
`` PUT "<baseurl>/v1/messages/1111"  ``


Тело запроса

```bash
{
  "message_ID": 1111,
     "room_ID": "1111",
     "sender": 1111,
     "content" [
           "message": "message text"
     ],
    "dateTime": "дата и время отправки сообщения",
    "status": "прочитано"
}
```
| Параметр | Описание | Тип данных |
| ------------- | ------------- |  ------------- |  
| message_ID | ID сообщения | int64 | 
| sender | ID отправителя | string  |
| message | Текст сообщения | string |
| dateTime | Дата и время отправки сообщения | string  |
| status | Статус сообщения: "доставлено/не прочитано/прочитано" |  string |

Ответ

```bash
{
     "message_ID": 111,
     "room_ID": "1111",
     "sender": 1111,
     "content" [
           "message": "message text"
     ],
    "dateTime": "дата и время отправки сообщения",
    "status": "прочитано"
}
```
| Параметр | Описание | Тип данных |
| ------------- | ------------- |  ------------- |  
| message_ID | ID сообщения | int64  | 
| sender | ID отправителя | string  |
| message | Текст сообщения | string |
| dateTime | Дата и время отправки сообщения | string  |
| status | Статус сообщения: "доставлено/не прочитано/прочитано" |  string |



Коды ответа

| Код | Описание |
| ------------- | ------------- |
| 201 | Попытка создана |
| 404 | Нельзя отредактировать несуществующий элемент |























### Удаление сообщения

## `` DELETE /messages/{id} ``


Параметры 
| Параметр | Обязательность | Описание | Тип данных |
| ------------- | ------------- |  ------------- | ------------- |
| id | Обязательно | ID сообщения | int64 |


Пример запроса
`` DELETE "<baseurl>/v1/messages/1111"  ``

Ответ

```bash
{
   "isDeleted": true
}
```

| Параметр | Описание | Тип данных |
| ------------- | ------------- |  ------------- |  
| isCreated | Флаг, что сообщение удалено | boolean |


Коды ответа

| Код | Описание |
| ------------- | ------------- |
| 200 | ОК |
| 404 | Несуществующее сообщение не может быть удалено |













### Вход

## `` POST auth/signin ``

Тело запроса

```bash
{
   
  "usernameOrEmail": "почта или имя пользователя",
  "password": "пароль"

}
```

| Параметр | Описание | Тип данных |
| ------------- | ------------- |  ------------- | 
| usernameOrEmail | Почта или имя пользователя | string  | 
| password | Пароль | string  |




Ответ

```bash
{
  "acessToken": "string",
  "refreshToken": "string",
  "time": "string"
}
```

| Параметр | Описание | Тип данных |
| ------------- | ------------- |  ------------- | 
| acessToken | ID сообщения | string  | 
| refreshToken | ID комнаты | string  |
| time | ID отправителя | string  |



Коды ответа

| Код | Описание |
| ------------- | ------------- |
| 201 | ОК. Возврат токена |
| 400 | Некорректные данные |
| 401 | Пользователь незарегистрирован |















### Регистрация

## `` POST auth/signup ``

Тело запроса

```bash
{
  "username": "имя пользователя",
  "email": "почта пользователя",
  "password": "пароль"
}
```

| Параметр | Описание | Тип данных |
| ------------- | ------------- |  ------------- | 
| username | Имя пользователя | string  | 
| email | Почта пользователя | string  | 
| password | Пароль | string  |



Ответ

```bash
{
  "acessToken": "string",
  "refreshToken": "string",
  "time": "string"
}
```

| Параметр | Описание | Тип данных |
| ------------- | ------------- |  ------------- | 
| acessToken | ID сообщения | string  | 
| refreshToken | ID комнаты | string  |
| time | ID отправителя | string  |


Коды ответа

| Код | Описание |
| ------------- | ------------- |
| 201 | ОК. Возврат токена |
| 400 | Некорректные данные|
| 409 | Пользователь уже зарегистрирован |



# OpenAPI

```bash
openapi: 3.0.0
info:
  title: Swagger Beercode - OpenAPI 3.0
  description: Beercode OpenAPI
  version: v1
servers:
  # Added by API Auto Mocking Plugin
  - description: SwaggerHub API Auto Mocking
    url: https://virtserver.swaggerhub.com/MARIESDENISOVA_1/BeerCode/v1
  - url: http://localhost:8080/api/v1
    description: Dev server  
tags:
  - name: Tasks
    description: "Методы задач"
  - name: Rooms
    description: "Методы чатов"
  - name: Messages
    description: "Методы сообщений"
  - name: Auth
    description: "Методы авторизации"
  - name: Feed
    description: "Методы ленты, постов"
  - name: User
    description: "Методы пользователя"
  - name: Info
    description: "Методы вытаскивания информации"
paths:
  /tasks:
    get:
      summary: Получение списка задач
      tags: 
        - Tasks
      description: Возвращает список задач, удовлетворяющих указанным параметрам.
      parameters:
        - name: level
          in: query
          description: Отображение задач с определенным уровнем сложности
          schema:
            type: string
          example: easy
        - name: status
          in: query
          description: Отображение задач с определенным статусом
          schema:
            type: string
          example: решено
        - name: title
          in: query
          description: Отображение задачи с определенным названием
          schema:
            type: string
          example: taskname
        - name: limit
          in: query
          description: Максимальное количество возвращаемых элементов
          schema:
            type: integer
            minimum: 1
            maximum: 100
            default: 20
        - name: sort
          in: query
          description: Порядок сортировки задач (возрастание или убывание)
          schema:
            type: string
            enum: [asc, desc]
            default: asc
      responses:
        '200':
          description: Успешный ответ
          content:
            application/json:
              schema:
                type: object
                properties:
                  tasks:
                    type: array
                    items:
                      $ref: '#/components/schemas/Task'
        '404':
          description: Элемент не найден

  /tasks/{id}:
    get:
      summary: Предварительный просмотр задачи
      tags: 
        - Tasks
      description: Возвращает информацию о задаче по указанному идентификатору.
      parameters:
        - name: id
          in: path
          description: Идентификатор задачи
          required: true
          schema:
            type: integer
            format: int64
      responses:
        '200':
          description: Успешный ответ
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/TaskDetails'
        '403':
          description: Доступ запрещен
        '404':
          description: Элемент не найден

  /tasks/{id}/attempts:
    post:
      summary: Отправка попытки решения задачи
      tags:
        - Tasks
      description: Позволяет пользователю отправить попытку решения задачи на определенном языке программирования
      parameters:
        - name: id
          in: path
          description: Идентификатор задачи
          required: true
          schema:
            type: integer
            format: int64
      requestBody:
        required: true
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/Attempt'
      responses:
        '201':
          description: Успешно создано
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/AttemptCreated'
        '400':
          description: Некорректный запрос
        '403':
          description: Доступ запрещен
        '404':
          description: Задача не найдена
    get:
      summary: Просмотр попытки решения задачи
      tags:
        - Tasks
      description: Возвращает информацию о попытках решения задачи для указанной задачи.
      parameters:
        - name: id
          in: path
          description: Идентификатор задачи
          required: true
          schema:
            type: integer
            format: int64
        - name: limit
          in: query
          description: Максимальное количество возвращаемых элементов
          schema:
            type: integer
            minimum: 1
            default: 10
        - name: sort
          in: query
          description: Порядок сортировки попыток (возрастание или убывание)
          schema:
            type: string
            enum: [asc, desc]
            default: asc
      responses:
        '200':
          description: Успешный ответ
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/TaskAttempts'
        '403':
          description: Доступ запрещен
        '404':
          description: Элемент не найден



  
          
  /attempts/{id}:
    get:
      summary: Просмотр попытки
      tags:
        - Tasks
      parameters:
        - name: id
          in: path
          description: Идентификатор попытки
          required: true
          schema:
            type: integer
            format: int64
      responses:
        '200':
          description: OK. Подробности попытки успешно получены.
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/AttemptDetails'
        '403':
          description: Доступ запрещен. Пользователь не имеет права просматривать попытку.
        '404':
          description: Указанная попытка не существует.

  /feed:
    get:
      summary: Общая лента
      tags:
        - Feed
      description: Возвращает список постов в общей ленте.
      parameters:
        - name: limit
          in: query
          description: Максимальное количество возвращаемых элементов. По умолчанию - 20.
          schema:
            type: integer
            minimum: 1
        - name: sort
          in: query
          description: Сортировка  (Сначала - новые посты)
          schema:
            type: string
            enum: [asc, desc]
            default: asc
          required: false
        - name: title
          in: query
          description: Отображение постов с определенным заголовком.
          schema:
            type: string
      responses:
        '200':
          description: Успешный ответ
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/FeedPosts'
        '404':
          description: Элемент не найден
  
  /posts/{id}:
    get:
      summary: Получение поста
      tags:
        - Feed
      description: Показывает пост из ленты
      parameters:
        - name: id
          in: path
          description: ID поста
          required: true
          schema:
            type: integer
            format: int64
      responses:
        '200':
          description: OK. Возвращает определённый пост.
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/Post'
        '404':
          description: Невозможно отобразить несуществующий пост.
  
  /posts:
    post:
      summary: Опубликовать пост
      tags:
        - Feed
      description: Публикация поста
      requestBody:
        required: true
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/NewPost'
      responses:
        '201':
          description: Успешно создано
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/NewPost'
        '400':
          description: Некорректные данные
        '401':
          description: Необходима авторизация
          
  /users/{id}:
    get:
      summary: Профиль пользователя
      tags:
        - User
      description: Показывает профиль пользователя
      parameters:
        - name: id
          in: path
          description: ID пользователя
          required: true
          schema:
            type: integer
            format: int64
      responses:
        '200':
          description: OK. Возвращает профиль определённого пользователя.
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/User'
        '401':
          description: Необходима авторизация
        '404':
          description: Невозможно отобразить несуществующего пользователя.
          
  /user/{id}/settings:
    get:
      summary: Настройки профиля пользователя
      tags:
        - User
      description: Показывает настройки профиля пользователя
      parameters:
        - name: id
          in: path
          description: ID пользователя
          required: true
          schema:
            type: integer
            format: int64
      responses:
        '200':
          description: OK. Возвращает профиль определённого пользователя.
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/UserSettings'
        '400':
          description: --
    put:
      summary: Обновление настроек проофиля
      tags:
        - User
      description: Обновляет настройки профиля пользователя
      parameters:
        - name: id
          in: path
          description: ID пользователя
          required: true
          schema:
            type: integer
            format: int64
      requestBody:
        required: true
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/UserSettings'
      responses:
        '200':
          description: OK. Возвращает настройки профиля определённого пользователя.
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/UserSettings'
        '400':
          description: Введены некорректные данные
      
  /rooms:
    post:
      summary: Создание и сохранение чата
      tags:
        - Rooms
      requestBody:
        required: true
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/Chat'
      responses:
        '201':
          description: Чат успешно создан и сохранен.
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/ChatCreated'
        '400':
          description: Неверный запрос. Чат не может быть создан с менее чем 2 пользователями
    get:
      summary: Список комнат
      tags:
      - Rooms
      parameters:
        - name: limit
          in: query
          description: Количество чатов в списке
          schema:
            type: integer
            default: 10
          required: false
        - name: sort
          in: query
          description: Сортировка 
          schema:
            type: string
            enum: [asc, desc]
            default: asc
          required: false
        - name: interlocutor
          in: query
          description: Юзернейм собеседника
          schema:
            type: string
          required: false
      responses:
        '200':
          description: Успешно, список чатов возвращён
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/Chats'
        '403':
         description: Access denied

  /rooms/{id}:
    get:
      summary: Получение определенного чата
      tags:
        - Rooms
      parameters:
        - name: id
          in: path
          description: ID чата
          required: true
          schema:
            type: string
      responses:
        '200':
          description: OK. Возвращает указанный чат.
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/ChatDetails'
        '404':
          description: Невозможно отобразить несуществующий чат.
    delete:
      summary: Удаление чата
      tags:
        - Rooms
      parameters:
        - name: id
          in: path
          description: ID чата
          required: true
          schema:
            type: string
      responses:
        '200':
          description: Успешное удаление чата
          content:
            application/json:
              schema:
                type: object
                properties:
                  isDeleted:
                    type: boolean
                    description: Флаг, что чат удалён
        '404':
          description: НЕльзя удалить несуществующий чат

  /rooms/{id}/messages:
    post:
      summary: Создание сообщения
      tags:
        - Messages
      parameters:
        - name: id
          in: path
          description: ID комнаты
          required: true
          schema:
            type: string
      requestBody:
        required: true
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/Message'
      responses:
        '201':
          description: Сообщение создано и успешно сохранено.
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/Message'
        '404':
          description: Невозможно отправить сообщение в несуществующую комнату или несуществующего пользователя.


  
         
  /messages/{id}:
    get:
      summary: Возврат конкретного сообщения
      tags: 
      - Messages
      parameters:
        - name: id
          in: path
          description: ID сообщения
          required: true
          schema:
            type: integer
            format: int64
      responses:
        '200':
          description: OK. Показ сообщения.
          content:
            application/json:
              schema:
               $ref: '#/components/schemas/Message'
        '404':
          description: Нельзя отобразить несуществующее сообщение
    put:
      summary: Редактирование сообщения
      tags:
      - Messages
      parameters:
        - name: id
          in: path
          description: ID сообщения
          required: true
          schema:
            type: integer
            format: int64
      requestBody:
        required: true
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/Message'
      responses:
        '200':
          description: Успешное обновление
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/Message'
        '404':
          description: НЕльзя обновить несуществующее сообщение
    delete:
      summary: Удаление сообщения
      tags:
      - Messages
      parameters:
        - name: id
          in: path
          description: ID сообщения
          required: true
          schema:
            type: integer
            format: int64
      responses:
        '200':
          description: OK. Сообщение успешно удалено
          content:
            application/json:
              schema:
                type: object
                properties:
                  isDeleted:
                    type: boolean
        '404':
          description: Несуществующее сообщение не может быть удалено
  /messages:
    get:
      summary: Получение сообщения
      tags:
      - Messages
      parameters:
        - name: room
          in: query
          description: ID чата
          required: false
          schema:
            type: string
        - name: user
          in: query
          description: ID пользователя
          required: false
          schema:
            type: string
      responses:
        '200':
          description: OK. Возврат сообщения
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/Message'
        '404':
          description: Нельзя отобразить сообщение, если пользователь или чат не существуют

  /auth/signin:
    post:
      summary: Вход
      tags:
      - Auth
      requestBody:
        required: true
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/SignIn'
      responses:
        '201':
          description: OK. Возврат токена
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/Token'
        '400':
          description: Введены некорректные данные
        '401':
          description: Пользователь не зарегистрирован
        
  
  /auth/signup:
    post:
      summary: Регистрация
      tags:
      - Auth
      requestBody:
        required: true
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/SignUp'
      responses:
        '201':
          description: OK. Возврат токена
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/Token'
        '400':
          description: Введены некорректные данные
        '409':
          description: Пользователь уже зарегистрирован
          
  /info/github:
    post:
      summary: Вытягивание информации с гитхаба
      tags:
      - Info
      requestBody:
        required: true
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/InfoGitHub'
      responses:
        '201':
          description: OK. Занесение данных
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/InfoGitHub'
        '400':
          description: Введены некорректные данные
  
  /info/google:
    post:
      summary: Вытягивание информации из гугла
      tags:
      - Info
      requestBody:
        required: true
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/InfoGoogle'
      responses:
        '201':
          description: OK. Занесение данных
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/InfoGoogle'
        '400':
          description: Введены некорректные данные
      
      
      
components:
  schemas:
    
    Task:
      type: object
      properties:
        taskID:
          type: integer
          format: int64
          description: Идентификатор задачи
        title:
          type: string
          description: Название задачи
        level:
          type: string
          description: Уровень сложности задачи
        status:
          type: string
          description: Статус задачи
    TaskDetails:
      allOf:
        - $ref: '#/components/schemas/Task'
        - type: object
          properties:
            description:
              type: string
              description: Описание задачи
            examples:
              type: array
              items:
                type: object
                properties:
                  input:
                    type: string
                    description: Входные данные примера
                  output:
                    type: string
                    description: Ожидаемый результат примера
                  explanation:
                    type: string
                    description: Объяснение примера
            languages:
              type: array
              items:
                type: object
                properties:
                  language:
                    type: string
                    description: Название языка программирования
                  solutionTemplate:
                    type: string
                    description: Шаблон решения для задачи
            timeRestrict:
              type: string
              description: Ограничение по времени для задачи
            volumeRestrict:
              type: string
              description: Ограничение по объему для задачи
              
              
    Attempt:
      type: object
      properties:
        userID:
          type: integer
          format: int64
          description: Идентификатор пользователя
        language:
          type: string
          description: Название языка программирования
        code:
          type: string
          description: Решение
          
          
    AttemptCreated:
      type: object
      properties:
        attemptID:
          type: integer
          format: int64
          description: ID созданной попытки
        userID:
          type: integer
          format: int64
          description: Идентификатор пользователя
        language:
          type: string
          description: Название языка программирования
    TaskAttempts:
      type: object
      properties:
        taskID:
          type: integer
          format: int64
          description: Идентификатор задачи
        title:
          type: string
          description: Название задачи
        level:
          type: string
          description: Уровень сложности задачи
        attempts:
          type: array
          items:
            type: object
            properties:
              status:
                type: string
                description: Статус попытки
              date:
                type: string
                description: Дата попытки
              language:
                type: string
                description: Язык программирования, использованный в попытке
              code:
                type: string
                description: Код пользователя
              timeResult:
                type: string
                description: Результат попытки по времени
              volumeResult:
                type: string
                description: Результат попытки по объему
                
                
    AttemptDetails:
      type: object
      properties:
        taskID:
          type: integer
          format: int64
        title:
          type: string
        level:
          type: string
        status:
          type: string
        datetime:
          type: string
        language:
          type: string
        code:
          type: string
        timeResult:
          type: string
        volumeResult:
          type: string
    
    Post:
      type: object
      properties:
        username:
          type: string
          description: Имя пользователя
        userImage:
          type: string
          description: Ссылка на фото пользователя
        timeOfPost:
          type: string
          description: Время публикации
        commentary:
          type: string
          description: Комментарий пользователя к сообщению
        title:
          type: string
          description: Заголовок задачи
        level:
          type: string
          description: Уровень сложности задачи
        code:
          type: string
        timeResult:
          type: string
        volumeResult:
          type: string
        
          
    FeedPosts:
      type: object
      properties:
        posts:
          type: array
          items:
            type: object
            properties:
              username:
                type: string
                description: Имя пользователя
              userImage:
                type: string
                description: Ссылка на фото пользователя
              timeOfPost:
                type: string
                description: Время публикации
              commentary:
                type: string
                description: Комментарий пользователя к сообщению
              title:
                type: string
                description: Заголовок задачи
              level:
                type: string
                description: Уровень сложности задачи
    NewPost:
      type: object
      properties:
        postID:
          type: integer
          format: int64
          description: ID поста
        userID:
          type: integer
          format: int64
        attemptID:
          type: integer
          format: int64
        timeOfPost:
          type: string
          description: Время публикации
        commentary:
          type: string
          description: Комментарий пользователя к сообщению
       
    
    Chat:
      type: object
      properties:
        roomID:
          type: integer
          format: int64
        users:
          type: array
          items:
            type: string
    ChatCreated:
      type: object
      properties:
        roomID:
          type: integer
          format: int64
    ChatDetails:
      type: object
      properties:
        users:
          type: array
          items:
            type: string
        messages:
          type: array
          items:
            type: object
            properties:
              message_ID:
                type: string
              room_ID:
                type: string
              sender:
                type: string
              userImage:
                type: string
                description: Ссылка на фото пользователя
              content:
                type: array
                items:
                  type: object
                  properties:
                    message:
                      type: string
              dateTime:
                type: string
                format: date-time
              status:
                type: string
    Message:
      type: object
      properties:
        message_ID:
          type: integer
          format: int64
          description: ID сообщения
        room_ID:
          type: string
        sender:
          type: integer
          format: int64
        userImage:
          type: string
          description: Ссылка на фото пользователя
        content:
          type: array
          items:
            type: string
        dateTime:
          type: string
        status:
          type: string
      
    Chats:
      type: object
      properties:
        interlocutor:
          type: string
        lastMessage:
            type: string
        dateTime:
          type: string
        status:
          type: string  
        notifications:
          type: integer
          
          
    SignIn:
      type: object
      properties:
        usernameOrEmail:
          type: string
          description: Имя пользователя или почта
        password:
          type: string
          description: Пароль
      required:
        - usernameOrEmail
        - password
        
    SignUp:
      type: object
      properties:
        username:
          type: string
          description: Имя пользователя
        email:
          type: string
          description: Почта пользователя
        password:
          type: string
          description: Пароль
      required:
        - username
        - email
        - password
        
    InfoGitHub:
      type: object
      properties:
        id:
          type: string
          description: ID в сервисе
        username:
          type: string
          description: Имя пользователя
        email:
          type: string
          description: Почта пользователя
        login:
          type: string
          description: Логин
    
    InfoGoogle:
      type: object
      properties:
        id:
          type: string
          description: ID в сервисе
        username:
          type: string
          description: Имя пользователя
        login:
          type: string
          description: Логин (email)
        
    User:
      type: object
      properties:
        username:
          type: string
        userImage:
          type: string
        dateOfBirth:
          type: string
        posts:
          type: array
          items:
            type: object
            properties:
              timeOfPost:
                type: string
                description: Время публикации
              commentary:
                type: string
                description: Комментарий пользователя к сообщению
              title:
                type: string
                description: Заголовок задачи
              level:
                type: string
                description: Уровень сложности задачи
                
    UserSettings:
      type: object
      properties:
        username:
          type: string
        userImage:
          type: string
        dateOfBirth:
          type: string
        email:
          type: string
        phone:
          type: string
        password:
          type: string
        
    
    Token:
      type: object
      properties:
        acessToken:
          type: string
        refreshToken:
          type: string
        time:
          type: string
```
