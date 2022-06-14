import {combineReducers} from "redux";

import coffeeDrinksReducer from "./coffeeDrinksSlice";
import userReducer from './userSlice';

const rootReducer = combineReducers({
    coffeeDrinks: coffeeDrinksReducer,
    user: userReducer,
})

export {
    rootReducer
}