import axios from 'axios';

const BASE_URL = 'http://localhost:9090';

function listCoffee() {
    return axios.get(`${BASE_URL}/coffee`)
        .then(response => response.data);
}

function createCustomCoffee(coffeeId, toppingsId) {
    return axios.post(`${BASE_URL}/coffee/customCoffee/${coffeeId}`, toppingsId);
}

export {
    listCoffee, createCustomCoffee
};