import React, { Component } from 'React';

const styles = {
    root:
};

class SelectboxComponent extends Component {

    handleClose() {

    }

    render() {
        return (
            <Menu
                id="menu-appbar"
                anchorEl={anchorEl}
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
        );
    }
}

export default withStyles(styles)(SelectboxComponent);