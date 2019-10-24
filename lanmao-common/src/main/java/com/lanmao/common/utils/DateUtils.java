package com.lanmao.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtils {


    private static final ThreadLocal<Map<String, SimpleDateFormat>> map;

    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    public static final String MM_DD = "MM-dd";
    public static final String MM_SS_MM_DD = "mm:ss dd/MM";
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String HH_MM_SS = "HH:mm:ss";
    public static final String HH_MM = "HH:mm";

    static {
        map = ThreadLocal.withInitial(() -> new HashMap<>());
    }

    public static Date parse(String date, String format) {

        SimpleDateFormat sdf = getSdf(format);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String format(Date date, String format) {

        SimpleDateFormat sdf = getSdf(format);
        return sdf.format(date);
    }

    public static String getWeek(Date date){
        String[] weeks = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if(week_index<0){
            week_index = 0;
        }
        return weeks[week_index];
    }

    private static SimpleDateFormat getSdf(String format) {
        SimpleDateFormat sdf = map.get().get(format);
        if (sdf == null) {
            sdf = new SimpleDateFormat(format);
            map.get().put(format, sdf);
        }
        return sdf;
    }

    /**
     * 比较两个日期
     * @param date1
     * @param date2
     * @return 等于返回0、date1 > date2 返回1、date1 < date2 返回-1
     */
    public static Integer compareTo(Date date1, Date date2){
        if (date1.getTime() == date2.getTime())
            return 0;
        return date1.getTime() > date2.getTime() ? 1 : -1;
    }

    /**
     * 获取指定日期所在月份开始的时间戳
     *
     * @param date 指定日期
     * @return
     */
    public static Date getMonthBegin(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        //设置为1号,当前日期既为本月第一天
        c.set(Calendar.DAY_OF_MONTH, 1);
        //将小时至0
        c.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0
        c.set(Calendar.MINUTE, 0);
        //将秒至0
        c.set(Calendar.SECOND, 0);
        //将毫秒至0
        c.set(Calendar.MILLISECOND, 0);
        // 获取本月第一天的时间戳
        return c.getTime();

    }

    /**
     * 获取上个月的开始时间
     * @return
     */
    public static Date getBeforeMonthStart(){
        Calendar c=Calendar.getInstance();
        c.add(Calendar.MONTH, -1);
        int lastMonthMinDay = c.getActualMinimum(Calendar.DAY_OF_MONTH);
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), lastMonthMinDay, 0, 0, 0);

        return c.getTime();
    }

    /**
     * 获取上个月的结束时间
     * @return
     */
    public static Date getBeforeMonthEnd(){
        Calendar c=Calendar.getInstance();
        c.add(Calendar.MONTH, -1);
        int lastMonthMaxDay=c.getActualMaximum(Calendar.DAY_OF_MONTH);
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), lastMonthMaxDay, 23, 59, 59);
        return c.getTime();
    }


    /**
     * 获取指定日期所在月份结束的时间戳
     * @param date 指定日期
     * @return
     */
    public static Date getMonthEnd(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        //设置为当月最后一天
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        //将小时至23
        c.set(Calendar.HOUR_OF_DAY, 23);
        //将分钟至59
        c.set(Calendar.MINUTE, 59);
        //将秒至59
        c.set(Calendar.SECOND, 59);
        //将毫秒至999
        c.set(Calendar.MILLISECOND, 999);
        // 获取本月最后一天的时间戳
        return c.getTime();
    }


    /**
     *
     * 获取零点
     * @param date
     * @return
     */
    public static Date getDateZero(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date zero = calendar.getTime();
        return zero;
    }

    /**
     * 获取前一天的零点
     * @param date
     * @return
     */
    public static Date getBeforeZero(Date date){
        long timeStamp = date.getTime() - 24*60*60*1000;
        date.setTime(timeStamp);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date zero = calendar.getTime();
        return zero;
    }

    public static Date getStartTime(Date date,String time){
        SimpleDateFormat df = new SimpleDateFormat(YYYY_MM_DD);//设置日期格式
        return DateUtils.parse(df.format(date) + " " + time,YYYY_MM_DD_HH_MM_SS);
    }

    public static Date getEndTime(String time, int intx){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD);
        calendar.add(Calendar.DAY_OF_YEAR, intx);
        Date date = calendar.getTime();
        return DateUtils.parse(sdf.format(date) + " " + time,YYYY_MM_DD_HH_MM_SS);
    }

    public static String getMmSsMmDd(Date date) {
        SimpleDateFormat df = new SimpleDateFormat(MM_SS_MM_DD);//设置日期格式
        return df.format(date);
    }


    /**
     *
     * 加天
     * @param date
     * @param days
     * @return
     */
    public static Date addDays(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        Date zero = calendar.getTime();
        return zero;
    }


    public static Date addMinutes(Date date, int minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minutes);
        Date zero = calendar.getTime();
        return zero;
    }

    /**
     * 获取月份的所有天数的日期
     * @param int_x
     * @return
     */
    public static List<String> getDayListOfMonth(int int_x) {
        List<String> list = new ArrayList();
        Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
        int year = aCalendar.get(Calendar.YEAR);//年份
        int month = aCalendar.get(Calendar.MONTH) + int_x;//月份
        int day = aCalendar.getActualMaximum(Calendar.DATE);
        SimpleDateFormat sd = new SimpleDateFormat(YYYY_MM_DD);
        String sd_date = sd.format(new Date());
        for (int i = 1; i <= day; i++) {
            String aDate = String.valueOf(year)+"-"+ (month >= 10?month:("0"+month)) + "-"+(i >= 10?i:("0"+i));
            if (aDate.compareTo(sd_date) == 0){
                break;
            }
            list.add(aDate);
        }
        return list;
    }

    /**
     * 包含本月到之前的一年时间所有的月份
     * @return
     */
    public static List<String> getInitMonthMapWithZero(){
        List<String> list = new ArrayList<String>();
        Calendar c = Calendar.getInstance();
        for(int i = 0; i < 12; i ++){
            int k = c.get(Calendar.YEAR);
            int j = c.get(Calendar.MONTH) + 1 - i;
            String date = "";
            if(j >= 1){
                date = k + "-" + (j >= 10 ? "" : "0") + j;
//                date = (j >= 10 ? "" : "0") + j;
            } else {
                int p = 11 - i;//剩余循环次数
                int m = c.get(Calendar.YEAR) - 1;
                int n = c.get(Calendar.MONTH) + 2 + p;
                date = m + "-" + (n >= 10 ? "" : "0") + n;
//                date = (n >= 10 ? "" : "0") + n;
            }
            list.add(date);
        }
        return list;
    }

    public static String getLastDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month-1);
        cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(Calendar.DATE));
        return  new   SimpleDateFormat( "yyyy-MM-dd ").format(cal.getTime());
    }

    public static String getFirstDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month-1);
        cal.set(Calendar.DAY_OF_MONTH,cal.getMinimum(Calendar.DATE));
        return   new   SimpleDateFormat( "yyyy-MM-dd ").format(cal.getTime());
    }

    /**
     * 根据当前日期获得所在周的日期区间（周一和周日日期）
     * @param date
     * @return
     */
    public static Map<String,Date> getTimeInterval(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // System.out.println("要计算日期为:" + sdf.format(cal.getTime())); // 输出要计算日期
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        String imptimeBegin = sdf.format(cal.getTime());
        // System.out.println("所在周星期一的日期：" + imptimeBegin);
        cal.add(Calendar.DATE, 6);
        String imptimeEnd = sdf.format(cal.getTime());
        // System.out.println("所在周星期日的日期：" + imptimeEnd);

        Map<String,Date> re_map = new HashMap<>();
        re_map.put("work_start_time",parse(imptimeBegin,YYYY_MM_DD_HH_MM_SS));
        re_map.put("work_end_time",parse(imptimeEnd,YYYY_MM_DD_HH_MM_SS));

        return re_map;
    }

    /**
     * 根据当前日期获得上周的日期区间（上周周一和周日日期）
     * @return
     */
    public static Map<String,Date> getLastTimeInterval(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date);
        int dayOfWeek = calendar1.get(Calendar.DAY_OF_WEEK) - 1;
        int offset1 = 1 - dayOfWeek;
        int offset2 = 7 - dayOfWeek;
        calendar1.add(Calendar.DATE, offset1 - 7);
        calendar2.add(Calendar.DATE, offset2 - 7);
        // System.out.println(sdf.format(calendar1.getTime()));// last Monday
        String lastBeginDate = sdf.format(calendar1.getTime());
        // System.out.println(sdf.format(calendar2.getTime()));// last Sunday
        String lastEndDate = sdf.format(calendar2.getTime());

        Map<String,Date> re_map = new HashMap<>();
        re_map.put("work_start_time",parse(lastBeginDate,YYYY_MM_DD_HH_MM_SS));
        re_map.put("work_end_time",parse(lastEndDate,YYYY_MM_DD_HH_MM_SS));
        return re_map;
    }

    public static String dateDifferent(String startTime, String endTime){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date d1 = null;
        Date d2 = null;
        long diffSeconds = new Long(0);
        long diffMinutes = new Long(0);
        long diffHours = new Long(0);
        long diffDays = new Long(0);
        try {
            d1 = format.parse(startTime);
            d2 = format.parse(endTime);

            //毫秒ms
            long diff = d2.getTime() - d1.getTime();

            diffSeconds = diff / 1000 % 60;
            diffMinutes = diff / (60 * 1000) % 60;
            diffHours = diff / (60 * 60 * 1000) % 24;
            diffDays = diff / (24 * 60 * 60 * 1000);

            System.out.print("两个时间相差：");
            System.out.print(diffDays + " 天, ");
            System.out.print(diffHours + " 小时, ");
            System.out.print(diffMinutes + " 分钟, ");
            System.out.print(diffSeconds + " 秒.");

        } catch (Exception e) {
            e.printStackTrace();
        }



        String hhmmss = (diffHours<10?("0"+diffHours):diffHours)+":"+(diffMinutes < 10?("0"+diffMinutes):diffMinutes)+":"+(diffSeconds < 10?("0"+diffSeconds):diffSeconds);
    if (diffDays > 0){
        hhmmss = diffDays+"天 "+hhmmss;
    }
        return hhmmss;
    }

    /**
     * 按照分钟获取时分秒格式
     * @param minutes
     * @return
     */
    public static  String remainTimeStr(int minutes){
        int hour = minutes/60;
        int minute= minutes%60;
        int seconds= minutes*60%60;
        String hhmmss = (hour<10?("0"+hour):hour)+":"+(minute < 10?("0"+minute):minute)+":"+(seconds < 10?("0"+seconds):seconds);
        return hhmmss;
    }

    /**
     * 拼接时间段信息
     * @param timeInt
     * @return
     */
    public static Map<String,Date> getTimeMap(int timeInt, Date paramStartTime, Date paramEndTime){

        Map<String,Date> times = new HashMap<>();
        switch(timeInt){
            case 1:
                Date startTime = DateUtils.getStartTime(new Date(),"10:00:00");
                times.put("startTime",startTime);
                times.put("endTime",DateUtils.addDays(startTime,1));
                break;
            case 2:
                Date endTime = DateUtils.getStartTime(new Date(),"10:00:00");
                times.put("startTime",DateUtils.addDays(endTime,-1));
                times.put("endTime",endTime);
                break;
            case 3:
                Date dateTime = DateUtils.getStartTime(new Date(), "10:00:00");
                Map<String,Date>  map = DateUtils.getTimeInterval(dateTime);
                times.put("startTime",map.get("work_start_time"));
                times.put("endTime",dateTime);
                break;
            case 4:
                Map<String,Date>  s_map = DateUtils.getLastTimeInterval(DateUtils.getStartTime(new Date(), "10:00:00"));
                times.put("startTime",s_map.get("work_start_time"));
                times.put("endTime",s_map.get("work_end_time"));
                break;
            case 5:
                int year = Integer.parseInt(DateUtils.format(new Date(),"YYYY"));
                int month = Integer.parseInt(DateUtils.format(new Date(),"MM"));
                int mm = month;
                if (month == 12){
                    year = year + 1;
                    mm = 1;
                }
                times.put("startTime",DateUtils.parse(DateUtils.getFirstDayOfMonth(year,month)+"10:00:00", DateUtils.YYYY_MM_DD_HH_MM_SS));
                times.put("endTime",DateUtils.parse(DateUtils.getFirstDayOfMonth(year, mm) + "10:00:00", DateUtils.YYYY_MM_DD_HH_MM_SS));
                break;
            case 6:
                int year_s = Integer.parseInt(DateUtils.format(new Date(), "YYYY"));
                int month_s = Integer.parseInt(DateUtils.format(new Date(),"MM"));
                if (month_s == 1){
                    year_s = year_s - 1;
                    month_s = 12;
                }
                times.put("startTime",DateUtils.parse(DateUtils.getFirstDayOfMonth(year_s,month_s-1)+"10:00:00", DateUtils.YYYY_MM_DD_HH_MM_SS));
                times.put("endTime",DateUtils.parse(DateUtils.getFirstDayOfMonth(year_s, month_s) + "10:00:00", DateUtils.YYYY_MM_DD_HH_MM_SS));
                break;
            case 7:
                times.put("startTime",DateUtils.getStartTime(paramStartTime, "10:00:00"));
                times.put("endTime",DateUtils.getStartTime(paramEndTime, "10:00:00"));
                break;
            default:
                Date defaultTime = DateUtils.getStartTime(new Date(),"10:00:00");
                times.put("startTime",defaultTime);
                times.put("endTime",DateUtils.addDays(defaultTime,1));
                break;
        }

        return times;
    }


    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.addAll(DateUtils.getDayListOfMonth(0));
////        Collections.reverse(list_1);
        list.addAll(DateUtils.getDayListOfMonth(1));


        Collections.reverse(list);
        for (String ss : list){
            System.out.println("date:"+ss);
        }

        List<String> list_y = getInitMonthMapWithZero();
        for (String sy : list_y){
            System.out.println("year:"+sy);
            String[] listint = sy.split("-");
            Integer y_int = Integer.parseInt(listint[0]);
            Integer m_int = Integer.parseInt(listint[1]);
            System.out.println("getLastDayOfMonth:"+getLastDayOfMonth(y_int,m_int));
            System.out.println("getFirstDayOfMonth:"+getFirstDayOfMonth(y_int,m_int));
        }

        Date beforeMonthStart = getBeforeMonthStart();
        if (beforeMonthStart != null){
            System.out.println(beforeMonthStart);
        }

        Date startTime = DateUtils.getStartTime(new Date(),"10:00:00");
        System.out.println("getStartTime:"+startTime);
        System.out.println("addDays1:"+DateUtils.addDays(startTime, 1));
        System.out.println("addDays1:"+DateUtils.addDays(startTime, -1));
        System.out.println("addDays getLastTimeInterval:"+ getLastTimeInterval(DateUtils.addDays(DateUtils.getStartTime(new Date(),"10:00:00"), -8)));
        System.out.println("addDays getTimeInterval:"+ getTimeInterval(DateUtils.addDays(DateUtils.getStartTime(new Date(), "10:00:00"), -8)));

        System.out.println("addDays1:"+DateUtils.remainTimeStr(35));
        System.out.println("dateDifferent:"+DateUtils.dateDifferent("2013-02-19 11:29:58","2013-02-20 11:31:48"));
    }
}
