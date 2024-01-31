import logo from './logo.svg';
import './App.css';
//import React from 'react';
import Product from './Product';


function App(){
  
// const Header = () => {
  return (
    // <div>
    //   {/* <h1 style={{color: "red"}}>Hello Style!</h1>
    //   <p>Add a little style!</p>
    // </div> */}
    <div className='ProductApp'>
      <Product/>
      <Product/>
      <Product/>
    </div>
  );
//}

}

// const root = ReactDOM.createRoot(document.getElementById('root'));
// root.render(<Header />);

export default App;