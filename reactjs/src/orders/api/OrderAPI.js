import axios from "axios";

function createOrder(username, password, coffeeDrinksId) {
    return axios.post('/orders', coffeeDrinksId,
        {auth: {
                username: username,
                password: password
            }
        });
}

function getOrderById(orderId) {
    return axios.get(`/orders/${orderId}`);
}

function getOrderByUsername(username, password) {
    return axios.get('/orders/userOrder',
        {auth: {
                username: username,
                password: password
            }});
}

function getCoffeeDrinksByOrderId(username, password, orderId) {
    return axios.get(`/orders/coffeeDrinks/${orderId}`,
        {auth: {
                username: username,
                password: password
            }});
}

export {
    getOrderById, createOrder, getOrderByUsername, getCoffeeDrinksByOrderId
}