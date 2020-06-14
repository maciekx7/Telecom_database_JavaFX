package application.tools.date.trashNaukaWyjatkow;

import application.tools.date.trashNaukaWyjatkow.DateExeption;

class DateValidator {
    static void validator(int day, int month, int year) throws DateExeption {
        boolean flag = false;
        if(month <= 12) {
            if (month == 1 || month == 3 || month == 5 || month == 7 || month == 9 || month == 11) {
                if (day <= 31) {
                    flag = true;
                }
            } else if (month == 4 || month == 6 || month == 8 || month == 10 || month == 12) {
                if (day <= 30) {
                    flag = true;
                }
            } else if (month == 2) {
                if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                    if (day <= 29) {
                        flag = true;
                    }
                } else {
                    if (day <= 28) {
                        flag = true;
                    }
                }
            }
        }
        if(flag==false) {
            throw new DateExeption("Your date is inappropriate! (" + year + "-" + month + "-" + day + ")");
        }
    }

    static void initializationValidator(int day, int month, int year) throws DateExeption{
        if(!(day!= 0 && month!= 0 && year !=0)) {
            throw new DateExeption("You haven't initializated date properely and we have nothing to get you.");
        }
    }
}

