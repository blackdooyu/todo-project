import { Route, Routes } from 'react-router-dom';
import { createGlobalStyle } from 'styled-components';
import React, {useState, useEffect} from 'react';
import axios from 'axios';
import Layout from './Layout';
import About from './pages/Todo';
import TodoHead from './componentes/TodoHead';
import TodoList from './componentes/TodoList';
import TodoCreate from './componentes/TodoCreate';
import Home from './pages/Home';
import TodoH from './homeCom/TodoH';
import TodoL from './homeCom/TodoL';
import TodoI from './homeCom/TodoI';

const GlobalStyle = createGlobalStyle`
  body {
    background: skyBlue;
  }
`;


function App  ()  {



  return (
    <div>
       <GlobalStyle />
    <Routes>
      <Route element={<Layout />}>
      <Route path="/" element={
      <Home>
       <TodoH/>
       <TodoL/>
      </Home>} />

      <Route path="/todo-list" element={
      <About> 
        <TodoHead/> 
        <TodoList/>
        <TodoCreate/>
      </About>
      } />
      </Route>
    </Routes>
    </div>
  );
};

export default App;
