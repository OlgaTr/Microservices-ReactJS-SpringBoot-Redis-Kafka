import React, {useEffect, useState} from "react";
import {useLocation, useNavigate} from "react-router-dom";
import {listToppings} from "../api/ToppingAPI";
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

function Toppings(props) {
    const location = useLocation();
    const navigate = useNavigate();
    const coffeeType = location.state.coffee.type;
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
        let price = 0;
        for (let topping of toppings) {
            if (topping.isChecked) {
                price += topping.price;
            }
        }
        props.sendFromToppings(toppings, price)
        setCoffeeDrinkPrice(() => price.toFixed(2));
        performRerender({...render});
    }

    // function handleClick() {
    //     let toppingsType = [];
    //     for (let topping of toppings) {
    //         if (topping.isChecked) {
    //             toppingsType.push(topping.type);
    //         }
    //     }
    //     let description = coffeeType + ' with ' + toppingsType;
    //     let customCoffee = {description: description, price: coffeeDrinkPrice};
    //     dispatch(addCoffee(customCoffee));
    //     navigate('/order');
    // }

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
            {/*<header>Toppings</header>*/}
            <main>
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
            </main>
            {/*<div className='coffeePage-floor'>*/}
            {/*    <div className='coffee-drink'>*/}
            {/*        <h4>{coffeeType}: {coffeeDrinkPrice}</h4>*/}
            {/*    </div>*/}
            {/*    <button onClick={() => handleClick()} className="custom-button">Order Coffee</button>*/}
            {/*</div>*/}
        </>
    )
}

export default Toppings;