import {createSlice} from "@reduxjs/toolkit";

export const justCoffeeSlice = createSlice({
    name: 'justCoffee',
    initialState: {
        coffeeDrinks: [],
        username: '',
        password: '',
        isAuthenticated: false
    },
    reducers: {
        signIn(state, action) {
            state.username = action.payload.username;
            state.password = action.payload.password;
            state.isAuthenticated = true;
        },
        logOut(state) {
            state.username = '';
            state.password = '';
            state.isAuthenticated = false;
        },
        addCoffee(state, action) {
            state.coffeeDrinks.push(action.payload);
        },
        deleteCoffee(state, action) {
            let keyToDelete = state.coffeeDrinks.findIndex(id => id === action.payload);
            state.coffeeDrinks.splice(keyToDelete, 1);
        },
        clearAll(state) {
            state.coffeeDrinks.splice(0, state.coffeeDrinks.length);
        },
    }
})

export const {signIn, logOut, addCoffee, deleteCoffee, clearAll} = justCoffeeSlice.actions;
export default justCoffeeSlice.reducer;