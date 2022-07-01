import axios from 'axios';

const BASE_URL = 'http://localhost:8080';

function listCoffee() {
    return axios.get(`${BASE_URL}/coffee`)
        .then(response => response.data);
}

export {
    listCoffee
};