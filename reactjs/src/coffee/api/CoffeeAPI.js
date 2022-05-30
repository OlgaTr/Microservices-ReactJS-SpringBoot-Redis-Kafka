import axios from 'axios';

function listCoffee() {
    return axios.get('/coffee')
        .then(response => response.data);
}

export {
    listCoffee
};