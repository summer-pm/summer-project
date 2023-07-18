
## API 

## Swagger Hub


[Beercode API](https://app.swaggerhub.com/apis/MARIESDENISOVA_1/BeerCode/v1 "Beercode API") 

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



OpenAPI 


```bash
paths:
  /tasks:
    get:
      summary: Getting the list of tasks
      description: Returns a list of tasks that satisfy the specified parameters.
      parameters:
        - name: level
          in: query
          description: Displaying tasks with a certain level of difficulty
          schema:
            type: string
          example: easy
        - name: status
          in: query
          description: Displaying tasks with a specific status
          schema:
            type: string
          example: решено
        - name: title
          in: query
          description: Displaying a task with a specific name
          schema:
            type: string
          example: taskname
        - name: limit
          in: query
          description: Maximum number of returned items
          schema:
            type: integer
            minimum: 1
            maximum: 100
            default: 20
        - name: sort
          in: query
          description: Sorting order for the tasks (ascending or descending)
          schema:
            type: string
            enum: [asc, desc]
            default: asc
      responses:
        '200':
          description: Successful response
          content:
            application/json:
              schema:
                type: object
                properties:
                  tasks:
                    type: array
                    items:
                      type: object
                      properties:
                        taskID:
                          type: integer
                          format: int64
                          description: The ID of the task
                        title:
                          type: string
                          description: The title of the task
                        level:
                          type: string
                          description: The difficulty level of the task
                        status:
                          type: string
                          description: The status of the task
        '404':
          description: The element does not exist


```













### Просмотр задачи

## `` GET /tasks/{id} ``

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


OpenAPI
```bash
paths:
  /tasks/{id}:
    get:
      summary: Task preview
      description: Returns task information for the specified identifier.
      parameters:
        - name: id
          in: path
          description: Task ID
          required: true
          schema:
            type: integer
            format: int64
      responses:
        '200':
          description: Successful response
          content:
            application/json:
              schema:
                type: object
                properties:
                  taskID:
                    type: integer
                    format: int64
                    description: The ID of the task
                  title:
                    type: string
                    description: The title of the task
                  description:
                    type: string
                    description: The description of the task
                  level:
                    type: string
                    description: The difficulty level of the task
                  examples:
                    type: array
                    items:
                      type: object
                      properties:
                        input:
                          type: string
                          description: The input data of the example
                        output:
                          type: string
                          description: The expected output of the example
                        explanation:
                          type: string
                          description: Explanation of the example
                  status:
                    type: string
                    description: The status of the task
                  languages:
                    type: array
                    items:
                      type: object
                      properties:
                        language:
                          type: string
                          description: The name of the programming language
                        solutionTemplate:
                          type: string
                          description: The solution template for the task
                  timeRestrict:
                    type: string
                    description: The time restriction for the task
                  volumeRestrict:
                    type: string
                    description: The volume restriction for the task
        '403':
          description: Access denied
        '404':
          description: Element not found

```








### Отправка попытки решения

## `` POST /tasks/{id}/attempts ``

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


OpenAPI

```bash
paths:
  /tasks/{id}/attempts:
    post:
      summary: Sending an attempt to solve a task
      description: Allows the user to submit an attempt to solve a problem in a specific programming language
      parameters:
        - name: id
          in: path
          description: Task ID
          required: true
          schema:
            type: integer
            format: int64
      requestBody:
        required: true
        content:
          application/json:
            schema:
              type: object
              properties:
                userID:
                  type: integer
                  format: int64
                  description: User ID
                language:
                  type: string
                  description: Programming language name
                code:
                  type: string
                  description: Solution
      responses:
        '201':
          description: Successfully created
          content:
            application/json:
              schema:
                type: object
                properties:
                  attemptID:
                    type: integer
                    format: int64
                    description: ID of the created attempt
                  userID:
                    type: integer
                    format: int64
                    description: User ID
                  language:
                    type: string
                    description: Programming language name
        '400':
          description: Incorrect request
        '404':
          description: Task not found



```





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



OpenAPI

```bash
paths:
  /tasks/{taskID}/attempts:
    get:
      summary: Viewing a problem solving attempt
      description: Returns task attempts information for the specified task.
      parameters:
        - name: taskID
          in: path
          description: Task ID
          required: true
          schema:
            type: integer
            format: int64
        - name: attemptID
          in: path
          description: Attempt ID
          required: true
          schema:
            type: integer
            format: int64
        - name: limit
          in: query
          description: Maximum number of returned items
          schema:
            type: integer
            minimum: 1
            default: 10
        - name: sort
          in: query
          description: Sorting order for the attempts (ascending or descending)
          schema:
            type: string
            enum: [asc, desc]
            default: asc
      responses:
        '200':
          description: Successful response
          content:
            application/json:
              schema:
                type: object
                properties:
                  taskID:
                    type: integer
                    format: int64
                    description: The ID of the task
                  title:
                    type: string
                    description: The title of the task
                  level:
                    type: string
                    description: The difficulty level of the task
                  attempts:
                    type: array
                    items:
                      type: object
                      properties:
                        status:
                          type: string
                          description: The status of the attempt
                        date:
                          type: string
                          description: The date of the attempt
                        language:
                          type: string
                          description: The programming language used in the attempt
                        code:
                          type: string
                          description: The user's code
                        timeResult:
                          type: string
                          description: Result of a time attempt
                        volumeResult:
                          type: string
                          description: Result of a volume attempt
        '403':
          description: Access denied
        '404':
          description: Element not found



```






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

OpenAPI
```bash
paths:
  /v1/attempts/{id}:
    get:
      summary: View an attempt
      parameters:
        - name: id
          in: path
          description: ID of the attempt
          required: true
          schema:
            type: integer
            format: int64
      responses:
        '200':
          description: OK. The attempt details are successfully retrieved.
          content:
            application/json:
              schema:
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
        '403':
          description: Access denied. The user does not have permission to view the attempt.
        '404':
          description: The specified attempt does not exist.
```





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


OpenAPI

```bash
paths:
  /feed:
    get:
      summary: General feed
      description: Returns a list of posts in the general feed.
      parameters:
        - name: limit
          in: query
          description: Maximum number of returned items. Default is 20.
          schema:
            type: integer
            minimum: 1
        - name: sort
          in: query
          description: Sorting order for the posts (ascending or descending). Default is descending.
          schema:
            type: string
            enum: [asc, desc]
        - name: title
          in: query
          description: Displaying a post with a specific title.
          schema:
            type: string
      responses:
        '200':
          description: Successful response
          content:
            application/json:
              schema:
                type: object
                properties:
                  posts:
                    type: array
                    items:
                      type: object
                      properties:
                        username:
                          type: string
                          description: User's username
                        timeOfPost:
                          type: string
                          description: Time of the post
                        commentary:
                          type: string
                          description: User's commentary on the post
                        title:
                          type: string
                          description: Title of the task
                        level:
                          type: string
                          description: Difficulty level of the task
        '403':
          description: Access denied
        '404':
          description: Element not found
```





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
                "username": "Имя пользователя",
                "lastMessage": "дата и вермя поста",
                "dateTime": "Комментарий пользователя"
               },
               {
                "username": "Имя пользователя",
                "lastMessage": "дата и вермя поста",
                "dateTime": "Комментарий пользователя"
                },
              ...
              ]
}
```


| Параметр | Описание | Тип данных |
| ------------- | ------------- |  ------------- |  
| username | Юзернейм собеседника | string |
| lastMessage | Текст последнего сообщения в диалоге | string |
| dateTime | Дата или время последнего сообщения | string |

Коды ответа

| Код | Описание |
| ------------- | ------------- |
| 200 | ОК |
| 403 | Нет прав доступа |

OpenAPI


```bash
paths:
  /v1/rooms:
    get:
      summary: Get list of rooms
      parameters:
        - name: limit
          in: query
          description: Limit the number of rooms in the response
          schema:
            type: integer
            default: 10
          required: false
        - name: sort
          in: query
          description: Sort results (asc - ascending, desc - descending)
          schema:
            type: string
            enum: [asc, desc]
            default: asc
          required: false
        - name: interlocutor
          in: query
          description: User name of interlocutor
          schema:
            type: string
          required: false
      responses:
        '200':
          description: Successful request. A list of rooms is returned.
          content:
            application/json:
              schema:
                type: object
                properties:
                  rooms:
                    type: array
                    items:
                      type: object
                      properties:
                        username:
                          type: string
                          description: username
                        lastMessage:
                          type: string
                          description: Text of the last message in the dialog
                        dateTime:
                          type: string
                          description: Date or time of the last message
        '403':
         description: Access denied
```








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


OpenAPI

```bash
paths:
  /v1/rooms:
    post:
      summary: Create and store a chat
      requestBody:
        required: true
        content:
          application/json:
            schema:
              type: object
              properties:
                roomID:
                  type: integer
                  format: int64
                users:
                  type: array
                  items:
                    type: string
      responses:
        '201':
          description: Chat created. The chat is successfully created and stored.
          content:
            application/json:
              schema:
                type: object
                properties:
                  roomID:
                    type: integer
                    format: int64
        '400':
          description: Bad Request. The room cannot be created with fewer than 2 users.

```






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
| message | Текст сообщения | string |
| dateTime | Дата и время отправки сообщения | string  |
| status | Статус сообщения: "доставлено/не прочитано/прочитано" |  string |


Коды ответа

| Код | Описание |
| ------------- | ------------- |
| 200 | ОК |
| 404 | Нельзя отобразить чат, которого нет |


OpenAPI

```bash

paths:
  /rooms/{id}:
    get:
      summary: Retrieve a specific chat
      parameters:
        - name: id
          in: path
          description: ID of the chat
          required: true
          schema:
            type: string
      responses:
        '200':
          description: OK. Returns the specified chat.
          content:
            application/json:
              schema:
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
        '404':
          description: Cannot display a chat that does not exist.

```















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

OpenAPI

```bash
paths:
  /rooms/{id}:
    delete:
      summary: Delete chat room
      parameters:
        - name: id
          in: path
          description: room ID
          required: true
          schema:
            type: string
      responses:
        '200':
          description: Successful request. The room was successfully deleted.
          content:
            application/json:
              schema:
                type: object
                properties:
                  isDeleted:
                    type: boolean
                    description: Flag indicating whether the room is deleted.
        '404':
          description: Invalid request. You cannot delete a room that does not exist
```


### Создание сообщения

## `` POST room/{id}/messages ``


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


OpenAPI

```bash
paths:
  /room/{id}/messages:
    post:
      summary: Create a message
      parameters:
        - name: id
          in: path
          description: ID of the room
          required: true
          schema:
            type: string
      requestBody:
        required: true
        content:
          application/json:
           schema:
                type: object
                properties:
                  message_ID:
                    type: string
                  room_ID:
                    type: string
                  sender:
                    type: string
                  content:
                    type: array
                    items:
                      type: string
                  dateTime:
                    type: string
                  status:
                    type: string 
      responses:
        '201':
          description: Message created and saved successfully.
          content:
            application/json:
              schema:
                type: object
                properties:
                  message_ID:
                    type: string
                  room_ID:
                    type: string
                  sender:
                    type: string
                  content:
                    type: array
                    items:
                      type: string
                  dateTime:
                    type: string
                  status:
                    type: string
        '404':
          description: Cannot send a message to a non-existent room or non-existent user.
```

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
| message | Текст сообщения | string |
| dateTime | Дата и время отправки сообщения | string  |
| status | Статус сообщения: "доставлено/не прочитано/прочитано" |  string |

Коды ответа

| Код | Описание |
| ------------- | ------------- |
| 200 | ОК |
| 404 | Нельзя отобразить сообщения, если чат или собеседник не существует |



OpenAPI

```bash
paths:
  /messages:
    get:
      summary: Retrieve messages
      parameters:
        - name: room
          in: query
          description: ID of the chat room
          required: false
          schema:
            type: string
        - name: user
          in: query
          description: ID of the user
          required: false
          schema:
            type: string
      responses:
        '200':
          description: OK. Returns the messages.
          content:
            application/json:
              schema:
                type: object
                properties:
                  messages:
                    type: array
                    items:
                      type: object
                      properties:
                        message_ID:
                          type: integer
                          format: int64
                        room_ID:
                          type: string
                        sender:
                          type: string
                        content:
                          type: array
                          items:
                            type: string
                        dateTime:
                          type: string
                          format: date-time
                        status:
                          type: string
        '404':
          description: Cannot display messages if the chat or user does not exist.

```


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
| message | Текст сообщения | string |
| dateTime | Дата и время отправки сообщения | string  |
| status | Статус сообщения: "доставлено/не прочитано/прочитано" |  string |

Коды ответа

| Код | Описание |
| ------------- | ------------- |
| 200 | ОК |
| 404 | Нельзя отобразить сообщения, если оно не существует |



OpenAPI

```bash
paths:
  /v1/messages/{id}:
    get:
      summary: Retrieve a specific message
      parameters:
        - name: id
          in: path
          description: ID of the message
          required: true
          schema:
            type: integer
            format: int64
      responses:
        '200':
          description: OK. Returns the specified message.
          content:
            application/json:
              schema:
                type: object
                properties:
                  message_ID:
                    type: string
                  room_ID:
                    type: string
                  sender:
                    type: integer
                    format: int64
                  content:
                    type: array
                    items:
                      type: object
                      properties:
                        message:
                          type: string
                  dateTime:
                    type: string
                  status:
                    type: string
        '404':
          description: Cannot display the message if it does not exist.
```







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


OpenAPI

```bash
paths:
  /messages/{id}:
    put:
      summary: Update a message
      parameters:
        - name: id
          in: path
          description: ID of the message
          required: true
          schema:
            type: integer
            format: int64
      requestBody:
        required: true
        content:
          application/json:
            schema:
              type: object
              properties:
                message_ID:
                  type: integer
                  format: int64
                room_ID:
                  type: string
                sender:
                  type: integer
                  format: int64
                content:
                  type: array
                  items:
                    type: string
                dateTime:
                  type: string
                status:
                  type: string
      responses:
        '200':
          description: Message updated successfully.
          content:
            application/json:
              schema:
                type: object
                properties:
                  message_ID:
                    type: integer
                    format: int64
                  room_ID:
                    type: string
                  sender:
                    type: integer
                    format: int64
                  content:
                    type: array
                    items:
                      type: string
                  dateTime:
                    type: string
                  status:
                    type: string
        '404':
          description: Cannot update a non-existent message.

```





















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

OpenAPI

```bash
paths:
  /v1/messages/{id}:
    delete:
      summary: Delete a message
      parameters:
        - name: id
          in: path
          description: ID of the message
          required: true
          schema:
            type: integer
            format: int64
      responses:
        '200':
          description: OK. The message is successfully deleted.
          content:
            application/json:
              schema:
                type: object
                properties:
                  isDeleted:
                    type: boolean
        '404':
          description: The specified message does not exist and cannot be deleted.
```
