import axios from "axios";

function createOrder(coffeeDrinksId) {
    return axios.post('/orders', coffeeDrinksId);
}

function getOrderById(orderId) {
    return axios.get(`/orders/${orderId}`);
}

export {
    createOrder, getOrderById
}