package towercontest;

import java.util.ArrayList;
import java.util.List;

/**
 * You have built some matchstick towers. A jury will judge the towers, and you know their grade is highly correlated
 * with the height of the towers. Towers have unique names. Towers enter and exit the judging arena at some schedules
 * (always assumed correct). You want to estimate your chances of victory by returning, among the tallest towers currently in
 * the arena, only those that you own. You are given the classes TowerDescription, TowerSchedule and the below signatures
 * and have to complete the class TowerContest. The main method of TowerContest is provided.
 */
public class TowerContest {
    public TowerContest(List<TowerDescription> yourTowers, List<TowerSchedule> yourSchedules) {
    }

    /**
     * The hard part is to determine whether the entering/exiting tower is yours, as you are only given the current time
     * (an integer) and the height of the tower, without its name.
     */
    public void towerEntered(int time, int height) {
    }

    public void towerExited(int time, int height) {
    }

    public List<String> tallestCurrentTowersOwned() {
        return new ArrayList<>();
    }
}
