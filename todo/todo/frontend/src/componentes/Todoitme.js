import React from 'react';
import styled, { css } from 'styled-components';
import { MdDone, MdDelete } from 'react-icons/md';
import axios from 'axios';



const Remove = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  color: #dee2e6;
  font-size: 24px;
  cursor: pointer;
  &:hover {
    color: #ff6b6b;
  }
  display: none;
`;

const TodoItemBlock = styled.div`
  display: flex;
  align-items: center;
  padding-top: 12px;
  padding-bottom: 12px;
  &:hover {
    ${Remove} {
      display: initial;
    }
  }
`;

const CheckCircle = styled.div`
  width: 32px;
  height: 32px;
  border-radius: 16px;
  border: 1px solid #ced4da;
  font-size: 24px;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-right: 20px;
  cursor: pointer;
  ${props =>
    props.done &&
    css`
      border: 1px solid #38d9a9;
      color: #38d9a9;
    `}
`;

const Text = styled.div`
  flex: 1;
  font-size: 21px;
  color: #495057;
  ${props =>
    props.done &&
    css`
      color: #ced4da;
    `}
`;


function TodoItem({ id, done, text ,getTodo,date}) {

  const baseUrl = "https://todo-list-project.xyz";

  axios.defaults.withCredentials = true;

  function deleteTodo(id){
    const deleteTodo = async () => {
      await axios.delete(baseUrl+"/todo",{
        data: {
          id: id
        }
      })
      .then((response) => {
        getTodo(date);
      })
      .catch((error) => {
        console.error(error);
      })
    }
    deleteTodo();
  }

  function updateTodo(id){
    const deleteTodo = async () => {
      await axios.put(baseUrl+"/todo",{
       
          id: id
       
      })
      .then((response) => {
        getTodo(date);
      })
      .catch((error) => {
        console.error(error);
      })
    }
    deleteTodo();
  }

  return (
    <TodoItemBlock>
      <CheckCircle done={done} onClick={() => updateTodo(id)}   >{done && <MdDone />}</CheckCircle>
      <Text done={done}>{text}</Text>
      <label onClick={() => deleteTodo(id)}>
      <Remove>
        <MdDelete />
      </Remove>
      </label>
    </TodoItemBlock>
  );
}

export default TodoItem;