// import './App.css';
import React from 'react';
import {
    BrowserRouter as Router,
    Routes,
    Route
} from "react-router-dom";
import CoffeeList from "./coffee/components/CoffeeList";
import Coffee from "./coffee/components/Coffee";
import CoffeeOrder from "./orders/components/CoffeeOrder";

function App() {
  return (
    <div>
      <Router>
          <Routes>
              <Route path='/' element = {<CoffeeList />}/>
              <Route path='/coffeePage' element = {<Coffee />}/>
              <Route path='/coffeeOrder' element = {<CoffeeOrder />}/>
          </Routes>
      </Router>
    </div>
  );
}

export default App;
