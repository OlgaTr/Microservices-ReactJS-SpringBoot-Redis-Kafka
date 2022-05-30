import axios from 'axios';

function listToppings() {
    return axios.get('/toppings')
        .then(response => response.data);
}

export {
    listToppings
}