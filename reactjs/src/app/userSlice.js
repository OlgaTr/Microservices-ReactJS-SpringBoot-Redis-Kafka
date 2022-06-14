import {createSlice} from "@reduxjs/toolkit";

export const userSlice = createSlice({
    name: 'user',
    initialState: {
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
    }
})

export const {signIn, logOut} = userSlice.actions;
export default userSlice.reducer;