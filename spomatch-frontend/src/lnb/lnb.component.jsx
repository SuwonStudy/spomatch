import React, {Component} from "react";
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import Drawer from '@material-ui/core/Drawer';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemText from '@material-ui/core/ListItemText';

const styles = {
    list: {
        width: 250,
    },
    fullList: {
        width: 'auto',
    },
};

class LnbComponent extends Component {

    lnbMenuList = [
        {label: '농구', value: 'basket'},
        {label: '야구', value: 'base'},
        {label: '축구', value: 'soccer'},
        {label: '풋살', value: 'futsal'},
    ];

    constructor (props){
        super(props);
        // bind
        this.handleClose = this.handleClose.bind(this);
    }

    handleClose = () => {
        this.props.onClosed();
    };

    render() {
        const { classes } = this.props;

        const sideList = (
            <div className={classes.list}>
                <List>
                    {this.lnbMenuList.map((item, index) => (
                        <ListItem button key={item.value}>
                            <ListItemText primary={item.label} />
                        </ListItem>
                    ))}
                </List>
            </div>
        );

        return (
            <Drawer open={this.props.isShowLnb} onClose={this.handleClose}>
                <div
                    tabIndex={0}
                    role="button"
                    onClick={this.handleClose}>
                    {sideList}
                </div>
            </Drawer>
        );
    }
}

LnbComponent.propTypes = {
    classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(LnbComponent);