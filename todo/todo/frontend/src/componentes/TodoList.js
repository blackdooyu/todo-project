import React, {useState, useEffect} from 'react';
import styled from 'styled-components';
import TodoItem from './Todoitme';
import axios from 'axios';



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

  const [todos, setTodos] = useState([]);

  const baseUrl = "http://localhost:8080";

  axios.defaults.withCredentials = true;

 async function getTodo(){
      await axios.get(baseUrl+"/todo")
      .then((response) =>{
        setTodos(response.data);
        console.log(response.data)
      })
      .catch((error) => {
        console.error(error);
      })
    
  }

  return <TodoListBlock>
  
      {
        todos 
        ? todos.map((todo) =>{
          return(
            <TodoItem key={todo.id} id={todo.id} text={todo.todoName} done={todo.todoState === 'Y' ? true : false} getTodo={getTodo} />
          )
        }) 
        : null
     }

      
  </TodoListBlock>;
}

export default TodoList;