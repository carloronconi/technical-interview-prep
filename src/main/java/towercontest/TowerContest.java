package towercontest;

import java.util.*;
import java.util.stream.Collectors;

/**
 * You have built some matchstick towers. A jury will judge the towers, and you know their grade is highly correlated
 * with the height of the towers. Towers have unique names. Towers enter and exit the judging arena at some schedules
 * (always assumed correct). You want to estimate your chances of victory by returning, among the tallest towers currently in
 * the arena, only those that you own. You are given: the classes TowerDescription, TowerSchedule and the signatures of the
 * constructor and the methods towerEntered, towerExited, tallestCurrentTowersOwned of class TowerContest. You should
 * complete the class TowerContest. The tests are provided (and are very few so try to cover corner cases even
 * if not covered by tests).
 */
public class TowerContest {
    private final List<TowerInfo> myTowers;
    private final List<TowerDescription> currentTowers = new ArrayList<>();
    public TowerContest(List<TowerDescription> yourTowers, List<TowerSchedule> yourSchedules) {
        myTowers = new ArrayList<>();

        // I'm not sure the problem stated that for each of your towers you get a schedule in the same order, so just
        // fill the eventually unscheduled towers with invalid times -1
        Map<String, TowerSchedule> scheduleMap = new HashMap<>();
        for (TowerSchedule s : yourSchedules) {
            scheduleMap.put(s.name(), s);
        }

        for (TowerDescription d : yourTowers) {
            TowerSchedule schedule = scheduleMap.getOrDefault(d.name(), new TowerSchedule(d.name(), -1, -1));
            TowerInfo info = new TowerInfo(d.name(), d.height(), schedule.enterTime(), schedule.exitTime(), false);
            myTowers.add(info);
        }
    }

    private Optional<TowerInfo> findAmongMyOutArenaTowers(int time, int height) {
        return myTowers.stream()
                .filter(t -> !t.inArena() && t.enterTime() == time && t.height() == height)
                .findFirst();
    }

    private Optional<TowerInfo> findAmongMyInArenaTowers(int time, int height) {
        return myTowers.stream()
                .filter(t -> t.inArena() && t.exitTime() == time && t.height() == height)
                .findFirst();
    }

    /**
     * The hard part is to determine whether the entering/exiting tower is yours, as you are only given the current time
     * (an integer) and the height of the tower, without its name.
     */
    public void towerEntered(int time, int height) {
        findAmongMyOutArenaTowers(time, height).ifPresentOrElse(
                info -> {
                    currentTowers.add(new TowerDescription(info.name(), info.height()));
                    myTowers.remove(info);
                    myTowers.add(new TowerInfo(info.name(), info.height(), info.enterTime(), info.exitTime(), true));
                },
                () -> currentTowers.add(new TowerDescription("", height))
        );
    }

    public void towerExited(int time, int height) {
        findAmongMyInArenaTowers(time, height).ifPresentOrElse(
                info -> {
                    currentTowers.removeIf(d -> d.name().equals(info.name()));
                    myTowers.remove(info);
                    myTowers.add(new TowerInfo(info.name(), info.height(), info.enterTime(), info.exitTime(), false));
                },
                () -> currentTowers.removeIf(d -> d.name().isEmpty() && d.height() == height)
        );
    }

    public List<String> tallestCurrentTowersOwned() {
        int maxHeight = currentTowers.stream()
                .map(TowerDescription::height)
                .max(Integer::compareTo)
                .orElse(-1);

        return currentTowers.stream()
                .filter(d -> !d.name().isEmpty() && d.height() == maxHeight)
                .map(TowerDescription::name)
                .collect(Collectors.toList());
    }
}
