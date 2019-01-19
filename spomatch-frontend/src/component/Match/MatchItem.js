import React from 'react';
import {Typography} from "@material-ui/core";
import Grid from '@material-ui/core/Grid';
import withStyles from "@material-ui/core/styles/withStyles";

const styles = theme => ({
    title: {
        textAlign: 'center'
    },
    home: {
        textAlign: 'center'
    },
    away: {
        textAlign: 'center'
    }
});

const MatchItem = ({classes, match}) => {
    return (
        <div>
            {/* HOME team */}
            <Grid className={classes.home}>
                <Typography className={classes.title}>HOME</Typography>
                <Typography>{match.homeTeam}</Typography>
            </Grid>
            {/* Match info */}
            <Grid className={classes.home}>{team.home}
                {/* 경기 날짜 */}
                <Typography>{match.date}</Typography>
                {/* 경기 시간 */}
                <Typography>{match.time}</Typography>
                {/* 경기 장소 */}
                <Typography>{match.place}</Typography>
            </Grid>
            {/* AWAY info */}
            <Grid className={classes.away}>
                <Typography className={classes.title}>AWAY</Typography>
                <Typography>{match.awayTeam}</Typography>
            </Grid>
        </div>
    );
};
export default withStyles(styles)(MatchItem);