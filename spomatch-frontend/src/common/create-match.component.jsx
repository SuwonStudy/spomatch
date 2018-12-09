import React, {Component, Fragment} from 'react';
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import Button from '@material-ui/core/Button';
import Dialog from '@material-ui/core/Dialog';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import IconButton from '@material-ui/core/IconButton';
import Typography from '@material-ui/core/Typography';
import CloseIcon from '@material-ui/icons/Close';
import Slide from '@material-ui/core/Slide';
import AddIcon from '@material-ui/icons/Add';
import BasketCreateComponent from '../basket/match/basket-create.component'
import AlertUtil from '../common/alert.util'

const styles = theme => ({
    appBar: {
        position: 'relative',
    },
    flex: {
        flex: 1,
    },
    button: {
        margin: theme.spacing.unit,
        // position: 'absolute',
        bottom: theme.spacing.unit * 2,
        right: theme.spacing.unit * 2,
    },

    extendedIcon: {
        marginRight: theme.spacing.unit,
    },
});


function Transition(props) {
    return <Slide direction="up" {...props} />;
}

class CreateMatchComponent extends Component {

    state = {
        open: false,
    };



    /**
     * Create Match open click event
     */
    handleOpen = () => {
        this.setState({ open: true });
    };

    /**
     * Create Match close click event
     */
    handleClose = () => {
        this.setState({ open: false });
    };

    /**
     * Create Match click event
     */
    handleCreate = () => {
        // TODO call create api
        // callback close
        this.handleClose();
        // this.testAPI()
        //     .then(result => AlertUtil.success('매칭이 생성되었습니다'))
        //     .catch(error => AlertUtil.error('실패'));
        // alert
        AlertUtil.success('매칭이 생성되었습니다');
    };

    /**
     * Render
     */
    render() {
        const { classes } = this.props;


        return (
            <Fragment>
                {/* BUTTON */}
                <Button variant="extendedFab" aria-label="create-match" className={classes.button} onClick={this.handleOpen}>
                    <AddIcon className={classes.extendedIcon} />
                    Create match
                </Button>
                {/* DIALOG */}
                <Dialog
                    fullScreen
                    open={this.state.open}
                    onClose={this.handleClose}
                    TransitionComponent={Transition}>
                    <AppBar className={classes.appBar} color="default">
                        <Toolbar>
                            <IconButton color="inherit" onClick={this.handleClose} aria-label="Close">
                                <CloseIcon />
                            </IconButton>
                            <Typography variant="h6" color="inherit" className={classes.flex}>
                                Create Match
                            </Typography>
                            <Button color="inherit" onClick={this.handleCreate}>
                                Create
                            </Button>
                        </Toolbar>
                    </AppBar>
                    {/* Match create field component */}
                    {(() => {
                        switch (this.props.mode) {
                            // basket mode
                            case "BASKET":
                                return <BasketCreateComponent/>;
                            // soccer mode
                            case "SOCCER":
                                return;
                        }
                    })()}
                </Dialog>
            </Fragment>
        );
    }
}

CreateMatchComponent.propTypes = {
    classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(CreateMatchComponent);