import './App.css';
import React, {useState, useEffect} from 'react';
import axios from 'axios';

function App() {

  const [search,setSearch] = useState("");
  const [input,setInput] = useState("");
  const [users, setUsers] = useState([]);
  const [info,setInfo] = useState([]); 
  const axios = require('axios');
  axios.defaults.withCredentials = true;
  
  const baseUrl = "http://localhost:8080";

  useEffect(() =>{
    getData();
    getInfo();
  }, []);

    function changeText(e){
      e.preventDefault();
      setInput(e.target.value);
    }

  async function getData(){
      await axios.get(baseUrl+"/todo",{
        params: {
          search: search
        }
      })
      .then((response) =>{
        setUsers(response.data);
      })
      .catch((error) => {
        console.error(error);
      })
      setSearch("");
  }

   function getInfo(){
    
    const getInfo = async () => {
      await axios.get(baseUrl+"/user")
      .then((response) => {
        setInfo(response.data);
      })
      .catch((error) =>{
        console.error(error);
      })
    }
    getInfo();
}

  function insertTodo(e){
    e.preventDefault();

    const insertTodo = async () => {
      await axios.post(baseUrl+"/todo",{
        todoName: input
      })
      .then((response) => {
        console.log(response.data)
        setInput("");
        getData();
      })
      .catch((error) =>{
        console.error(error);
      })
    }
    insertTodo();
    console.log("할 일이 추가됐습니다.")
  }

  function deleteTodo(id){
    const deleteTodo = async () => {
      await axios.delete(baseUrl+"/todo",{
        data: {
          id: id
        }
      })
      .then((response) => {
        getData();
      })
      .catch((error) => {
        console.error(error);
      })
    }
    deleteTodo();
  }

  function updateTodo(id){
    const updateTodo = async () => {
      await axios.put(baseUrl+"/todo",{
        id: id
      })
      .then((response) => {
        getData();
      })
      .catch((error) =>{
        console.error(error);
      })
    }
    updateTodo();
    console.log("할 일을 완료했습니다.")
    
  }

  function changeSearch(e){
     e.preventDefault();
     setSearch(e.target.value);
  
  }


  return (
    <div className="App">
      <h1>TODO List</h1>
      <form onSubmit={insertTodo}>
        Todo &nbsp; 
        <input type="text"
        required={true}
        value={input}
        onChange={changeText}
        />
        <input type= "submit"
        value="Create"/>
      </form>


      {
        users 
        ? users.map((user) =>{
          return(
            <div className='todo' key={user.id}>
              <h3>  
                <label
                onClick={() => updateTodo(user.id)}
                className={user.todoState === 'Y' ? "completed" : null }
               >
              {user.todoName} {user.todoState}
               </label>
      
               <label
              onClick={() => deleteTodo(user.id)}
              >
                &nbsp; &nbsp;  ❌
              </label>
              </h3>
                  
              </div>
          )
        }) 
        : null
     }

     <input type='text'
      value={search}
      onChange={changeSearch}
      placeholder="             검색어 입력"
      />
    
    <input type="button"
       onClick={getData}
       value="🔍"/>

         <div>
            <a href= "http://localhost:8080/oauth2/authorization/kakao"
                
               >
              카카오톡 로그인
               </a>

          </div>
          <div>
            <a href= "http://localhost:8080/oauth2/authorization/google"
                
               >
              구글 로그인
               </a>

          </div>
          <div>
            <a href= "http://localhost:8080/logout"
                
               >
              카카오톡 로그아웃
               </a>

          </div>
          
          {
            info ? 
            <div>{info.name} {info.email}</div>
            : <div> 노 </div>
          }

    </div>
  );
}

export default App;