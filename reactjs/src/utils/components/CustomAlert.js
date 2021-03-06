import {AiOutlineClose} from "react-icons/ai";
import React from "react";

export default function CustomAlert(props) {
    if (props.renderAlert) {
        return (<div className='customAlert'>
                <AiOutlineClose onClick={() => props.closeAlert()}/>
                <p>{props.children}</p>
            </div>
        )
    }
};