import React, { Component } from 'react';
import { Route, Switch } from 'react-router-dom';
import { BasketMatchListComponent } from './basket-match-list.component'
import { BasketJobListComponent } from './basket-job-list.component';

class BasketModule extends Component {
    render() {
        return (
            <Switch>
                <Route path="/match" component={BasketMatchListComponent}/>
                <Route path="/job" component={BasketJobListComponent}/>
            </Switch>
        );
    }
}
export default BasketModule;