import React, { Component } from 'react';
import './App.css';
import GnbComponent from './gnb/gnb.component';
import BasketMatchListComponent from './app/basket/basket-match-list.component';
import BasketTabComponent from './app/basket/basket-tab.component';

class App extends Component {
  render() {
    return (
      <div className="App">
        <GnbComponent/>
        <BasketTabComponent/>
        <BasketMatchListComponent/>
      </div>
    );
  }
}

export default App;
