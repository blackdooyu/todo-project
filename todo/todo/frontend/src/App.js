import { Route, Routes } from 'react-router-dom';
import { createGlobalStyle } from 'styled-components';
import React, {useState, useEffect} from 'react';
import axios from 'axios';
import Layout from './Layout';
import About from './pages/Todo';
import Home from './pages/Home';
import TodoHead from './componentes/TodoHead';
import TodoList from './componentes/TodoList';
import TodoCreate from './componentes/TodoCreate';

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
      <Route path="/" element={<Home />} />
      <Route path="/about" element={
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
