import './App.css';
import React from 'react';
import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import CoffeeList from "./coffee/components/CoffeeList";
import Toppings from "./coffee/components/Toppings";
import Order from "./orders/components/Order";
import RegistrationForm from "./users/components/RegistrationForm";
import SignInForm from "./users/components/SignInForm";
// import 'bootstrap/dist/css/bootstrap.min.css';
import NavigationBar from './utils/components/NavigationBar'
import Footer from "./utils/components/Footer";
import OrderConfirmation from "./orders/components/OrderConfirmation";
import CoffeeCustomization from "./coffee/components/CoffeeCustomization";
import Error from "./utils/components/Error";
import Payment from "./orders/components/Payment";

function App() {

    return (
    <div>
      <Router>
          <NavigationBar />
          <Routes>
              <Route path='/' element = {<CoffeeList />}/>
              <Route path='/coffeePage' element = {<CoffeeCustomization/>}/>
              <Route path='/order' element = {<Order/>}/>
              <Route path='/payment' element = {<Payment />}/>
              <Route path='/confirmation' element = {<OrderConfirmation />}/>
              <Route path='/register' element = {<RegistrationForm />}/>
              <Route path='/login' element = {<SignInForm />}/>
              <Route path='/error' element = {<Error />}/>
          </Routes>
          <Footer />
      </Router>
    </div>
  );
}

export default App;
