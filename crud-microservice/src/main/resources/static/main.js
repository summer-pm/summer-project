let stompClient = null;
let roomId = null;
const url = 'http://localhost:8889'

function joinRoom() {
    // Получаем ID комнаты из поля ввода
    roomId = document.getElementById('roomIdInput').value;
    connectWebSocket();
}

function connectWebSocket() {
    // Подключение к WebSocket-серверу с использованием SockJS и Stomp.js
    let socket = new SockJS(url + '/websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        // Передаем ID комнаты в заголовке запроса подключения
        let headers = {
            roomId: roomId
        };

        // Подписываемся на топик комнаты для получения входящих сообщений
        stompClient.subscribe('/topic/' + roomId, function(message) {
            // Обработка входящего сообщения
            let messageContent = JSON.parse(message.body).content;
            console.log('Received message: ' + messageContent);
        });

        console.log('Connected to WebSocket');
    });
}

function sendMessage() {
    // Отправка сообщения на сервер через WebSocket
    let message = document.getElementById('messageInput').value;

    if (stompClient && roomId && message) {
        let messageData = {
            content: message
        };

        // Отправляем сообщение на сервер с указанием ID комнаты в URL
        stompClient.send('/app/sendMessage/' + roomId, {}, JSON.stringify(messageData));
    }
}
