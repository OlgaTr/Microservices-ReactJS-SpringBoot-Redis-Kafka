import {createSlice} from "@reduxjs/toolkit";

export const coffeeDrinksSlice = createSlice({
    name: 'coffeeDrinks',
    initialState: {
        coffeeDrinks: []
    },
    reducers: {
        add(state, action) {
            state.coffeeDrinks.push(action.payload);
        },
        deleteCoffee(state, action) {
            let keyToDelete = state.coffeeDrinks.findIndex(id => id === action.payload);
            state.coffeeDrinks.splice(keyToDelete, 1);
        },
        clearAll(state) {
            state.coffeeDrinks.splice(0, state.coffeeDrinks.length);
        }
    }
})

export const {add, deleteCoffee, clearAll} = coffeeDrinksSlice.actions;
export default coffeeDrinksSlice.reducer;