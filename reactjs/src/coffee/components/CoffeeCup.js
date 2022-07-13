import React, {useEffect, useState} from 'react';
import {listCoffeeCups} from "../api/CoffeeCupAPI";
import {SiCoffeescript} from "react-icons/si";
import {BsCircle} from "react-icons/bs";

function CustomRadioButton(props) {
    if (props.coffeeCup.checked) {
        return (
            <SiCoffeescript/>
        );
    } else {
        return (
            <BsCircle onClick={() => props.handleClick()}/>
        )
    }
}

export default function CoffeeCup(props) {
    const [coffeeCups, setCoffeeCups] = useState([]);
    const [render, performRerender] = useState({});

    useEffect(() => {
        listCoffeeCups()
            .then(response => {
                setCoffeeCups(response.data);
            })
            .catch(error => alert(error));
    }, []);

    function handleClick(coffeeCup) {
        for (let cup of coffeeCups) {
            cup.checked = false;
        }
        coffeeCup.checked = true;
        performRerender({...render})
        props.sendFromCoffeeCup(coffeeCup);
    }

    const listItems = coffeeCups.map((coffeeCup, index) =>
        <li key={index}>
            <CustomRadioButton coffeeCup={coffeeCup} handleClick={() => handleClick(coffeeCup)}/>
            <p>{coffeeCup.size}<br/> +{coffeeCup.price}</p>
        </li>);

    return (
        <>
            <main className='coffee-cup-container'>
                <h3>Size options</h3>
                <ul>{listItems}</ul>
            </main>
        </>);
}