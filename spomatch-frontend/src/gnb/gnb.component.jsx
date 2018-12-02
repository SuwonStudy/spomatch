import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import IconButton from '@material-ui/core/IconButton';
import MenuIcon from '@material-ui/icons/Menu';
import AccountCircle from '@material-ui/icons/AccountCircle';
import MenuItem from '@material-ui/core/MenuItem';
import Menu from '@material-ui/core/Menu';
import LnbComponent from "../lnb/lnb.component";

// GNB에서 사용하는 style
const styles = {
    root: {
        flexGrow: 1,
    },
    grow: {
        flexGrow: 1,
    },
    menuButton: {
        marginLeft: -12,
        marginRight: 20,
    },
};

/**
 * name: Global navigation bar component
 *
 * description: 모든 페이지에서 최상단에 있는 네비게이션 바
 *              종목을 선택할 수 있는 menu와 사용자 정보를 가지고있는 profile을 가지고 있음
 */
class GnbComponent extends Component {
    state = {
        auth: true,
        anchorEl: null,
        isShowLnb: false
    };

    handleChange = event => {
        this.setState({ auth: event.target.checked });
    };


    handleMenu = event => {
        this.setState({ anchorEl: event.currentTarget });
    };

    /**
     * Menu close event
     */
    handleClose = () => {
        this.setState({ anchorEl: null });
    };

    /**
     * Lnb drawer close event
     */
    handleCloseLnb = () => {
        this.setState({isShowLnb: null});
    };

    /**
     * Lnb drawer open event
     */
    handleOpenLnb  = () => {
        this.setState({ isShowLnb: true});
    };

    /**
     * Render
     * @returns {XML}
     */
    render() {
        const { classes } = this.props;
        const open = Boolean(this.state.anchorEl);

        return (
            <div className={classes.root}>
                <AppBar position="static" color="default">
                    <Toolbar>
                        <IconButton className={classes.menuButton} onClick={this.handleOpenLnb} color="inherit" aria-label="Menu">
                            <MenuIcon />
                        </IconButton>
                        <Typography variant="h6" color="inherit" className={classes.grow}>
                            Spomatch
                        </Typography>
                        {this.state.auth && (
                            <div>
                                <IconButton
                                    aria-owns={open ? 'menu-appbar' : undefined}
                                    aria-haspopup="true"
                                    onClick={this.handleMenu}
                                    color="inherit">
                                    <AccountCircle />
                                </IconButton>
                                <Menu
                                    id="menu-appbar"
                                    anchorEl={this.state.anchorEl}
                                    anchorOrigin={{
                                        vertical: 'top',
                                        horizontal: 'right',
                                    }}
                                    transformOrigin={{
                                        vertical: 'top',
                                        horizontal: 'right',
                                    }}
                                    open={open}
                                    onClose={this.handleClose}>
                                    <MenuItem onClick={this.handleClose}>Profile</MenuItem>
                                    <MenuItem onClick={this.handleClose}>My account</MenuItem>
                                </Menu>
                            </div>
                        )}
                    </Toolbar>
                </AppBar>
                <LnbComponent isShowLnb={this.state.isShowLnb} onClosed={this.handleCloseLnb}/>
            </div>
        );
    };
}

GnbComponent.propTypes = {
    classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(GnbComponent);