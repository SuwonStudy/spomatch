import React, { Component } from 'react';
import './App.css';
import { BrowserRouter } from 'react-router-dom'
import GnbComponent from './gnb/gnb.component';
import Routs from './route/routes';

class App extends Component {
  render() {
    return (
      <BrowserRouter>
        <div className="App">
          <GnbComponent/>
            <Routs />
        </div>
      </BrowserRouter>
    );
  }
}

export default App;
