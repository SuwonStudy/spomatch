import React, { Component } from 'react';
import ListItem from '@material-ui/core/ListItem';
import ListItemText from '@material-ui/core/ListItemText';

class MatchItemComponent extends Component {

    render() {
        return (
            <ListItem button key={`item-${this.props.name}`}>
                <ListItemText primary={this.props.name} />
            </ListItem>
        );
    }
}
export default MatchItemComponent;