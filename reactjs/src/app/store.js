import {configureStore} from "@reduxjs/toolkit";
import coffeeDrinksReducer from "./coffeeDrinksSlice";
// import {persistReducer} from "redux-persist";
// import storage from 'redux-persist/lib/storage'

// const persistConfig = {
//     key: 'root',
//     storage
// }

// const persistedReducer = persistReducer(persistConfig, coffeeDrinksReducer);

export default configureStore({
    reducer: {
        coffeeDrinks: coffeeDrinksReducer
    }
})