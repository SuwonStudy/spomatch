import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemText from '@material-ui/core/ListItemText';
import ListSubheader from '@material-ui/core/ListSubheader';

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

class BasketJobListComponent extends Component {

    render() {
        const { classes } = this.props;

        return (
            <List className={classes.listBox} subheader={<li />}>
                {[0, 1, 2, 3, 4].map(sectionId => (
                    <li key={`section-${sectionId}`} className={classes.listSection}>
                        <ul className={classes.ul}>
                            {/* list sub name */}
                            <ListSubheader>{`구인 ${sectionId}`}</ListSubheader>

                            {/* list */}
                            {[0, 1, 2].map(item => (
                                <ListItem button key={`item-${sectionId}-${item}`}>
                                    <ListItemText primary={`Item ${item}`} />
                                </ListItem>
                            ))}
                        </ul>
                    </li>
                ))}
            </List>
        );
    }
}

BasketJobListComponent.propTypes = {
    classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(BasketJobListComponent);