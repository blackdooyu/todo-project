import React, {useState, useEffect} from 'react';
import styled from 'styled-components';
import DatePicker from './DatePicker';
import axios from 'axios';


const TodoHeadBlock = styled.div`
  padding-top: 48px;
  padding-left: 32px;
  padding-right: 32px;
  padding-bottom: 24px;
  border-bottom: 1px solid #e9ecef;
  h1 {
    margin: 0;
    font-size: 36px;
    color: #343a40;
  }
  .day {
    margin-top: 4px;
    color: #868e96;
    font-size: 21px;
  }
  .tasks-left {
    color: #20c997;
    font-size: 18px;
    margin-top: 40px;
    font-weight: bold;
  }
`;

const TodoListBlock = styled.div`
  flex: 1;
  padding: 20px 32px;
  padding-bottom: 48px;
  overflow-y: auto;
`;




function TodoHead() {

  useEffect(() =>{
    getUser();
  }, []);

  const [user, setUsers] = useState([]);

  const baseUrl = "http://localhost:8080";

  axios.defaults.withCredentials = true;

 async function getUser(){
      await axios.get(baseUrl+"/user")
      .then((response) =>{
        setUsers(response.data);
        console.log(response.data)
      })
      .catch((error) => {
        console.error(error);
      })
    
  }

  return (
   
    <TodoHeadBlock>
       <DatePicker name={user.name}/>
      <div className="tasks-left"> 개 완료</div>
    </TodoHeadBlock>
    
  );
}

export default TodoHead;