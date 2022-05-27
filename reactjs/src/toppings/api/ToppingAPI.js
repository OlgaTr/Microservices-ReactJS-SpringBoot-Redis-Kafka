import axios from 'axios';
import {BASE_URL} from "../../coffee/api/CoffeeAPI";

function listToppings() {
    return axios.get(`${BASE_URL}/toppings`)
        .then(response => response.data);
}

export {
    listToppings
}