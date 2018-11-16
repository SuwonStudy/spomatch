import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import Paper from '@material-ui/core/Paper';
import Tabs from '@material-ui/core/Tabs';
import Tab from '@material-ui/core/Tab';

const styles = theme => ({
    tabBox: {
        width: '100%',
        maxWidth: '100%',
    },
    tabItem: {
        width: '50%',
        maxWidth: '50%'
    }
});

class BasketTabComponent extends Component {

    state = {
        value: 2,
    };

    /**
     * Tab changed event
     * @param event
     * @param value
     */
    onChangedTab = (event, value) => {
        this.setState({ value });
    };

    /**
     * Render
     * @returns {XML}
     */
    render() {
        const { classes } = this.props;
        return (
            <Paper square className={classes.tabBox}>
                <Tabs
                    value={this.state.value}
                    indicatorColor="primary"
                    textColor="primary"
                    onChange={this.onChangedTab}>
                    <Tab className={classes.tabItem} label="매칭" />
                    <Tab className={classes.tabItem} label="구인/구직" />
                </Tabs>
            </Paper>
        );
    }
}

BasketTabComponent.propTypes = {
    classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(BasketTabComponent);
