// IG 10/12
// 

public class DateManager {
    // This integer array keeps track of how many days is in each month for
    // calculations later
    int[] daysInMonth = { -1, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

    // This method will take a date string input ("month/day/year") and output the
    // integer value for month
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

    // This method will take a date string ("month/day/year") and return the integer
    // value for the day
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

    // This method will take a date string ("month/day/year") and return the integer
    // value for the year
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

    // This method will take a date string ("month/day/year") and return one integer
    // displaying the total amount of days (used for calculating due dates etc.)
    private int getDateValue(String dateString) {
        int date = 0;
        int day = GetDay(dateString);
        int month = GetMonth(dateString);
        int year = GetYear(dateString);
        if (month > 1) {
            date += daysInMonth[month - 1];
        } else {
            date += daysInMonth[13];
        }
        ;
        date += day;
        date += year * 365;

        return (date);
    }

    // This method takes two date strings and will output an integer of the amount
    // of days between the first and second date
    public int getDateDifference(String date1, String date2) {
        int dateValue1 = getDateValue(date1);
        int dateValue2 = getDateValue(date2);
        return (dateValue2 - dateValue1);
    }
}