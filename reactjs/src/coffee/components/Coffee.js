import React, {useEffect, useState} from "react";
import {useLocation, useNavigate} from "react-router-dom";
import {listToppings} from "../../toppings/api/ToppingAPI";
import {createCoffeeOrder} from "../../coffeeOrders/api/CoffeeOrderAPI";
import {useDispatch} from "react-redux";
import {addCoffee} from "../../app/coffeeDrinksSlice";
import {BsSquare} from 'react-icons/bs';
import {BsPatchCheck} from 'react-icons/bs';

function CustomCheckbox(props) {
    if (!props.topping.isChecked) {
        return (
            <BsSquare onClick={() => props.handleClicked()} className='icon'/>
        );
    } else {
        return (
            <BsPatchCheck onClick={() => props.handleClicked()} className='icon'/>
        );
    }
}

function Coffee() {
    const location = useLocation();
    const navigate = useNavigate();
    const coffeeType = location.state.coffee.type;
    const coffeeId = location.state.id;
    const [toppings, setToppings] = useState([]);
    const coffeePrice = location.state.coffee.price;
    const [coffeeDrinkPrice, setCoffeeDrinkPrice] = useState(coffeePrice);
    const dispatch = useDispatch();
    const [render, performRerender] = useState({});

    useEffect(() => {
        listToppings().then(response => setToppings(response));
    }, [])

    function handleClicked(topping) {
        topping.isChecked ? topping.isChecked = false : topping.isChecked = true;
        let price = coffeePrice;
        for (let topping of toppings) {
            if (topping.isChecked) {
                price += topping.price;
            }
        }
        setCoffeeDrinkPrice(() => price.toFixed(2));
        performRerender({...render});
    }

    function handleClick() {
        let toppingsId = [];
        for (let topping of toppings) {
            if (topping.isChecked) {
                toppingsId.push(topping.id);
            }
        }
        let coffeeDrinkId;
        createCoffeeOrder(coffeeId, toppingsId)
            .then(response => {
                coffeeDrinkId = response.data;
                dispatch(addCoffee(response.data));
                navigate('/order');
            })
    }

    const toppingsRows = toppings.map(topping =>
        <tr key={topping.id}>
            <td>{topping.type}</td>
            <td>{topping.price}</td>
            <td>
                <CustomCheckbox topping={topping} handleClicked={() => handleClicked(topping)}/>
            </td>
        </tr>);

    return (
        <>
            <div className='header-container'>
                <p>Toppings</p>
            </div>
            <div className="content-container">
                <table>
                    <thead>
                    <tr>
                        <th>Toppings</th>
                        <th>Price</th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        toppingsRows
                    }
                    </tbody>
                </table>
            </div>
            <div className='coffeePage-floor'>
                <div className='coffee-drink'>
                    <h4>{coffeeType} {coffeeDrinkPrice}</h4>
                </div>
                <button onClick={() => handleClick()} className="footer">Order Coffee</button>
            </div>
        </>
    )
}

export default Coffee;