package com.sample.demo.common.util;


import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.FastDateFormat;
import org.joda.time.DateTime;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

/**
 * 날짜 관련 유틸 클래스입니다.
 * @author ikyuja79
 * @date 2018. 8. 1.
 */
public final class DateUtils {
//	private static Logger logger = LoggerFactory.getLogger(DateUtils.class);

	/*
	 * 추가로 필요한 데이트 포맷 패턴이 필요하시면 직접 추가해서 사용하시거나 ckpark에게 요청바랍니다.
	 * 패턴을 추가할 경우 dateFormatPatternMap 에도 추가해야 합니다.
	 * */

	/**
	 * yyyyMMddHHmmss
	 */
	public static final String YYYYMMDDHHmmss = "yyyyMMddHHmmss";

	public static final String YYYYMMDDHHmmssSSS = "yyyyMMddHHmmssSSS";

	/**
	 * yyyyMMddHHmm
	 */
	public static final String YYYYMMDDHHmm = "yyyyMMddHHmm";
	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static final String YYYYMMDDHHmmss_DASH = "yyyy-MM-dd HH:mm:ss";

	/**
	 * yyyy-MM-dd HH:mm:ss.SSS
	 */
	public static final String YYYYMMDDHHmmssSSS_DASH = "yyyy-MM-dd HH:mm:ss.SSS";

	/**
	 * yyyy.MM.dd HH:mm:ss
	 */
	public static final String YYYYMMDDHHmmss_DOT = "yyyy.MM.dd HH:mm:ss";

	/**
	 * yyyyMMdd
	 */
	public static final String YYYY = "yyyy";

	/**
	 * yyyyMMdd
	 */
	public static final String YYYYMMDD = "yyyyMMdd";
	/**
	 * HH
	 */
	public static final String HH = "HH";
	/**
	 * mm
	 */
	public static final String mm = "mm";


	public static final String HHmm = "HHmm";

	/**
	 * yyyy-MM-dd
	 */
	public static final String YYYYMMDD_DASH = "yyyy-MM-dd";
	/**
	 * yyyy.MM.dd
	 */
	public static final String YYYYMMDD_DOT = "yyyy.MM.dd";

	public static final String HHmmssSSS = "HHmmssSSS";

	private static Map<String, FastDateFormat> dateFormatPatternMap;

	private DateUtils(){
		//don't use!
	}

	static {
		dateFormatPatternMap = new HashMap<>();
		dateFormatPatternMap.put(YYYYMMDDHHmm		, FastDateFormat.getInstance(YYYYMMDDHHmm));
		dateFormatPatternMap.put(YYYYMMDDHHmmss		, FastDateFormat.getInstance(YYYYMMDDHHmmss));
		dateFormatPatternMap.put(YYYYMMDDHHmmssSSS		, FastDateFormat.getInstance(YYYYMMDDHHmmssSSS));
		dateFormatPatternMap.put(YYYYMMDDHHmmss_DASH, FastDateFormat.getInstance(YYYYMMDDHHmmss_DASH));
		dateFormatPatternMap.put(YYYYMMDDHHmmssSSS_DASH, FastDateFormat.getInstance(YYYYMMDDHHmmssSSS_DASH));
		dateFormatPatternMap.put(YYYYMMDDHHmmss_DOT	, FastDateFormat.getInstance(YYYYMMDDHHmmss_DOT));
		dateFormatPatternMap.put(YYYYMMDD			, FastDateFormat.getInstance(YYYYMMDD));
		dateFormatPatternMap.put(YYYYMMDD_DASH		, FastDateFormat.getInstance(YYYYMMDD_DASH));
		dateFormatPatternMap.put(YYYYMMDD_DOT		, FastDateFormat.getInstance(YYYYMMDD_DOT));
		dateFormatPatternMap.put(YYYY				, FastDateFormat.getInstance(YYYY));
		dateFormatPatternMap.put(HHmm				, FastDateFormat.getInstance(HHmm));
		dateFormatPatternMap.put(HH					, FastDateFormat.getInstance(HH));
		dateFormatPatternMap.put(mm					, FastDateFormat.getInstance(mm));

		dateFormatPatternMap.put(HHmmssSSS					, FastDateFormat.getInstance(HHmmssSSS));
	}


	/**
	 * System의 현재 Date 정보를 yyyy-MM-dd HH:mm String으로 Return 한다.
	 * @return
	 */
	public static String getStringNowDateTime() {
		return getToday("yyyy-MM-dd HH:mm");
	}

	/**
	 * System의 현재 Date 정보를 변환할 Format Parameter 형태를 받아 String으로 Return 한다.
	 * @return
	 */
	public static String getStringNowDateTime(String pattern) {
		return getToday(pattern);
	}

	/**
	 * 기존 데이트포맷 패턴에 없으면 추가하고 있으면 기존 데이트 포맷을 반환합니다.
	 *
	 * @param pattern
	 * @return
	 */
	private static FastDateFormat getDateFormat(String pattern){
		if(!dateFormatPatternMap.containsKey(pattern)){
			dateFormatPatternMap.put(pattern, FastDateFormat.getInstance(pattern));
//			logger.info("날짜 패턴 새로 생성, pattern = {}", pattern);
		}
		return dateFormatPatternMap.get(pattern);
	}

	public static class DateTypeBuilder{
		private Object basicDate;
		public DateTypeBuilder(Object basicDate){
			this.basicDate = basicDate;
		}
		public String string(){
			if(basicDate instanceof String){
				return (String)basicDate;
			}
			return null;
		}

		public void sqlDate(){

		}

		public void date(){

		}

		public void longNumber(){

		}
	}

	public static DateTypeBuilder changePattern(Date date, String pattern){
		return new DateTypeBuilder(getDateFormat(pattern).format(date));
	}


	/**
	 * Date타입 날짜를 지정한 포맷에 맞게 변경합니다.
	 *
	 * @param date    java.util.date or java.sql.date 사용.
	 * @param pattern 미리 정의한 String 패턴 or 개발자가 임의로 작성한 날짜 패턴.
	 * @return
	 * @exception 인자가 널일 경우 NullPointerException을 발생시키며, pattern값에 오류가 있을 경우 IllegalArgumentException이 발생합니다.
	 */
	public static String changeDateToString(Date date, String pattern){
		if(NullUtil.isNullAll(date, pattern)){
			throw new NullPointerException();
		}
		return getDateFormat(pattern).format(date);
	}


	/**
	 * 스트링 날짜 데이터를 Date객체로 변환합니다.
	 *
	 * @param strDate
	 * @param pattern
	 * @return 데이트 값이 pattern으로 파싱할 수 없을 경우 널값을 반환합니다.
	 */
	public static java.util.Date changeStringToDate(String strDate, String pattern){
		if(NullUtil.isNullAll(strDate, pattern)){
			throw new NullPointerException();
		}
		Date date = null;
		try {
			date = getDateFormat(pattern).parse(strDate);
		} catch (ParseException e) {
//			logger.warn("날짜 변환 중 오류가 발생하였습니다. 날짜데이터:{}, 패턴:{}", strDate, pattern);
			e.printStackTrace();
		}
		return date;
	}

	public static Date changeLongToDate(Long date) {
		if(NullUtil.isNull(date)){
			throw new NullPointerException();
		}
		return new Date(date);
	}

	/**
	 * 스트링 날짜 데이터를 java.sql.Date객체로 변환합니다.
	 *
	 * @param strDate
	 * @param pattern
	 * @return 데이트 값이 pattern으로 파싱할 수 없을 경우 널값을 반환합니다.
	 */
	public static java.sql.Date changeStringToSqlDate(String strDate, String pattern){
		if(NullUtil.isNullAll(strDate, pattern)){
			throw new NullPointerException();
		}
		Date date = null;
		try {
			date = getDateFormat(pattern).parse(strDate);
		} catch (ParseException e) {
//			logger.warn("날짜 변환 중 오류가 발생하였습니다. 날짜데이터:{}, 패턴:{}", strDate, pattern);
			e.printStackTrace();
		}
		return toSqlDate(date);
	}

	/**
	 * java.util.Date를 java.sql.Date로 변환합니다.
	 *
	 * @param date
	 * @return
	 */
	public static java.sql.Date toSqlDate(Date date){
		return new java.sql.Date(date.getTime());
	}

	/**
	 * 숫자 값으로 Date 객체를 생성합니다.
	 *
	 * @param year
	 * @param month
	 * @param day
	 * @param hour
	 * @param min
	 * @param second
	 * @return 입력 파라미터에 오류가 있을 경우 NULL값을 반환합니다.
	 */
	public static Date toDate(int year, int month, int day, int hour, int min, int second){
		if((month < 1 || month > 12)
		|| (day < 1 || day > 31)
		|| (hour < 0 || hour > 23)
		|| (min < 0 || min > 59)
		|| (second < 0 || second > 59)){
//			logger.error("날짜를 생성하기 위한 파라미터 데이터에 오류가 있습니다. year:{} month:{}, day:{}, hour:{}, min:{}, second:{}", year, month, day, hour, min, second);
			return null;
		}
		DateTime dateTime = new DateTime(year, month, day, hour, min, second);
		return dateTime.toDate();
	}

	/**
	 * 숫자 값으로 날짜를 더한 만큼의 값을 패턴에 맞게 리턴합니다.
	 *
	 * @param year
	 * @param month
	 * @param day
	 * @param hour
	 * @param min
	 * @param second
	 * @return 입력 파라미터에 오류가 있을 경우 NULL값을 반환합니다.
	 */
	public static String getPlusDays(int year, int month, int day, int hour, int min, int second, int plusDay, String pattern){
		if((month < 1 || month > 12)
		|| (day < 1 || day > 31)
		|| (hour < 0 || hour > 23)
		|| (min < 0 || min > 59)
		|| (second < 0 || second > 59)){
//			logger.error("날짜를 생성하기 위한 파라미터 데이터에 오류가 있습니다. year:{} month:{}, day:{}, hour:{}, min:{}, second:{}", year, month, day, hour, min, second);
			return null;
		}
		DateTime dateTime = new DateTime(year, month, day, hour, min, second);
		return dateTime.plusDays(plusDay).toString(pattern);
	}

	/**
	 * 오늘 날짜를 기준으로 year, month, day를 뺀 날짜를 구합니다.
	 *
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static Date minusDateFromToday(int year, int month, int day){
		DateTime lastAnyDate = DateTime.now().minusYears(year).minusMonths(month).minusDays(day);
		return lastAnyDate.toDate();
	}

	/**
	 * 오늘 날짜를 기준으로 year, month, day를 뺀 날짜를 구합니다.
	 *
	 * @param year
	 * @param month
	 * @param day
	 * @param pattern
	 * @return
	 */
	public static String minusDateFromToday(int year, int month, int day, String pattern){
		DateTime lastAnyDate = DateTime.now()
				.minusYears(year)
				.minusMonths(month)
				.minusDays(day);
		return changeDateToString(lastAnyDate.toDate(), pattern);
	}

	public static Date minusDateFromToday(int year, int month, int day, int hour, int min, int second){
		DateTime lastAnyDate = DateTime.now()
				.minusYears(year)
				.minusMonths(month)
				.minusDays(day)
				.minusHours(hour)
				.minusMinutes(min)
				.minusSeconds(second);
		return lastAnyDate.toDate();
	}

	public static String minusDateFromToday(int year, int month, int day, int hour, int min, int second, String pattern){
		DateTime lastAnyDate = DateTime.now()
				.minusYears(year)
				.minusMonths(month)
				.minusDays(day)
				.minusHours(hour)
				.minusMinutes(min)
				.minusSeconds(second);
		return changeDateToString(lastAnyDate.toDate(), pattern);
	}

	/**
	 * 오늘 날짜 기준으로 hours, minutes, seconds 를 뺀 날짜를 구한다.
	 * @param hours
	 * @param minutes
	 * @param seconds
	 * @param pattern
	 * @return
	 */
	public static String minusTimeFromToday(int hours, int minutes, int seconds, String pattern){
		DateTime lastAnyDate = DateTime.now().minusHours(hours).minusMinutes(minutes).minusSeconds(seconds);
		return changeDateToString(lastAnyDate.toDate(), pattern);
	}

	/**
	 * 스트링 날짜 데이터가 날짜형식이 맞는지 확인합니다.
	 *
	 * @param date 년월일 정보만 입력받습니다. 예) 20160617, 2016.06.17, 2016-06-17
	 * @return date값이 날짜형식이면 TRUE 아니면 FALSE
	 */
	public static boolean isDate(String date){
		String onlyNumberDate = date.replaceAll("[^0-9]", "");

		if(NullUtil.isNull(onlyNumberDate)) return false;

		if(onlyNumberDate.length() != 8) return false;

		int year = Integer.parseInt(onlyNumberDate.substring(0, 4));
		int month = Integer.parseInt(onlyNumberDate.substring(4, 6));
		int day = Integer.parseInt(onlyNumberDate.substring(6, 8));

		if(year < 1970 ) return false;
		if(month < 1 || month > 12) return false;
		if(day < 1 || day > 31) return false;
		if(month == 2 && day > 29) return false;

		return true;
	}

	/**
	 * 날짜가 fromDate부터 toDate사이에 있는지 확인합니다.
	 *
	 * @param date
	 * @param fromDate inclusive관계입니다.
	 * @param toDate   inclusive관계입니다.
	 * @return
	 */
	public static boolean isWithinDate(Date date, Date fromDate, Date toDate){
		return date.after(fromDate) && date.before(toDate);
	}

	/**
	 * 오늘 날짜를 가져옵니다.
	 *
	 * @return yyyyMMdd 포맷.
	 */
	public static String getToday(){
		return changeDateToString(new Date(), YYYYMMDD);
	}

//	public static Date getToday(){
//		return DateTime.now().toDate();
//	}

	/**
	 * 오늘 날짜를 가져옵니다.
	 *
	 * @param format
	 * @return
	 */
	public static String getToday(String pattern){
		return changeDateToString(new Date(), pattern);
	}

	public static java.util.Date today(String pattern){
		try {
			return getDateFormat(pattern).parse(changeDateToString(new Date(), pattern));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}


	/**
	 * [미구현]
	 *
	 * 예) 입력: (20160606, "yyyy.MM.dd")
	 * 결과: (2016.06.06)
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String changeDateFormat(String date, String oldPattern, String newPattern){
		if(NullUtil.isNullAll(date, oldPattern, newPattern)){
			throw new NullPointerException();
		}
		return getDateFormat(newPattern).format(changeStringToDate(date, oldPattern));
	}

	/**
	 * 날짜 사이의 차이를 계산합니다. day단위로만 계산됩니다.
	 *
	 * @param newerDate
	 * @param olderDate
	 * @return
	 */
	public static int differenceInDays(Date newerDate, Date olderDate) {
		return (int) ((newerDate.getTime() - olderDate.getTime()) / (1000 * 60 * 60 * 24));
	}

	/**
	 * [미구현]
	 *
	 * @param year
	 * @param month
	 * @return
	 */
	public static int getDaysOfMonth(String year, String month) {
		int days = 0;
		if (month.equals("1") || month.equals("3") || month.equals("5") || month.equals("7") || month.equals("8")
				|| month.equals("10") || month.equals("12")) {
			days = 31;
		} else if (month.equals("4") || month.equals("6") || month.equals("9") || month.equals("11")) {
			days = 30;
		} else {
			if ((Integer.parseInt(year) % 4 == 0 && Integer.parseInt(year) % 100 != 0)
					|| Integer.parseInt(year) % 400 == 0) {
				days = 29;
			} else {
				days = 28;
			}
		}

		return days;
	}

	/**
	 * UI/DB 에서 날짜 비교시 시분초 데이터까지 포함하여 비교하기 위해 데이터를 변환시킵니다.
	 *
	 * 예)
	 * 입력값: 2016-06-15, 2016-07-30
	 * 출력값: 20160615000000, 20160730235959
	 *
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static String [] addHHmmddssForCompare(String startDate, String endDate){
		if(startDate == null || endDate == null){
			throw new NullPointerException("입력 파라미터의 날짜 데이터에 오류가 있습니다.");
		}

		String pureStartDate = startDate.replaceAll("[^0-9]", "");
		String pureEndDate = endDate.replaceAll("[^0-9]", "");

		if(pureStartDate.length() != 8 || pureEndDate.length() != 8){
			throw new IllegalArgumentException("입력 파라미터의 날짜 데이터에 오류가 있습니다.");
		}

		String [] resultDate = new String [] {pureStartDate + "0000", pureEndDate + "235959"};
		return resultDate;
	}

	/**
	 * 두 문자열 시간의 차이를 계산하여 반환. 계산을 원하는 date 패턴  + 반환 값의 TimeUnit 값을 파라매터로 전달 -- 밀리 세컨드 단위까지만 지원. 밀리 세컨드 이상은 null 리턴
	 * null이 반환될 수 있음.
	 *
	 * <pre>
	 *  String endTime  =  "201607041422";
	* 	String startTime = "201607041410";
	* 	String pattern = "yyyyMMddHHmm";
	* 	long diffTime = DateUtils.stringDateDiff(endTime, startTime, pattern,TimeUnit.MINUTES);
	 * </pre>
	 * @param endDateString
	 * @param startDateString
	 * @param pattern
	 * @param timeUnit
	 * @return
	 */
	public static Long stringDateDiff(String endDateString, String startDateString, String pattern, TimeUnit timeUnit)
	{
		Date endDate = changeStringToDate(endDateString, pattern);
		Date startDate = changeStringToDate(startDateString, pattern);

		if( endDate == null || startDate == null ) return null;

		long diff = endDate.getTime() - startDate.getTime();

		switch(timeUnit){

			case DAYS :
				return diff / (1000 * 60 * 60 * 24);
			case SECONDS :
				return diff / (1000);
			case MINUTES :
				return diff / (1000 * 60);
			case HOURS :
				return diff / (1000 * 60 * 60);
			case MILLISECONDS :
				return diff;
			default :
				return null;
		}

	}
	/**
	 * 오늘 날짜를 기준으로  파라매터 날짜와 시간 차 비교 , 계산을 원하는 date 패턴  + 반환 값의 TimeUnit 값을 파라매터로 전달 -- 밀리 세컨드 단위까지만 지원. 밀리 세컨드 이상은 null 리턴
	 * null이 반환될 수 있음.
	 * @param startDateString
	 * @param pattern
	 * @param timeUnit
	 * @return
	 */
	public static Long stringDateDiff(String startDateString, String pattern, TimeUnit timeUnit)
	{
		Date today = new Date();
		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat(pattern);
		String todayDateString = dateFormat.format(today);
		return stringDateDiff(todayDateString, startDateString, pattern , timeUnit);
	}

	/**
	 * 오늘날짜
	 * @return
	 */
	public static String getDate(){
		Calendar now = Calendar.getInstance();
		String y="",m="",d="",hh="",mm="",ss="",ms="";
		int year = now.get(Calendar.YEAR);
		int month = (now.get(Calendar.MONTH))+1;
		int day = now.get(Calendar.DAY_OF_MONTH);
		int hour=now.get(Calendar.HOUR_OF_DAY);
		int min=now.get(Calendar.MINUTE);
		int sec=now.get(Calendar.SECOND);
		long msec = now.get(Calendar.MILLISECOND);

		y=year+"";
		if(month<10){m="0"+month;}else if(month>=10){m=""+month;}
		if(day<10){d="0"+day;}else if(day>=10){d=""+day;}
		if(hour<10){hh="0"+hour;}else if(hour>=10){hh=""+hour;}
		if(min==0){mm="00";}else if(min<10){mm="0"+min;}else if(min>=10){mm=""+min;}
		if(sec==0){ss="00";}else if(sec<10){ss="0"+sec;}else if(sec>=10){ss=""+sec;}
		if(msec==0){ms="000";}else if(msec<100 && msec>=10){ms="0"+msec;}else if(msec<=10){ms="00"+msec;}else if(msec>=100){ms=""+msec;}

		return y+"-"+m+"-"+d+" "+hh+":"+mm+":"+ss+"."+ms;
	}

	public static String getCalendarPattern(Calendar calendar, String pattern) {
		return getDateFormat(pattern).format(calendar.getTime());
	}

	/**
	 * java.time.LocalDate -> java.util.Date
	 *
	 * @param localDate
	 * @return
	 */
	public static Date asDate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * java.time.LocalDateTime -> java.util.Date
	 *
	 * @param localDateTime
	 * @return
	 */
	public static Date asDate(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * java.util.Date -> java.time.LocalDate
	 *
	 * @param date
	 * @return
	 */
	public static LocalDate asLocalDate(Date date) {
		return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
	}

	/**
	 * java.util.Date -> java.time.LocalDateTime
	 *
	 * @param date
	 * @return
	 */
	public static LocalDateTime asLocalDateTime(Date date) {
		return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	/**
	 * java.time.format.DateTimeFormatter를 구해온다.
	 *
	 * @param pattern
	 * @return
	 * @throws Exception
	 */
	public static DateTimeFormatter getDateTimeFormatter(final String pattern) throws Exception {
		if(NullUtil.isNull(pattern))
			throw new NullPointerException();

		return DateTimeFormatter.ofPattern(pattern);
	}
}
