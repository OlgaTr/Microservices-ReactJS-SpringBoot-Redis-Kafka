import './App.css';
import React from 'react';
import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import CoffeeList from "./coffee/components/CoffeeList";
import Coffee from "./coffee/components/Coffee";
import Order from "./orders/components/Order";
import RegistrationForm from "./users/components/RegistrationForm";
import SignInForm from "./users/components/SignInForm";
// import 'bootstrap/dist/css/bootstrap.min.css';
import NavigationBar from './utils/components/NavigationBar'
import Footer from "./utils/components/Footer";
import OrderConfirmation from "./orders/components/OrderConfirmation";

function App() {

    return (
    <div>
      <Router>
          <NavigationBar />
          <Routes>
              <Route path='/' element = {<CoffeeList />}/>
              <Route path='/coffeePage' element = {<Coffee/>}/>
              <Route path='/order' element = {<Order/>}/>
              <Route path='/confirmation' element = {<OrderConfirmation />}/>
              <Route path='/register' element = {<RegistrationForm />}/>
              <Route path='/login' element = {<SignInForm />}/>
          </Routes>
          <Footer />
      </Router>
    </div>
  );
}

export default App;
