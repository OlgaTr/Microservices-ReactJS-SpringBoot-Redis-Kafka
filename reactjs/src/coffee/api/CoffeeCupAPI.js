import axios from "axios";

const BASE_URL = 'http://localhost:8080';

function listCoffeeCups() {
    return axios.get(`${BASE_URL}/coffeeCups`);
}

export {
    listCoffeeCups
}