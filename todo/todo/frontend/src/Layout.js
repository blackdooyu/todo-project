import { Outlet } from 'react-router-dom';

const Layout = () => {
  return (
    <div>
      <header style={{ background: 'linear-gradient(45deg, Violet, Orange)', padding: 16, fontSize: 32}}>
        Todo List🎈
      </header>
      <main>
        <Outlet />
      </main>
    </div>
  );
};

export default Layout;