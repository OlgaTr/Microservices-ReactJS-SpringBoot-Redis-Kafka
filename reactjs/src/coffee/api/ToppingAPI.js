import axios from 'axios';

const BASE_URL = 'http://localhost:8080';

function listToppings() {
    return axios.get(`${BASE_URL}/toppings`)
        .then(response => response.data);
}

export {
    listToppings
}