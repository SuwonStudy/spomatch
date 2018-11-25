import React, { Component } from 'react';
import './App.css';
import GnbComponent from './gnb/gnb.component';
import BasketMatchListComponent from './basket/match/basket-match-list.component';

class App extends Component {
  render() {
    return (
      <div className="App">
        <GnbComponent/>

        <BasketMatchListComponent/>
      </div>
    );
  }
}

export default App;
