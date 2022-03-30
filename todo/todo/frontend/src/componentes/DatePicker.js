import React, { useState } from 'react';



function DatePicker({name}) {

    const [date,setDate] =useState();

    function changeDate(e){
       
        setDate(e.target.value);
      }

    return (
        <div>
        <h1>{name}님의 {date}</h1>    
        <h1>Todo List</h1>  
        <input type="date" onChange={changeDate} />
        </div>
    );
}

export default DatePicker;