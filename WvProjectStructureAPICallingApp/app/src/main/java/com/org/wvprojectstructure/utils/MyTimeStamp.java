package com.org.wvprojectstructure.utils;
import android.util.Log;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class MyTimeStamp {
    public static long getEpochFromDate(String date) {
        long epochTime = 0;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        //  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        try {
            Date date1 = formatter.parse(date);
            assert date1 != null;
            epochTime = date1.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return epochTime / 1000;
    }

    public static long EpochTimeFun(Date today) {
        long epochTime = 0;
        //Date today = Calendar.getInstance().getTime();
        // Constructs a SimpleDateFormat using the given pattern
        SimpleDateFormat crunchifyFormat = new SimpleDateFormat("MMM dd yyyy HH:mm:ss.SSS zzz", Locale.ENGLISH);
        // format() formats a Date into a date/time string.
        String currentTime = crunchifyFormat.format(today);
        //log("Current Time = " + currentTime);

        try {
            //parse() parses text from the beginning of the given string to produce a date.
            Date date = crunchifyFormat.parse(currentTime);
            //getTime() returns the number of milliseconds since January 1, 1970, 00:00:00 GMT represented by this Date object.
            epochTime = date.getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return epochTime / 1000;
    }


    public static String dateFormatConvert(String dateData) {
        String inputPattern = "yyyy-mm-dd";
        String outputPattern = "mm/dd/yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern, Locale.ENGLISH);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern, Locale.ENGLISH);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(dateData);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String TimeFormatConvert(String time) {
        String inputPattern = "H:mm";
        String outputPattern = "K:mm a";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern, Locale.ENGLISH);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern, Locale.ENGLISH);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String getCurrentDate() {
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        // formattedDate have current date/time
        return df.format(c.getTime());
    }

    public static String getCurrentTime() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
        // formattedDate have current date/time
        return df.format(c.getTime());
    }

    public static String getCurrentTimeDotFormat(String orderType) {
        //Log.e("order", orderType);

        Calendar self = Calendar.getInstance();
        self.set(Calendar.HOUR_OF_DAY, 23);
        self.set(Calendar.MINUTE, 0);
        self.set(Calendar.SECOND, 0);

        Calendar later = Calendar.getInstance();
        later.set(Calendar.HOUR_OF_DAY, 22);
        later.set(Calendar.MINUTE, 30);
        later.set(Calendar.SECOND, 0);

        Calendar c = Calendar.getInstance();
        Log.e("order", c.getTime() + "" + self.getTime());
        SimpleDateFormat df = new SimpleDateFormat("HH.mm", Locale.ENGLISH);
        if (orderType.equalsIgnoreCase("Self Pickup Time") && c.getTime().before(self.getTime())) {
            c.add(Calendar.MINUTE, 60);
        } else if (orderType.equalsIgnoreCase("Order For Later Time") && c.getTime().before(later.getTime())) {
            c.add(Calendar.MINUTE, 90);
        }

        // formattedDate have current date/time
        return df.format(c.getTime());
    }

    public static String convertTimeToOneHour(String stringUTCDate) {
        //Log.e("tiome",stringUTCDate);
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
        formatter.setLenient(false);
        Calendar cal = Calendar.getInstance();
        try {
            if (stringUTCDate != null && stringUTCDate.length() > 0) {
                Date oldDate = formatter.parse(stringUTCDate);
                assert oldDate != null;
                cal.setTime(oldDate);
                cal.add(Calendar.MINUTE, 60);
            }
        } catch (Exception e) {
            //  Log.e("Exxx",e.toString());
        }

        return formatter.format(cal.getTime());
    }

    public static String convertTimeToOneHalfHour(String stringUTCDate) {

        //  Log.e("tiome",stringUTCDate);
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
        formatter.setLenient(false);
        Calendar cal = Calendar.getInstance();
        try {
            if (stringUTCDate != null && stringUTCDate.length() > 0) {
                Date oldDate = formatter.parse(stringUTCDate);
                assert oldDate != null;
                cal.setTime(oldDate);
                cal.add(Calendar.MINUTE, 90);
            }
        } catch (Exception e) {
            //  Log.e("Exxx",e.toString());
        }

        return formatter.format(cal.getTime());
    }

/*    public static String getCurrentTime(){
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+05:30"));
        Date currentLocalTime = cal.getTime();
        DateFormat date = new SimpleDateFormat("HH:mm a");
        date.setTimeZone(TimeZone.getTimeZone("GMT+05:30"));
        String localTime = date.format(currentLocalTime);
        return localTime;
    }*/

    //formatter.locale = Locale(identifier: "en_US")
    //formatter.timeZone = TimeZone(abbreviation: "UTC"

    public static String getTomorrowDate() {
        Calendar calendar = Calendar.getInstance();
        System.out.println("Current time => " + calendar.getTime());
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        return df.format(calendar.getTime());
    }


    public static String epochToDateAppFormat(long time) {
        //String format = "dd/MM/yyyy HH:mm a";
        String format = "MMM dd, yyyy";
        //String format = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        sdf.setTimeZone(TimeZone.getDefault());
        return sdf.format(new Date(time * 1000)).replace("am", "AM").replace("pm", "PM");
    }

    public static String epochToDateTimeAppFormat(long time) {
        //String format = "dd/MM/yyyy HH:mm a";
        String format = "MMM dd, yyyy hh:mm a";
        //String format = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        sdf.setTimeZone(TimeZone.getDefault());
        return sdf.format(new Date(time * 1000)).replace("am", "AM").replace("pm", "PM");
    }

    public static String epochToIso8601(long time) {
        //String format = "dd/MM/yyyy HH:mm a";
        String format = "dd/MM/yyyy hh:mm a";
        //String format = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        sdf.setTimeZone(TimeZone.getDefault());
        return sdf.format(new Date(time * 1000)).replace("am", "AM").replace("pm", "PM");
    }

    public static String epochToTime(long time) {
        String format = "hh:mm a";
        //String format = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        sdf.setTimeZone(TimeZone.getDefault());
        return sdf.format(new Date(time * 1000)).replace("am", "AM").replace("pm", "PM");
    }

    public static String epochToTimeWithoutAmPm(long time) {
        String format = "HH:mm";
        //String format = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        sdf.setTimeZone(TimeZone.getDefault());
        return sdf.format(new Date(time * 1000));
    }

    public static String epochToDate(long time) {
        String format = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        sdf.setTimeZone(TimeZone.getDefault());
        return sdf.format(new Date(time * 1000));
    }

    public static boolean epochTimeToTimeStamp(String openTime, String closeTime, String breakStart, String breakEnd) {
        Date openDate = null;
        Date closeDate = null;
        Date breakStarDate = null;
        Date breakEndDate = null;
        Date now = null;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
            // formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            if (openTime != null && openTime.length() > 0)
                openDate = formatter.parse(openTime);
            if (closeTime != null && closeTime.length() > 0)
                closeDate = formatter.parse(closeTime);
            if (breakStart != null && breakStart.length() > 0)
                breakStarDate = formatter.parse(breakStart);
            if (breakEnd != null && breakEnd.length() > 0)
                breakEndDate = formatter.parse(breakEnd);
            Date nowDate = new Date();
            String date2 = formatter.format(nowDate);
            now = formatter.parse(date2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (openTime != null && openTime.length() > 0 && closeTime != null && closeTime.length() > 0 && breakStart != null && breakStart.length() > 0 && breakEnd != null && breakEnd.length() > 0) {
            assert now != null;
            if ((now.after(openDate) && now.before(breakStarDate)) || (now.before(closeDate) && now.after(breakEndDate))) {
                //Log.e("Date is between ", "isRestaurantClose-----------" + openDate + " " + openTime + " " + closeDate + " " + closeTime + " " + breakStarDate + " " + breakStart + " " + breakEndDate + " " + breakEnd);
                return true;
            }
        } else if (openTime != null && openTime.length() > 0 && closeTime != null && closeTime.length() > 0) {
            assert now != null;
            if ((now.after(openDate) && (now.before(closeDate)))) {
                //Log.e("Date is between ", "isRestaurantClose-----------" + openDate + " " + openTime + " " + closeDate + " " + closeTime + " " + breakStarDate + " " + breakStart + " " + breakEndDate + " " + breakEnd);
                return true;
            }
        }
        //Log.e("Date is not between ", "isRestaurantClose-----------" + openDate + " " + openTime + " " + closeDate + " " + closeTime + " " + breakStarDate + " " + breakStart + " " + breakEndDate + " " + breakEnd);
        return false;
    }


    public static boolean ifTimeIsAdded(String orderType) {
        //Log.e("order", orderType);

        Calendar self = Calendar.getInstance();
        self.set(Calendar.HOUR_OF_DAY, 23);
        self.set(Calendar.MINUTE, 0);
        self.set(Calendar.SECOND, 0);

        Calendar later = Calendar.getInstance();
        later.set(Calendar.HOUR_OF_DAY, 22);
        later.set(Calendar.MINUTE, 30);
        later.set(Calendar.SECOND, 0);

        Calendar c = Calendar.getInstance();
        // Log.e("order", c.getTime() + "" + self.getTime());
        if (orderType.equalsIgnoreCase("Self Pickup Time") && c.getTime().before(self.getTime())) {
            return false;
        } else if (orderType.equalsIgnoreCase("Order For Later Time") && c.getTime().before(later.getTime())) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean epochCurrentTimeToTimeStamp(String openTime, String closeTime, String breakStart, String breakEnd, String current, String end, boolean isOrderLater, boolean isCurrentDay) {
        Date openDate = null;
        Date closeDate = null;
        Date breakStarDate = null;
        Date breakEndDate = null;
        Date currentNow = null, currentEnd = null;

        Calendar c1 = Calendar.getInstance();
        c1.set(Calendar.DATE, 1);
        c1.set(Calendar.MONTH, 0);
        c1.set(Calendar.YEAR, 1970);
        c1.set(Calendar.HOUR_OF_DAY, 23);
        c1.set(Calendar.MINUTE, 0);
        c1.set(Calendar.SECOND, 0);

        Date self = c1.getTime();

        Calendar c2 = Calendar.getInstance();
        c1.set(Calendar.DATE, 1);
        c1.set(Calendar.MONTH, 0);
        c1.set(Calendar.YEAR, 1970);
        c2.set(Calendar.HOUR_OF_DAY, 22);
        c2.set(Calendar.MINUTE, 30);
        c2.set(Calendar.SECOND, 0);

        Date later = c1.getTime();

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
            // formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            if (openTime != null && openTime.length() > 0)
                openDate = formatter.parse(openTime);
            if (closeTime != null && closeTime.length() > 0)
                closeDate = formatter.parse(closeTime);
            if (breakStart != null && breakStart.length() > 0)
                breakStarDate = formatter.parse(breakStart);
            if (breakEnd != null && breakEnd.length() > 0)
                breakEndDate = formatter.parse(breakEnd);
            // Date nowDate = new Date();
            // String date2 = formatter.parse(current);
            currentNow = formatter.parse(current);
            currentEnd = formatter.parse(end);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (openTime != null && openTime.length() > 0 && closeTime != null && closeTime.length() > 0 && breakStart != null && breakStart.length() > 0 && breakEnd != null && breakEnd.length() > 0) {
            assert currentNow != null;
            assert currentEnd != null;
            assert breakStarDate != null;
            assert breakEndDate != null;
            if (breakStarDate.after(currentNow) && breakEndDate.after(currentNow) && breakStarDate.before(currentEnd) && breakEndDate.before(currentEnd)) {
                //Log.e("Date is not between ", "isRestaurantClose-----------" + openDate + " " + openTime + " " + closeDate + " " + closeTime + " " + breakStarDate + " " + breakStart + " " + breakEndDate + " " + breakEnd + " " + current);
                return false;
            } else if ((currentNow.after(openDate) && currentNow.before(breakStarDate)) || (currentNow.before(closeDate) && currentNow.after(breakEndDate))) {
                if (!isCurrentDay) {
                    if (ifTimeIsAdded("Order For Later Time")) {
                        if (isOrderLater && currentNow.after(later)) {
                            //Log.e("Date is between ", "isRestaurantClose1-----------" + later + " " + openDate + " " + openTime + " " + closeDate + " " + closeTime + " " + breakStarDate + " " + breakStart + " " + breakEndDate + " " + breakEnd + " " + current);
                            return false;
                        } else if (currentNow.after(self) && !ifTimeIsAdded("Self Pickup Time")) {
                            //Log.e("Date is between ", "isRestaurantClose6-----------" + later + " " + openDate + " " + openTime + " " + closeDate + " " + closeTime + " " + breakStarDate + " " + breakStart + " " + breakEndDate + " " + breakEnd + " " + current);
                            return false;
                        }
                    } else if (ifTimeIsAdded("Self Pickup Time")) {
                        if (isOrderLater && currentNow.after(later)) {
                            //Log.e("Date is between ", "isRestaurantClose1-----------" + later + " " + openDate + " " + openTime + " " + closeDate + " " + closeTime + " " + breakStarDate + " " + breakStart + " " + breakEndDate + " " + breakEnd + " " + current);
                            return false;
                        } else if (currentNow.after(self) && !ifTimeIsAdded("Self Pickup Time")) {
                            //Log.e("Date is between ", "isRestaurantClose6-----------" + later + " " + openDate + " " + openTime + " " + closeDate + " " + closeTime + " " + breakStarDate + " " + breakStart + " " + breakEndDate + " " + breakEnd + " " + current);
                            return false;
                        }
                    } else {
                        if ((currentNow.after(openDate) && currentNow.before(breakStarDate)) || (currentNow.before(closeDate) && currentNow.after(breakEndDate))) {
                            //Log.e("Date is between ", "isRestaurantClose7-----------" + later + " " + openDate + " " + openTime + " " + closeDate + " " + closeTime + " " + breakStarDate + " " + breakStart + " " + breakEndDate + " " + breakEnd + " " + current);
                            return true;
                        } else {
                            return false;
                        }
                    }
                } else {
                    if ((currentNow.after(openDate) && currentNow.before(breakStarDate)) || (currentNow.before(closeDate) && currentNow.after(breakEndDate))) {
                        //Log.e("Date is between ", "isRestaurantClose7-----------" + later + " " + openDate + " " + openTime + " " + closeDate + " " + closeTime + " " + breakStarDate + " " + breakStart + " " + breakEndDate + " " + breakEnd + " " + current);
                        return true;
                    } else {
                        return false;
                    }
                }
            } else if (currentNow.compareTo(openDate) == 0) {
                Log.e("Date is between ", "isRestaurantClose2-----------" + openDate + " " + openTime + " " + closeDate + " " + closeTime + " " + breakStarDate + " " + breakStart + " " + breakEndDate + " " + breakEnd + " " + current);
                return true;
            } else if (currentNow.compareTo(breakEndDate) == 0) {
                Log.e("Date is between ", "isRestaurantClose3-----------" + openDate + " " + openTime + " " + closeDate + " " + closeTime + " " + breakStarDate + " " + breakStart + " " + breakEndDate + " " + breakEnd + " " + current);
                return true;
            }
        } else if (openTime != null && openTime.length() > 0 && closeTime != null && closeTime.length() > 0) {
            assert currentNow != null;
            if ((currentNow.after(openDate) && (currentNow.before(closeDate)))) {
                Log.e("Date is between ", "isRestaurantClose4-----------" + openDate + " " + openTime + " " + closeDate + " " + closeTime + " " + breakStarDate + " " + breakStart + " " + breakEndDate + " " + breakEnd + " " + current);
                if (!isCurrentDay) {
                    if (ifTimeIsAdded("Order For Later Time")) {
                        if (isOrderLater && currentNow.after(later)) {
                            //Log.e("Date is between ", "isRestaurantClose1-----------" + later + " " + openDate + " " + openTime + " " + closeDate + " " + closeTime + " " + breakStarDate + " " + breakStart + " " + breakEndDate + " " + breakEnd + " " + current);
                            return false;
                        } else if (currentNow.after(self) && !ifTimeIsAdded("Self Pickup Time")) {
                            //Log.e("Date is between ", "isRestaurantClose6-----------" + later + " " + openDate + " " + openTime + " " + closeDate + " " + closeTime + " " + breakStarDate + " " + breakStart + " " + breakEndDate + " " + breakEnd + " " + current);
                            return false;
                        }
                    } else if (ifTimeIsAdded("Self Pickup Time")) {
                        if (isOrderLater && currentNow.after(later)) {
                            //Log.e("Date is between ", "isRestaurantClose1-----------" + later + " " + openDate + " " + openTime + " " + closeDate + " " + closeTime + " " + breakStarDate + " " + breakStart + " " + breakEndDate + " " + breakEnd + " " + current);
                            return false;
                        } else if (currentNow.after(self) && !ifTimeIsAdded("Self Pickup Time")) {
                            //Log.e("Date is between ", "isRestaurantClose6-----------" + later + " " + openDate + " " + openTime + " " + closeDate + " " + closeTime + " " + breakStarDate + " " + breakStart + " " + breakEndDate + " " + breakEnd + " " + current);
                            return false;
                        }
                    } else {
                        if ((currentNow.after(openDate) && (currentNow.before(closeDate)))) {
                            //Log.e("Date is between ", "isRestaurantClose7-----------" + later + " " + openDate + " " + openTime + " " + closeDate + " " + closeTime + " " + breakStarDate + " " + breakStart + " " + breakEndDate + " " + breakEnd + " " + current);
                            return true;
                        } else {
                            return false;
                        }
                    }
                } else {
                    if ((currentNow.after(openDate) && (currentNow.before(closeDate)))) {
                        //Log.e("Date is between ", "isRestaurantClose7-----------" + later + " " + openDate + " " + openTime + " " + closeDate + " " + closeTime + " " + breakStarDate + " " + breakStart + " " + breakEndDate + " " + breakEnd + " " + current);
                        return true;
                    } else {
                        return false;
                    }
                }
            } else if (currentNow.compareTo(openDate) == 0) {
                Log.e("Date is between ", "isRestaurantClose5-----------" + openDate + " " + openTime + " " + closeDate + " " + closeTime + " " + breakStarDate + " " + breakStart + " " + breakEndDate + " " + breakEnd + " " + current);
                return true;
            }
        }
        //Log.e("Date is not between ", "isRestaurantClose-----------" + openDate + " " + openTime + " " + closeDate + " " + closeTime + " " + breakStarDate + " " + breakStart + " " + breakEndDate + " " + breakEnd + " " + current);
        return false;
    }



/*String startDate = CommonMethods.dateFromStamp(model.getDate(), "dd-MM-yyyy");
String startTime = model.getStartTime();
String strEventDateTime = startDate.concat(" ").concat(startTime);
long longDate = CommonMethods.getDateTimeMergeLong(strEventDateTime, "dd-MM-yyyy kk:mm");*/

    public static long getDateTimeMergeLong(String str_date, String format) {
        long dob = 0;
        SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.getDefault());
        try {
            Date date = (Date) formatter.parse(str_date);
            dob = date.getTime() / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dob;
    }

    public static boolean isBeforeCurrentTime(String openTime) {
        long openDate = Long.parseLong(openTime) * 1000;
        long now = 0;
        try {
            now = System.currentTimeMillis();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (openTime.length() > 0) {
            if (openDate > now) {
                //Log.e("Date is between ", "isRestaurantClose-----------" + openDate + " " + openTime);
                return true;
            }
        }
        //Log.e("Date is not between ", "isRestaurantClose-----------" + openDate + " " + openTime);
        return false;
    }
}