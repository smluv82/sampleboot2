package com.sample.demo.common.util;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 널 체크를 도와주는 유틸 클래스입니다.
 * @author ikyuja79
 * @date 2018. 8. 1.
 */
public final class NullUtil {

	private NullUtil() {
		// don't use!
	}

	/**
	 * 입력값이 널인지 확인합니다. 입력받은 값 중 하나라도 널이면 TRUE를 반환합니다.
	 *
	 * @param values
	 * @return
	 */
	public static boolean isNullAll(Object ... values){
		if(values == null){
			return true;
		}

		for(Object obj : values){
			if(isNull(obj)){
				return true;
			}
		}

		return false;
	}

	/**
	 * 입력값이 널인지 확인합니다.
	 *
	 * @param value
	 * @return 널이면 TRUE, 아니면 FALSE
	 */
	public static boolean isNull(Object value) {
		if (value == null) {
			return true;
		}
		if (value instanceof Integer && 0 == (Integer) value) {
			return true;
		}
		if (value instanceof Long && 0 == (Long) value) {
			return true;
		}
		if(value instanceof Double && 0 == (Double) value){
			return true;
		}
		if(value instanceof Float && 0 == (Float) value){
			return true;
		}
		if(value instanceof BigDecimal && BigDecimal.ZERO == (BigDecimal) value){
			return true;
		}
		if(value instanceof Map){
			return ((Map<?,?>)value).isEmpty();
		}
		if(value instanceof List){
			return ((List<?>)value).isEmpty();
		}
		if(value instanceof Object[]){
			return ((Object[])value).length == 0;
		}
		if(value.getClass().isArray()){
			return Array.getLength(value) == 0;
		}
		if(value instanceof String){
			String s = ((String)value).trim();
			if(s.length() == 0){
				return true;
			}
		}
		return false;
	}

	public static boolean isNullMberSeq(Object value) {
		if (value == null) {
			return true;
		}
		if(value instanceof Map){
			return ((Map<?,?>)value).isEmpty();
		}
		if(value instanceof List){
			return ((List<?>)value).isEmpty();
		}
		if(value instanceof Object[]){
			return ((Object[])value).length == 0;
		}
		if(value.getClass().isArray()){
			return Array.getLength(value) == 0;
		}
		if(value instanceof String){
			String s = ((String)value).trim();
			if(s.length() == 0){
				return true;
			}
		}
		return false;
	}

	public static boolean isNullByInt(Object value) {
		if (value == null) {
			return true;
		}
		if(value instanceof Map){
			return ((Map<?,?>)value).isEmpty();
		}
		if(value instanceof List){
			return ((List<?>)value).isEmpty();
		}
		if(value instanceof Object[]){
			return ((Object[])value).length == 0;
		}
		if(value.getClass().isArray()){
			return Array.getLength(value) == 0;
		}
		if(value instanceof String){
			String s = ((String)value).trim();
			if(s.length() == 0){
				return true;
			}
		}
		return false;
	}


	/**
	 * 입력값이 널인지 확인합니다.
	 * 값이 널일 경우 replacement값으로 대체합니다.
	 *
	 * @param value
	 * @param replacement
	 * @return
	 */
	public static <T> T replaceIfNull(T value, T replacement) {
		return isNull(value) ? replacement : value;
	}

	/**
	 * 입력값이 널일 경우 errorCode에 해당하는 익셉션을 발생시킵니다.
	 * HttpStatus 값을 생략할 경우 HttpStatus.BAD_REQUEST가 기본 설정됩니다.
	 * @param value
	 * @param errorCode
	 * @return
	 * @throws Exception
	 */
	/*
	public static boolean throwExceptionIfNull(Object value, SsocioError errorCode) throws Exception{
		if(isNull(value)) throw new Exception(errorCode, HttpStatus.BAD_REQUEST);
		return false;
	}
	public static boolean throwExceptionIfNullMberSeq(Object value, SsocioError errorCode) throws Exception{
		if(isNullMberSeq(value)) throw new Exception(errorCode, HttpStatus.BAD_REQUEST);
		return false;
	}
	public static boolean throwExceptionIfNullByInt(Object value, SsocioError errorCode) throws Exception{
		if(isNullByInt(value)) throw new Exception(errorCode, HttpStatus.BAD_REQUEST);
		return false;
	}
	*/
}