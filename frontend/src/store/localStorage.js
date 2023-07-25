export const getItem = (key) => {
    try {
        return JSON.parse(localStorage.getItem(key))
    } catch (e) {
        console.log('error getting from localStorage ' + e)
        return null
    }
}
export const setItem = (key, value) => {
    try {
        console.log('key: ' + key + ' value: ' + JSON.stringify(value))
        localStorage.setItem(key, JSON.stringify(value))
    } catch (e) {
        console.log(e)
    }
}
