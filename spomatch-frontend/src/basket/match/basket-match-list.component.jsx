import React, { Component, Fragment } from 'react';
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import List from '@material-ui/core/List';
import MatchItemComponent from '../../common/match-item.component';
import BasketTabComponent from '../basket-tab.component';
import CreateMatchComponent from '../../common/create-match.component';
import IconButton from "@material-ui/core/IconButton/IconButton";

const styles = theme => ({
    listBox: {
        width: '100%',
        maxWidth: '100%',
        backgroundColor: theme.palette.background.paper,
        position: 'relative',
        overflow: 'auto',
        maxHeight: '100%',
    },
    listSection: {
        backgroundColor: 'inherit',
    },
    ul: {
        backgroundColor: 'inherit',
        padding: 0,
    },
});

class BasketMatchListComponent extends Component {

    /**
     * Render
     * @returns {XML}
     */
    render() {
        const { classes } = this.props;
        // match list
        const matchList = [
            {name: '매치1', value: 'enable'},
            {name: '매치2', value: 'enable'},
            {name: '매치3', value: 'enable'},
            {name: '매치4', value: 'enable'},
            {name: '매치5', value: 'enable'},
            {name: '매치6', value: 'enable'},
            {name: '매치7', value: 'enable'},
            {name: '매치8', value: 'enable'},
            {name: '매치2', value: 'enable'},
            {name: '매치3', value: 'enable'},
            {name: '매치4', value: 'enable'},
            {name: '매치5', value: 'enable'},
            {name: '매치6', value: 'enable'},
            {name: '매치7', value: 'enable'},
            {name: '매치8', value: 'enable'},
            {name: '매치2', value: 'enable'},
            {name: '매치3', value: 'enable'},
            {name: '매치4', value: 'enable'},
            {name: '매치5', value: 'enable'},
            {name: '매치6', value: 'enable'},
            {name: '매치7', value: 'enable'},
            {name: '매치8', value: 'enable'},
        ];

        return (
            <Fragment>
                {/* TAB */}
                <BasketTabComponent/>
                {/* create match */}
                <CreateMatchComponent variant="fab" mode="BASKET"/>
                {/* LIST */}
                <List className={classes.listBox} subheader={<li />}>
                    {matchList.map((item, index) => (
                        <li className={classes.listSection} key={index}>
                            <MatchItemComponent name={item.name}/>
                        </li>
                    ))}
                </List>
            </Fragment>

        );
    }
}

BasketMatchListComponent.propTypes = {
    classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(BasketMatchListComponent);