import {createSlice} from "@reduxjs/toolkit";

export const coffeeDrinksSlice = createSlice({
    name: 'coffeeDrinks',
    initialState: [],
    reducers: {
        addCoffee(state, action) {
            state.push(action.payload);
        },
        deleteCoffee(state, action) {
            state.splice(action.payload, 1);
        },
        clearAll(state) {
            state.splice(0, state.length);
        }
    }
})

export const {addCoffee, deleteCoffee, clearAll} = coffeeDrinksSlice.actions;
export default coffeeDrinksSlice.reducer;