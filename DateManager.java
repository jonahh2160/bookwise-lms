// IG 10/12
// Handles calculation and storage for day, month, and year

public class DateManager {
    // This integer array keeps track of how many days is in each month for
    // calculations later
    public int[] daysInMonth = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

    // Input date string of format ("month/day/year") returning int value for month
    public int GetMonth(String dateString) {
        int month = -1;
        String monthString = "";
        for (int i = 0; i < dateString.length(); i++) {
            if (dateString.charAt(i) != 47) {
                monthString += dateString.charAt(i);
            } else {
                break;
            }
        }
        month = Integer.parseInt(monthString);
        return (month);
    }

    // Input date string of format ("month/day/year") returning int value for day
    public int GetDay(String dateString) {
        int day = -1;
        String dayString = "";
        int passed = 0;
        for (int i = 0; i < dateString.length(); i++) {
            if (dateString.charAt(i) != 47) {
                if (passed == 1) {
                    dayString += dateString.charAt(i);
                }
            } else {
                if (passed == 1) {
                    break;
                } else {
                    passed += 1;
                }
            }
        }
        day = Integer.parseInt(dayString);
        return (day);
    }

    // Input date string of format ("month/day/year") returning int value for year
    public int GetYear(String dateString) {
        int year = -1;
        String yearString = "";
        int passed = 0;
        for (int i = 0; i < dateString.length(); i++) {
            if (dateString.charAt(i) != 47) {
                if (passed == 2) {
                    yearString += dateString.charAt(i);
                }
            } else {
                if (passed == 2) {
                    break;
                } else {
                    passed += 1;
                }
            }
        }
        year = Integer.parseInt(yearString);
        return (year);
    }

    // Input dateString and display total amount of days (e.g. to calc due dates)
    private int getDateValue(String dateString) {
        int date = 0;
        int day = GetDay(dateString);
        int month = GetMonth(dateString);
        int year = GetYear(dateString);
        if (month > 1) {
            date += daysInMonth[month];
        }
        date += day;
        date += year * 365;

        return (date);
    }

    // Pass two date strings to output an int equal to no. of days between them
    public int getDateDifference(String date1, String date2) {
        int dateValue1 = getDateValue(date1);
        int dateValue2 = getDateValue(date2);
        return (dateValue2 - dateValue1);
    }

    // This method checks if a given date string is viable
    public int checkDate(String date) {
        int day = GetDay(date);
        int month = GetMonth(date);
        int year = GetYear(date);
        if (day < 1 || month < 1 || month > 12 || year < 0) {
            return(0);
        }
        if (day > daysInMonth[month]) {
            return(0);
        }
        return(1);
    }
}