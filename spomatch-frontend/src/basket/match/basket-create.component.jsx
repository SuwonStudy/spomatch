import React, { Component } from 'react';
import PropTypes from 'prop-types';
import classNames from 'classnames';
import { withStyles } from '@material-ui/core/styles';
import MenuItem from '@material-ui/core/MenuItem';
import TextField from '@material-ui/core/TextField';
import IconButton from '@material-ui/core/IconButton';
import InputAdornment from '@material-ui/core/InputAdornment';
import Search from '@material-ui/icons/Search';

const styles = theme => ({
    container: {
        display: 'flex',
        flexWrap: 'wrap',
    },
    textField: {
        marginLeft: theme.spacing.unit,
        marginRight: theme.spacing.unit,
        width: 200,
    },
    menu: {
        width: 200,
    },
});

const attendee = [
    { label: '3 vs 3', value: 3},
    { label: '4 vs 4', value: 4},
    { label: '5 vs 5', value: 5},
    { label: '6 vs 6', value: 6},
    { label: '7 vs 7', value: 7}
    ];

class BasketCreateComponent extends Component {
    state = {
        name: 'Cat in the Hat',
        age: '',
        multiline: 'Controlled',
        currency: 'EUR',
        attendee: 3,
    };

    handleChange = name => event => {
        this.setState({
            [name]: event.target.value,
        });
    };

    handleClickPlace = () => {

    };

    render() {
        const { classes } = this.props;

        return (
            <form className={classes.container} autoComplete="off">
                {/* 매칭 제목 */}
                <TextField
                    required
                    id="match-title"
                    label="매칭 제목"
                    style={{ margin: 8 }}
                    fullWidth
                    margin="normal"
                    InputLabelProps={{
                        shrink: true,
                    }}/>
                {/* 장소 */}
                <TextField
                    id="match-place"
                    label="장소"
                    className={classes.textField}
                    margin="normal"
                    InputLabelProps={{
                        shrink: true,
                    }}
                    InputProps={{
                        readOnly: true
                        ,endAdornment: (
                            <InputAdornment variant="filled" position="end">
                                <IconButton
                                    aria-label="매칭 장소 검색"
                                    onClick={this.handleClickPlace}>
                                    <Search/>
                                </IconButton>
                            </InputAdornment>
                        ),
                    }}
                />
                {/* 경기 참여 인원 */}
                <TextField
                    id="match-attendee"
                    select
                    label="참여 인원"
                    className={classes.textField}
                    value={this.state.attendee}
                    onChange={this.handleChange('attendee')}
                    SelectProps={{
                        MenuProps: {
                            className: classes.menu,
                        },
                    }}
                    helperText="매칭 참여 인원수를 선택하세요"
                    margin="normal">
                    {attendee.map(item => <MenuItem key={item.label} value={item.value}>{item.label}</MenuItem>)}
                </TextField>
                {/* 경기 날짜 설정 */}
                <TextField
                    id="match-time"
                    label="경기 날짜"
                    type="datetime-local"
                    margin="normal"
                    className={classes.textField}
                    InputLabelProps={{
                        shrink: true,
                    }}
                />
            </form>
        );
    }
}

BasketCreateComponent.propTypes = {
    classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(BasketCreateComponent);