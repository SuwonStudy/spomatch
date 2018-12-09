import React, { Component } from 'react';
import { Route, Switch } from 'react-router-dom';
import Main from '../main';
import BasketMatchListComponent from "../basket/match/basket-match-list.component";
import {BasketJobListComponent} from "../basket/job/basket-job-list.component";

/**
 * name: Main Routes
 *
 * description: 모든 화면 모듈의 routes를 포함하고 있다
 *
 */
class Routes extends Component {

    /**
     * Render
     * @returns {XML}
     */
    render() {
        return (
            <div className="view-routes">
                <Switch>
                    {/* basket */}
                    <Route exact path="/" component={Main} />
                    {/*<Route path="/basket/jobs" component={BasketJobListComponent}/>*/}
                    {/*<Route path="/basket/match" component={BasketMatchListComponent}/>*/}
                    {/*<Route path="/basket" component={BasketMatchListComponent}/>*/}
                    <Route path="/basket" component={BasketMatchListComponent}/>
                    {/* baseball */}
                    {/* soccer */}


                    {/* <Route path="/" component={BasketMatchListComponent}/> */}
                </Switch>
            </div>
        );
    }
}
export default Routes;