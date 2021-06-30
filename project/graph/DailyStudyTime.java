//日別学習時間がとってこれるかのテスト用(大谷さんが用意してくれているから不要)

package com.example.StudySupport;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DailyStudyTime {
    int read(Calendar day) {
        day.set(Calendar.HOUR_OF_DAY, 0);
        day.set(Calendar.MINUTE, 0);
        day.set(Calendar.SECOND, 0);
        day.set(Calendar.MILLISECOND, 0);
        int time = -1;

        List<StudyTime> studylist = new ArrayList<StudyTime>();
        StudyTime study1 = new StudyTime();
        StudyTime study2 = new StudyTime();
        Calendar day1 = Calendar.getInstance();
        Calendar day2 = Calendar.getInstance();
        day1.set(Calendar.HOUR_OF_DAY, 0);
        day1.set(Calendar.MINUTE, 0);
        day1.set(Calendar.SECOND, 0);
        day1.set(Calendar.MILLISECOND, 0);
        day2.set(Calendar.HOUR_OF_DAY, 0);
        day2.set(Calendar.MINUTE, 0);
        day2.set(Calendar.SECOND, 0);
        day2.set(Calendar.MILLISECOND, 0);
        study1.date = day1;
        study1.time = 2;
        studylist.add(study1);
        day2.add(Calendar.DATE, -1);
        study2.date = day2;
        study2.time = 4;
        studylist.add(study2);
        System.out.println(studylist.get(0).date + "," + studylist.get(0).time);
        for (int i = 0; i < studylist.size(); i++) {
            if (studylist.get(i).date.compareTo(day) == 0) {
                time = studylist.get(i).time;
            }
        }
        return time;
    }
}
class StudyTime {
    Calendar date;
    int time;
}


