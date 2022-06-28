import axios from "axios";

const BASE_URL = 'http://localhost:8081';

function createOrder(username, password, customCoffeesId) {

    return axios.post(`${BASE_URL}/orders`, customCoffeesId,
        {headers: {
                'username': username,
                'password': password
            }
    })
}

function getOrderById(username, password, orderId) {
    return axios.get(`${BASE_URL}/orders/${orderId}`,
        {headers: {
                'username': username,
                'password': password
            }});
}

// function getOrderByUsername(username, password) {
//     return axios({
//         method: 'get',
//         url: `${BASE_URL}/orders/userOrder`,
//         headers: {
//             'username': username,
//             'password': password
//         }
//     })
// }

function getCoffeeDrinksByOrderId(username, password, orderId) {
    return axios.get(`${BASE_URL}/orders/coffeeDrinks/${orderId}`,
        {auth: {
                username: username,
                password: password
            }});
}

export {
    getOrderById, createOrder, getCoffeeDrinksByOrderId
}