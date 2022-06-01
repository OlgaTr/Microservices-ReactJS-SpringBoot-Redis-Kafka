import {createSlice} from "@reduxjs/toolkit";

export const coffeeDrinksSlice = createSlice({
    name: 'coffeeDrinks',
    initialState: [],
    reducers: {
        add: (state, action) => {
            state.push(action.payload);
        },
        deleteCoffee: (state, action) => {
            let keyToDelete = state.findIndex(id => id === action.payload);
            state.splice(keyToDelete, 1);
        },
        clearAll: state => {
            state.splice(0, state.length);
        }
    }
})

export const {add, deleteCoffee, clearAll} = coffeeDrinksSlice.actions
export default coffeeDrinksSlice.reducer
export const {selectCoffeeDrinks} = state => state.coffeeDrinks