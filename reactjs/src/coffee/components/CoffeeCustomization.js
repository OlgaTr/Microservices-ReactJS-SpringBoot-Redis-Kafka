import React, {useEffect, useState} from 'react';
import CoffeeCup from "./CoffeeCup";
import Toppings from "./Toppings";
import {addCoffee} from "../../app/coffeeDrinksSlice";
import {useDispatch} from "react-redux";
import {useLocation, useNavigate} from "react-router-dom";

function CoffeeCustomization() {
    const navigate = useNavigate();
    const location = useLocation();
    const dispatch = useDispatch();
    const coffeeType = location.state.coffee.type;
    const coffeePrice = location.state.coffee.price;
    const [customCoffeePrice, setCustomCoffeePrice] = useState(coffeePrice);
    const[coffeeCup, setCoffeeCup] = useState({size: "", price: 0});
    const[coffeeCupPrice, setCoffeeCupPrice] = useState(0);
    const[toppings, setToppings] = useState([]);
    const[toppingsPrice, setToppingsPrice] = useState(0);
    const [render, performRerender] = useState({});

    useEffect(() => {
        let price = (coffeePrice + coffeeCupPrice +toppingsPrice).toFixed(2);
        setCustomCoffeePrice(price);
    }, [coffeeCupPrice, toppingsPrice]);

    const handleOrder = () => {
        let toppingsType = [];
        for (let topping of toppings) {
            if(topping.isChecked) {
                toppingsType.push(topping.type);
            }
        }
        let description = coffeeType + ' with ' + toppingsType + ', ' + coffeeCup.size;
        let customCoffee = {description: description, price: customCoffeePrice};
        dispatch(addCoffee(customCoffee));
        navigate('/order');
    }
    function sendFromCoffeeCup(cupOption) {
        setCoffeeCup({size: cupOption.size, price: cupOption.price});
        setCoffeeCupPrice(cupOption.price);
    }

    const sendFromToppings = (toppingsData, price) => {
        setToppings(toppingsData);
        setToppingsPrice(price);
    }

    return (
        <>
            <header className='header-customization'>
                <h3>Coffee customization</h3>
            </header>
            <div className='container'>
                <article className='coffee-cups-customization'>
                    <CoffeeCup sendFromCoffeeCup={sendFromCoffeeCup}/>
                </article>
                <article className='toppings-customization'>
                    <Toppings sendFromToppings={sendFromToppings}/>
                </article>
            </div>
            <div className='coffee-customization-footer'>
                <h4> {coffeeType}: {customCoffeePrice} </h4>
                <button onClick={handleOrder} className="custom-button">Order Coffee</button>
            </div>
        </>
    )
}

export default CoffeeCustomization;