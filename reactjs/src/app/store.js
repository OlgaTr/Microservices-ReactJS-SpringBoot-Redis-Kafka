import {configureStore} from "@reduxjs/toolkit";
import coffeeDrinksReducer from "./coffeeDrinksSlice";
import userReducer from './userSlice';
import {persistReducer} from "redux-persist";
import storage from 'redux-persist/lib/storage'

const persistConfig = {
    key: 'root',
    storage,
}

const coffeePersistedReducer = persistReducer(persistConfig, coffeeDrinksReducer);
const userPersistedReducer = persistReducer(persistConfig, userReducer);

export default configureStore({
    reducer: {
        coffeeDrinks: coffeePersistedReducer,
        user: userPersistedReducer
    }
})