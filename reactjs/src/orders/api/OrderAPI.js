import axios from "axios";

function createOrder() {
    return axios.post('/orders');
}

function getOrderById(orderId) {
    return axios.get(`/orders/${orderId}`);
}

function getCoffeeDrinksByOrderId(orderId) {
    return axios.get(`/orders/coffeeDrinks/${orderId}`)
}

function addCoffeeOrderToOrder(orderId, coffeeOrderId) {
    return axios.put(`/orders/${orderId}/${coffeeOrderId}`);
}

export {
    createOrder, getOrderById, addCoffeeOrderToOrder, getCoffeeDrinksByOrderId
}