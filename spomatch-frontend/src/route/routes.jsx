import React, { Component } from 'react';
import { Route, Switch } from 'react-router-dom';
import { BasketModule } from '../app/basket/basket-module';

class Routes extends Component {
    render() {
        return (
            <div className="view-routes">
                <Switch>
                    {/* basket */}
                    <Route path="/basket" component={BasketModule}/>
                    {/* baseball */}
                    {/* soccer */}
                </Switch>
            </div>
        );
    }
}
export default Routes;