import React from 'react';
import styled from 'styled-components';
import TodoItem from './TodoI';

const TodoListBlock = styled.div`
  flex: 1;
  padding: 20px 32px;
  padding-bottom: 48px;
  overflow-y: auto;
`;

function TodoList() {
    return (
      <TodoListBlock>
        <TodoItem />
      </TodoListBlock>
    );
  }

export default TodoList;