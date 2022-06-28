import axios from "axios";

const BASE_URL = 'http://localhost:8081';

function getCoffeeDrinksById(coffeeDrinksId) {
    return axios.post(`${BASE_URL}/customCoffees`, coffeeDrinksId);
}

export {
    getCoffeeDrinksById
}