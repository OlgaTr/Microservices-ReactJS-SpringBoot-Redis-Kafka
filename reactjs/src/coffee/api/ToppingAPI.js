import axios from 'axios';

function listToppings() {
    return axios.get('http://localhost:8082/toppings')
        .then(response => response.data);
}

export {
    listToppings
}