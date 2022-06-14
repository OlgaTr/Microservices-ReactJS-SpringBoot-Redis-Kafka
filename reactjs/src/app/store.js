import {configureStore} from "@reduxjs/toolkit";
import {persistReducer} from "redux-persist";
import storage from 'redux-persist/lib/storage'
import {rootReducer} from "./reducers";

const persistConfig = {
    key: 'root',
    storage,
}

export default configureStore({
    reducer: {
        justCoffee: persistReducer(persistConfig, rootReducer),
    }
})