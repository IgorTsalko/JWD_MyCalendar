package by.epamtc.tsalko.main;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class MyCalendar {

    private String[][][] calendar = new String[3][4][];

    public MyCalendar(int year) {
        createCalendar(year);
    }

    private void createCalendar(int year) {
        for (int i = 0, k = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                calendar[i][j] = formMonth(year, k++);
            }
        }
    }

    private String[] formMonth(int year, int month) {
        Calendar calendar = new GregorianCalendar(year, month, 1);
        String[] monthLines = new String[8];

        String monthName = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG_STANDALONE, Locale.getDefault());
        monthLines[0] = String.format("%13s", monthName.substring(0, 1).toUpperCase() + monthName.substring(1));
        StringBuilder namesOfDaysOfWeek = new StringBuilder()
                .append(String.format("%3s", "пн"))
                .append(String.format("%3s", "вт"))
                .append(String.format("%3s", "ср"))
                .append(String.format("%3s", "чт"))
                .append(String.format("%3s", "пт"))
                .append(String.format("%3s", "сб"))
                .append(String.format("%3s", "вс"));

        monthLines[1] = namesOfDaysOfWeek.toString();

        StringBuilder weekdayNumbers;
        int weekCounter = 0;
        for (int i = 0; i < calendar.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            weekdayNumbers = new StringBuilder();

            if (i == 0 && dayOfWeek != Calendar.SUNDAY) {
                weekdayNumbers.append(String.format("%" + ((dayOfWeek - 1) * 3) + "d", dayOfMonth));
            } else if (i == 0) {
                weekdayNumbers.append(String.format("%21d", dayOfMonth));
            } else {
                weekdayNumbers.append(String.format("%3d", dayOfMonth));
            }

            if (dayOfWeek == Calendar.SUNDAY || dayOfMonth == calendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
                monthLines[weekCounter++ + 2] = weekdayNumbers.toString();
            }

            calendar.roll(Calendar.DAY_OF_MONTH, 1);
        }

        return monthLines;
    }

    public void printCalendar() {
        StringBuilder calendarBuf = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 4; k++) {
                    String monthLine = calendar[i][k][j] != null ? calendar[i][k][j] : "";
                    calendarBuf.append(String.format("%-24s", monthLine));
                }
                calendarBuf.append("\n");
            }
            calendarBuf.append("\n");
        }
        System.out.println(calendarBuf);
    }
}
