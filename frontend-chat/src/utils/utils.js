export function getCurrentTime() {
    const now = new Date();
    const hours = String(now.getHours()).padStart(2, '0'); // Добавляем ведущий ноль, если часы меньше 10
    const minutes = String(now.getMinutes()).padStart(2, '0'); // Добавляем ведущий ноль, если минуты меньше 10
    return `${hours}:${minutes}`;
}

export function extractTime(timeString) {
    const dateTime = new Date(timeString);
    const hours = dateTime.getHours().toString().padStart(2, '0');
    const minutes = dateTime.getMinutes().toString().padStart(2, '0');
    return `${hours}:${minutes}`;
  }

export function getRandomIndex() {
    const totalAvatars = 11;
    return Math.floor(Math.random() * totalAvatars) + 1;
}