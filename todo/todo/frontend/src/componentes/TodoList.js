import React, {useState, useEffect} from 'react';
import styled from 'styled-components';
import TodoItem from './Todoitme';
import axios from 'axios';
import { Link } from "react-router-dom";



const TodoListBlock = styled.div`
  flex: 1;
  padding: 20px 32px;
  padding-bottom: 48px;
  overflow-y: auto;
`;

function TodoList() {

  useEffect(() =>{
    getTodo();
  }, []);

  const [date,setDate] =useState("");

  function changeDate(e){
    e.preventDefault();
    setDate(e.target.value);
    getTodo(e.target.value);
  }

  const [todos, setTodos] = useState([]);

  const baseUrl = "https://todo-list-project.xyz";

  axios.defaults.withCredentials = true;

 async function getTodo(e){
 
      await axios.get(baseUrl+"/todo",{
        params:{
          localDate:e
        }
      })
      .then((response) =>{
        setTodos(response.data);
        setDate(e.target.value);
      })
      .catch((error) => {
        console.log(error);
      })
    
  }

  return <TodoListBlock>
    <input type="date" onChange={changeDate} />
  
      {
        todos 
        ? todos.map((todo) =>{
          return(
            <TodoItem key={todo.id} id={todo.id} text={todo.todoName} done={todo.todoState === 'Y' ? true : false} getTodo={getTodo} date={date} />
          )
        }) 
        : null
     }

     

      
  </TodoListBlock>;
}

export default TodoList;