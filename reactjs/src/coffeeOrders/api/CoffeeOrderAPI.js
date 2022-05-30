import axios from "axios";

function createCoffeeOrder() {
    return axios.post('/coffeeOrders');
}

function getCoffeeOrderById(coffeeOrderId) {
    return axios.get(`/coffeeOrders/${coffeeOrderId}`);
}

function addCoffeeToCoffeeOrder(coffeeOrderId, coffeeId) {
    return axios.put(`/coffeeOrders/${coffeeOrderId}/${coffeeId}`);
}

function addToppingsToCoffee(coffeeOrderId, toppings) {
    return axios.put(`/coffeeOrders/${coffeeOrderId}`, toppings)
}

function listOrders() {
    return axios.get('/coffeeOrders')
        .then(response => response.data);
}

export {
    createCoffeeOrder, getCoffeeOrderById, addCoffeeToCoffeeOrder, addToppingsToCoffee
}