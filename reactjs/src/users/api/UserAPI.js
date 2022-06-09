import axios from 'axios';

function registerNewUser(user) {
    return axios.post('/users', user);
}

export {
    registerNewUser
}