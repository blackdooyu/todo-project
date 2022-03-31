import React, { useState } from 'react';
import axios from 'axios';


function DatePicker({name}) {
    
    return (
        <div>
        <h1>{name}님의 Todo List</h1>    
        <h3>기본 값: 오늘</h3>
        <a href= "http://localhost:8080/logout"
                
               >
              로그아웃
               </a>
        </div>
    );
}

export default DatePicker;