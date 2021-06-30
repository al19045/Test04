package com.example.StudySupport;

import java.util.Calendar;

public class Percentcal {
    int cal() {
        GoalSyori goalsyori = new GoalSyori();
        DailyStudyTime dailystudytime = new DailyStudyTime();
        String goal = goalsyori.getGoal();
        Calendar cal = Calendar.getInstance();
        int weektime = 0;
        int time = 0;
        double percent = 0.0;
        int intgoal = Integer.parseInt(goal);
        switch (cal.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.SUNDAY:
                for (int i = 7; i > 0; i--) {
                    time = dailystudytime.read(cal);
                    weektime += time;
                    cal.add(Calendar.DATE, -1);
                }
                break;
            case Calendar.MONDAY:
                time = dailystudytime.read(cal);
                weektime = time;
                break;
            case Calendar.TUESDAY:
                for (int i = 2; i > 0; i--) {
                    time = dailystudytime.read(cal);
                    weektime += time;
                    cal.add(Calendar.DATE, -1);
                }
                break;
            case Calendar.WEDNESDAY:
                for (int i = 3; i > 0; i--) {
                    time = dailystudytime.read(cal);
                    weektime += time;
                    cal.add(Calendar.DATE, -1);
                }
                break;
            case Calendar.THURSDAY:
                for (int i = 4; i > 0; i--) {
                    time = dailystudytime.read(cal);
                    weektime += time;
                    cal.add(Calendar.DATE, -1);
                }
                break;
            case Calendar.FRIDAY:
                for (int i = 5; i > 0; i--) {
                    time = dailystudytime.read(cal);
                    weektime += time;
                    cal.add(Calendar.DATE, -1);
                }
                break;
            case Calendar.SATURDAY:
                for (int i = 6; i > 0; i--) {
                    time = dailystudytime.read(cal);
                    weektime += time;
                    cal.add(Calendar.DATE, -1);
                }
                break;
        }
        weektime += 1;
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaa"+weektime);
        percent = 100 * weektime / intgoal;

        int per = (int) percent;
        return per;
    }
}
