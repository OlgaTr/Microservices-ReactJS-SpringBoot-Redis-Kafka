import axios from 'axios';

const BASE_URL = 'http://localhost:8082';

function registerNewUser(user) {
    return axios.post(`${BASE_URL}/users`, user);
}

function authenticateUser(user) {
    return axios.post(`${BASE_URL}/users/signIn`, user);
}

export {
    registerNewUser, authenticateUser
}