package application.tools.date;

import application.Alerts.ErrorAlert;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class DateTool{
    protected LocalDate date;

    static Map<String, String> monthsMap = Stream.of(new Object[][] {
            { "1", "sty" },
            { "2", "lut" },
            { "3", "mar" },
            { "4", "kwi" },
            { "5", "maj" },
            { "6", "cze" },
            { "7", "lip" },
            { "8", "sie" },
            { "9", "wrz" },
            { "01", "sty" },
            { "02", "lut" },
            { "03", "mar" },
            { "04", "kwi" },
            { "05", "maj" },
            { "06", "cze" },
            { "07", "lip" },
            { "08", "sie" },
            { "09", "wrz" },
            { "10", "paz" },
            { "11", "lis" },
            { "12", "gru" },
    }).collect(Collectors.toMap(data -> (String) data[0], data -> (String) data[1]));


    /** CONSTRUCTORS */
    /*
    private DateTool() { }

    private DateTool(String rawDate) {
        try {
            System.out.println(rawDate);
            String[] trash = rawDate.split(" ");
            String[] usefulDate = trash[0].split("-");
            System.out.println(this.getAsString());
            date = LocalDate.of(Integer.parseInt(usefulDate[2]),Integer.parseInt(usefulDate[1]),Integer.parseInt(usefulDate[0]));
        } catch (Exception e) {
            try {
                String[] trash = rawDate.split(" ");
                String[] usefulDate = trash[0].split("-");
                System.out.println(this.getAsString());
                date = LocalDate.of(Integer.parseInt(usefulDate[0]),Integer.parseInt(usefulDate[1]),Integer.parseInt(usefulDate[2]));
            } catch (Exception ex) {
                ErrorAlert.errorAlert(ex,DateTool.class.getName());
            }
        }

    }

    private DateTool(int day, int month, int year) {
        try {
            date = LocalDate.of(year,month,day);
        } catch (Exception e) {
            ErrorAlert.errorAlert(e, DateTool.class.getName());
        }
    }

    private DateTool(DateTool copy) {
        try {
            date = copy.date;
        } catch (Exception e) {
            ErrorAlert.errorAlert(e,DateTool.class.getName());
        }
    }

    private DateTool(LocalDate localDate) {
        try {
            date = localDate;
        } catch (Exception e) {
            ErrorAlert.errorAlert(e, DateTool.class.getName());
        }
    }



    private void setDate(String rawDate) {
        try {
            String[] trash = rawDate.split(" ");
            String[] usefulDate = trash[0].split("-");
            date = LocalDate.of(Integer.parseInt(usefulDate[0]),Integer.parseInt(usefulDate[1]),Integer.parseInt(usefulDate[2]));
        } catch (Exception e) {
            ErrorAlert.errorAlert(e,DateTool.class.getName());
        }
    }

    private void setDate(int day, int month, int year) {
        try {
            date = LocalDate.of(year,month,day);
        } catch (Exception e) {
            ErrorAlert.errorAlert(e,DateTool.class.getName());
        }
    }

    private void setDate(LocalDate localDate) {
        try {
            date = localDate;
        } catch (Exception e) {
            ErrorAlert.errorAlert(e, DateTool.class.getName());
        }
    }


    private void setDate(DateTool copy) {
        try {
            date = copy.date;
        } catch (Exception e) {
            ErrorAlert.errorAlert(e, DateTool.class.getName());
        }
    }



    private LocalDate get() {
        return date;
    }

     */


    /** ----------------------STATIC----------------------------- */
    public static class getDateWithMonthNames {
        public static String YearFirst(String date) {
            try {
                String[] trahs = date.split("-");
                return trahs[2] + " " + monthsMap.get(trahs[1]) + " " + trahs[0];
            } catch (Exception e) {
                ErrorAlert.errorAlert(e, DateTool.class.getName());
            }
            return "";
        }
        public static String DayFirst(String date) {
            try {
                String[] trahs = date.split("-");
                return trahs[0] + " " + monthsMap.get(trahs[1]) + " " + trahs[2];
            } catch (Exception e) {
                ErrorAlert.errorAlert(e, DateTool.class.getName());
            }
            return "";
        }
    }

    public static String getStrDateFromOracleDB(String rawDate) {
        try {
            String[] trahs = rawDate.split(" ");
            return trahs[0];
        } catch (Exception e) {
            ErrorAlert.errorAlert(e, DateTool.class.getName());
        }
        return "";
    }

    public static LocalDate getLocalDate(String date) {
        try {
            String[] usefulDate = date.split("-");
            return LocalDate.of(Integer.parseInt(usefulDate[0]),Integer.parseInt(usefulDate[1]),Integer.parseInt(usefulDate[2]));
        } catch (Exception e) {
            try {
                String[] usefulDate = date.split("-");
                return LocalDate.of(Integer.parseInt(usefulDate[2]),Integer.parseInt(usefulDate[1]),Integer.parseInt(usefulDate[0]));
            } catch (Exception ex) {
                ErrorAlert.errorAlert(e,DateTool.class.getName());
            }
        }
        return null;
    }

    public static String addMonths_(String date, int months) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd");
        try{
            c.setTime(dateFormat.parse(date));
        } catch (Exception e) {
            ErrorAlert.errorAlert(e, DateTool.class.getName());
        }
        c.add(Calendar.MONTH, months);
        return dateFormat.format(c.getTime());
    }



   /*
    public void clear() {
        date = LocalDate.now();
    }

    */

    /** Add months to date and return inself */
    /*
    public DateTool addMonths(int months) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd");
        try{
            c.setTime(dateFormat.parse(this.getAsString()));
        } catch (Exception e) {
            ErrorAlert.errorAlert(e, DateTool.class.getName());
        }
        c.add(Calendar.MONTH, months);
        this.setDate(dateFormat.format(c.getTime()));
        return this;
    }\
    */

    /** Add days to date and return inself */
    /*
    public DateTool addDays(int days) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd");
        try{
            c.setTime(dateFormat.parse(this.getAsString()));
        } catch (Exception e) {
            ErrorAlert.errorAlert(e, DateTool.class.getName());
        }
        c.add(Calendar.DAY_OF_MONTH, days);
        this.setDate(dateFormat.format(c.getTime()));
        return this;
    }

     */

    /** get Data with month names (in PL) as String */
    /*
    public String dateWithMonthName() {
        try {
            System.out.println(date);
            String[] trahs = this.getAsString().split("-");
            return trahs[2] + " " + monthsMap.get(trahs[1]) + " " + trahs[0];
        } catch (Exception e) {
            ErrorAlert.errorAlert(e, DateTool.class.getName());
        }
        return "";
    }

     */

    /** return date in format 'yyy-MM-dd' */
    /*
    public String getAsString() {
        return String.valueOf(date);
    }

    public String getAsReversedString() {
        return date.getDayOfMonth() + "-" + date.getMonthValue() + "-" + date.getYear();
    }

    public DateTool getDateAfter(int months) {
        DateTool tmp = new DateTool(this);
        tmp.addMonths(months);
        return tmp;
    }

     */
}
