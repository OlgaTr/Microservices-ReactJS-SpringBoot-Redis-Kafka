import axios from 'axios';

const BASE_URL = 'http://localhost:8080';

function listCoffee() {
    return axios.get(`${BASE_URL}/coffee`);
}

export {
    listCoffee
};