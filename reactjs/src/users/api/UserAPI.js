import axios from 'axios';

function registerNewUser(user) {
    return axios.post('/users', user);
}

function authenticateUser(user) {
    return axios.post('/users/signIn', user);
}

export {
    registerNewUser, authenticateUser
}