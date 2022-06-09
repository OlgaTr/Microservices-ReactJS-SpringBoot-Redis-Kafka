import './App.css';
import React, {useState} from 'react';
import {
    BrowserRouter as Router,
    Routes,
    Route
} from "react-router-dom";
import CoffeeList from "./coffee/components/CoffeeList";
import Coffee from "./coffee/components/Coffee";
import Order from "./orders/components/Order";
import RegistrationForm from "./users/components/RegistrationForm";
import SignInForm from "./users/components/SignInForm";
// import NavigationBar from "./NavigationBar";
// import 'bootstrap/dist/css/bootstrap.min.css';
import NavigationBar from './utils/components/NavigationBar'
import Footer from "./utils/components/Footer";
import OrderConfirmation from "./orders/components/OrderConfirmation";

const coffeeDrinks =[];

function App() {
    // const [coffeeDrinks, setCoffeeDrinks] = useState([]);

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

export {
    coffeeDrinks
}
export default App;
