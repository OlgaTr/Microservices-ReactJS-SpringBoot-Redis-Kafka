import axios from "axios";

function createCoffeeOrder(coffeeId, toppingsId) {
    return axios.post(`/coffeeOrders/${coffeeId}`, toppingsId);
}

function getCoffeeDrinksById(coffeeDrinksId) {
    return axios.post('/coffeeDrinks', coffeeDrinksId);
}

export {
    createCoffeeOrder, getCoffeeDrinksById
}