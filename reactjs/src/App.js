import './App.css';
import React from 'react';
import {
    BrowserRouter as Router,
    Routes,
    Route
} from "react-router-dom";
import CoffeeList from "./coffee/components/CoffeeList";
import Coffee from "./coffee/components/Coffee";
import Order from "./orders/components/Order";
import CoffeeMenu from "./coffee/components/CoffeeMenu";

function App() {
  return (
    <div>
      <Router>
          <Routes>
              <Route path='/' element = {<CoffeeList />}/>
              <Route path='/coffeePage' element = {<Coffee />}/>
              <Route path='/order' element = {<Order />}/>
              <Route path='/menu' element = {<CoffeeMenu />}/>
          </Routes>
      </Router>
    </div>
  );
}

export default App;
