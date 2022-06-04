import React, {useEffect, useState} from 'react';
import Button from 'react-bootstrap/Button';
import {useNavigate} from 'react-router-dom';
import {listCoffee} from "../api/CoffeeAPI";
import {listToppings} from "../../toppings/api/ToppingAPI";
import {createCoffeeOrder} from "../../coffeeOrders/api/CoffeeOrderAPI";
import {add} from "../../app/coffeeDrinksSlice";
import {useDispatch} from "react-redux";

let checkedCoffee =[];

function CoffeeList() {
    const [coffeeList, setCoffeeList] = useState([]);
    const [toppings, setToppings] = useState([]);
    const navigate = useNavigate();
    const [Rerender, performReRerender] = useState({});
    const dispatch = useDispatch();

    useEffect(() => {
        listCoffee().then(response => setCoffeeList(response));
        listToppings().then(response => setToppings(response));
    }, [])

    function handleChecked(event, topping) {
        topping.isChecked = event.target.checked;
        performReRerender({...Rerender})
        if (checkedCoffee.length !== 1) {
            clearToppings();
        }
    }

    function handleChooseCoffee(event, coffee) {
        coffee.isChecked = event.target.checked;
        var myToppings = [];
        if (coffee.isChecked) {
            checkedCoffee.push(coffee.id);
            performReRerender({...Rerender})
        } else {
            checkedCoffee.splice(checkedCoffee.indexOf(coffee.id), 1);
            if (checkedCoffee.length === 0) {
                clearToppings();
            }
            performReRerender({...Rerender})
        }
        if (checkedCoffee.length > 1) {
            clearToppings();
            performReRerender({...Rerender})
        }
    }

    function handleOrder() {
        if (checkedCoffee.length === 1) {
            let toppingsId = [];
            for (let topping of toppings) {
                if (topping.isChecked) {
                    toppingsId.push(topping.id);
                }
            }
            createCoffeeOrder(checkedCoffee[0], toppingsId)
                .then(response => {
                    dispatch(add(response.data));
                    navigate('/order');
                })
        } else if (checkedCoffee.length > 1) {
            for (let coffee of checkedCoffee) {
                createCoffeeOrder(coffee, [])
                    .then(response => {
                        dispatch(add(response.data));
                    })
            }
            navigate('/order');
        }

        checkedCoffee.splice(0, checkedCoffee.length);
    }

    function clearToppings() {
        var toppingsInput = document.getElementsByName('toppings');
        for (let topping of toppingsInput) {
            topping.checked = false;
        }
        for (let topping of toppings) {
            topping.isChecked = false;
        }
    }

    function calculatePrice() {
        let price = 0;
        for (let topping of toppings) {
            if (topping.isChecked) price += topping.price;
        }
        for (let coffee of coffeeList) {
            if (coffee.isChecked) price += coffee.price;
        }
        return Math.round(price * 100)/100;
    }

    const tableRows = coffeeList.map(coffee =>
        <tr key={coffee.id}>
            <td>{coffee.type}</td>
            <td>{coffee.price}</td>
            <td>
                <input
                    id = {coffee.id}
                    name="input"
                    type="checkbox" className="input"
                    value={coffee.id}
                    onChange={event => handleChooseCoffee(event, coffee)}
                    // checked={checked}
                />
            </td>
        </tr>
    );

    const toppingsRows = toppings.map(topping =>
        <tr key={topping.id}>
            <td>{topping.type}</td>
            <td>{topping.price}</td>
            <td>
                <input
                    // id={}
                    name='toppings'
                    type='checkbox'
                    onChange={event => handleChecked(event, topping)}
                    className="input"/>
            </td>
        </tr> );

    return (
        <div className="general">
            <div className="header-container">
                <h3 className="header"> Coffee Menu </h3>
            </div>
            <div className="menu-container">
                <div className="menu">
                    <table>
                        <thead>
                        <tr>
                            <th>Coffee drink</th>
                            <th>Price</th>
                        </tr>
                        </thead>
                        <tbody>
                        {
                            tableRows
                        }
                        </tbody>
                    </table>
                </div>
                <div className="menu">
                    <table>
                        <thead>
                        <tr>
                            <th>Toppings</th>
                            <th>Price</th>
                        </tr>
                        </thead>
                        <tbody>{toppingsRows}</tbody>
                    </table>
                </div>
            </div>
            <div className="footer-container">
                <p className="order"> Price: {calculatePrice()} </p>
                <Button onClick={handleOrder} className="button">Order Coffee</Button>
                <p className="footer"> A bad day with coffee is better than a good day without it. </p>
            </div>
        </div>
    )
}

export default CoffeeList;