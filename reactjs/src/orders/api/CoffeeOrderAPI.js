import {BASE_URL} from "../../coffee/api/CoffeeAPI";
import axios from "axios";

function createCoffeeOrder() {
    return axios.post('http://localhost:8081/orders');
}

function addCoffeeToCoffeeOrder(coffeeOrderId, coffeeId) {
    return axios.put(`${BASE_URL}/orders/${coffeeOrderId}/${coffeeId}`)
}

function addToppingToCoffee(coffeeOrderId, toppingId) {
    return axios.put(`${BASE_URL}/coffeeOrders/${coffeeOrderId}/${toppingId}`)
}

function listOrders() {
    return axios.get(`${BASE_URL}/orders`)
        .then(response => response.data);
}

export {
    createCoffeeOrder, addCoffeeToCoffeeOrder, addToppingToCoffee
}