import {Route} from "react-router-dom";
import {BasketJobListComponent} from "./job/basket-job-list.component";
import {BasketMatchListComponent} from "./match/basket-match-list.component";
import React from "react";

class BasketModule {
    render() {
        return (
            <Route>
                <Route path="/jobs" component={BasketJobListComponent}/>
                <Route path="/match" component={BasketMatchListComponent}/>
            </Route>
        );
    }
}
export default BasketModule;