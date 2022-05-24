import axios from 'axios';
import React from 'react';

const BASE_URL = 'http://localhost:8081';

function listCoffee() {
    return axios.get(`${BASE_URL}/coffee`)
        .then(response => response.data);
}

export {
    BASE_URL, listCoffee
};