import axios from "axios";

function createOrder(username, password, coffeeDrinksId) {
    return axios.post('/orders', coffeeDrinksId,
        {auth: {
                username: username,
                password: password
            }
        });
}

function getOrderById(orderId) {
    return axios.get(`/orders/${orderId}`);
}

export {
    getOrderById, createOrder
}