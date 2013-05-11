package com.zuyue.util;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

/**
 *<p>Title: </p>
 *
 *<p>Description: </p>
 *
 *<p>Copyright: Copyright (c) 2010</p>
 *
 *<p>Company:昆明逐月科技有限公司</p>
 *
 *<p>Author shizx</p>
 *
 *<p>Email:shizhuxiong@gmail.com </p>
 *
 *<p>Version :1.0</p>
 *
 *<p>date:2013-5-11 下午10:04:34</p>
 */
public class GetterUtil {

	public static String[] BOOLEANS = { "true", "t", "y", "on", "1" };
	public static final boolean DEFAULT_BOOLEAN = false;
	public static final boolean[] DEFAULT_BOOLEAN_VALUES = new boolean[0];
	public static final byte DEFAULT_BYTE = 0;
	public static final byte[] DEFAULT_BYTE_VALUES = new byte[0];

	public static final Date[] DEFAULT_DATE_VALUES = new Date[0];
	public static final double DEFAULT_DOUBLE = 0.0D;
	public static final double[] DEFAULT_DOUBLE_VALUES = new double[0];
	public static final float DEFAULT_FLOAT = 0.0F;
	public static final float[] DEFAULT_FLOAT_VALUES = new float[0];
	public static final int DEFAULT_INTEGER = 0;
	public static final int[] DEFAULT_INTEGER_VALUES = new int[0];
	public static final long DEFAULT_LONG = 0L;
	public static final long[] DEFAULT_LONG_VALUES = new long[0];
	public static final short DEFAULT_SHORT = 0;
	public static final short[] DEFAULT_SHORT_VALUES = new short[0];
	public static final String DEFAULT_STRING = "";

	public static boolean get(Serializable value, boolean defaultValue) {
		if (value == null) {
			return defaultValue;
		}

		if ((value instanceof String)) {
			return get((String) value, defaultValue);
		}
		if (value.getClass().isAssignableFrom(Boolean.class)) {
			return ((Boolean) value).booleanValue();
		}

		return defaultValue;
	}

	public static Date get(Serializable value, DateFormat dateFormat,
			Date defaultValue) {
		if (value == null) {
			return defaultValue;
		}

		if ((value instanceof String)) {
			return get((String) value, dateFormat, defaultValue);
		}
		if (value.getClass().isAssignableFrom(Date.class)) {
			return (Date) value;
		}

		return defaultValue;
	}

	public static double get(Serializable value, double defaultValue) {
		if (value == null) {
			return defaultValue;
		}

		if ((value instanceof String)) {
			return get((String) value, defaultValue);
		}
		if (value.getClass().isAssignableFrom(Double.class)) {
			return ((Double) value).doubleValue();
		}

		return defaultValue;
	}

	public static float get(Serializable value, float defaultValue) {
		if (value == null) {
			return defaultValue;
		}

		if ((value instanceof String)) {
			return get((String) value, defaultValue);
		}
		if (value.getClass().isAssignableFrom(Float.class)) {
			return ((Float) value).floatValue();
		}

		return defaultValue;
	}

	public static int get(Serializable value, int defaultValue) {
		if (value == null) {
			return defaultValue;
		}

		if ((value instanceof String)) {
			return get((String) value, defaultValue);
		}
		if (value.getClass().isAssignableFrom(Integer.class)) {
			return ((Integer) value).intValue();
		}

		return defaultValue;
	}

	public static long get(Serializable value, long defaultValue) {
		if (value == null) {
			return defaultValue;
		}

		if ((value instanceof String)) {
			return get((String) value, defaultValue);
		}
		if (value.getClass().isAssignableFrom(Long.class)) {
			return ((Long) value).longValue();
		}

		return defaultValue;
	}

	public static short get(Serializable value, short defaultValue) {
		if (value == null) {
			return defaultValue;
		}

		if ((value instanceof String)) {
			return get((String) value, defaultValue);
		}
		if (value.getClass().isAssignableFrom(Short.class)) {
			return ((Short) value).shortValue();
		}

		return defaultValue;
	}

	public static String get(Serializable value, String defaultValue) {
		if (value == null) {
			return defaultValue;
		}

		if ((value instanceof String)) {
			return get((String) value, defaultValue);
		}

		return defaultValue;
	}

	public static boolean get(String value, boolean defaultValue) {
		if (value == null) {
			return defaultValue;
		}
		try {
			value = value.trim();

			return (value.equalsIgnoreCase(BOOLEANS[0]))
					|| (value.equalsIgnoreCase(BOOLEANS[1]))
					|| (value.equalsIgnoreCase(BOOLEANS[2]))
					|| (value.equalsIgnoreCase(BOOLEANS[3]))
					|| (value.equalsIgnoreCase(BOOLEANS[4]));
		} catch (Exception localException) {
		}

		return defaultValue;
	}

	public static Date get(String value, DateFormat dateFormat,
			Date defaultValue) {
		if (value == null) {
			return defaultValue;
		}
		try {
			Date date = dateFormat.parse(value.trim());

			if (date != null) {
				return date;
			}
		} catch (Exception localException) {
		}
		return defaultValue;
	}

	public static double get(String value, double defaultValue) {
		if (value != null) {
			try {
				return Double.parseDouble(_trim(value));
			} catch (Exception localException) {
			}
		}
		return defaultValue;
	}

	public static float get(String value, float defaultValue) {
		if (value == null) {
			return defaultValue;
		}
		try {
			return Float.parseFloat(_trim(value));
		} catch (Exception localException) {
		}
		return defaultValue;
	}

	public static int get(String value, int defaultValue) {
		if (value == null) {
			return defaultValue;
		}

		return _parseInt(_trim(value), defaultValue);
	}

	public static long get(String value, long defaultValue) {
		if (value == null) {
			return defaultValue;
		}

		return _parseLong(_trim(value), defaultValue);
	}

	public static short get(String value, short defaultValue) {
		if (value == null) {
			return defaultValue;
		}

		return _parseShort(_trim(value), defaultValue);
	}

	public static String get(String value, String defaultValue) {
		if (value == null) {
			return defaultValue;
		}

		return StringUtil.replace(value.trim(), "\r\n", "\n");
	}

	public static boolean getBoolean(Serializable value) {
		return getBoolean(value, false);
	}

	public static boolean getBoolean(Serializable value, boolean defaultValue) {
		return get(value, defaultValue);
	}

	public static boolean getBoolean(String value) {
		return getBoolean(value, false);
	}

	public static boolean getBoolean(String value, boolean defaultValue) {
		return get(value, defaultValue);
	}

	public static boolean[] getBooleanValues(Serializable value) {
		return getBooleanValues(value, DEFAULT_BOOLEAN_VALUES);
	}

	public static boolean[] getBooleanValues(Serializable value,
			boolean[] defaultValue) {
		Class classObject = value.getClass();

		if (classObject.isArray()) {
			Class componentType = classObject.getComponentType();

			if (componentType.isAssignableFrom(String.class)) {
				return getBooleanValues((String[]) value, defaultValue);
			}
			if (componentType.isAssignableFrom(Boolean.class)) {
				return (boolean[]) value;
			}
		}

		return defaultValue;
	}

	public static boolean[] getBooleanValues(String[] values) {
		return getBooleanValues(values, DEFAULT_BOOLEAN_VALUES);
	}

	public static boolean[] getBooleanValues(String[] values,
			boolean[] defaultValue) {
		if (values == null) {
			return defaultValue;
		}

		boolean[] booleanValues = new boolean[values.length];

		for (int i = 0; i < values.length; i++) {
			booleanValues[i] = getBoolean(values[i]);
		}

		return booleanValues;
	}

	public static Date getDate(Serializable value, DateFormat dateFormat) {
		return getDate(value, dateFormat, new Date());
	}

	public static Date getDate(Serializable value, DateFormat dateFormat,
			Date defaultValue) {
		return get(value, dateFormat, defaultValue);
	}

	public static Date getDate(String value, DateFormat dateFormat) {
		return getDate(value, dateFormat, new Date());
	}

	public static Date getDate(String value, DateFormat dateFormat,
			Date defaultValue) {
		return get(value, dateFormat, defaultValue);
	}

	public static Date[] getDateValues(Serializable value, DateFormat dateFormat) {
		return getDateValues(value, dateFormat, DEFAULT_DATE_VALUES);
	}

	public static Date[] getDateValues(Serializable value,
			DateFormat dateFormat, Date[] defaultValue) {
		Class classObject = value.getClass();

		if (classObject.isArray()) {
			Class componentType = classObject.getComponentType();

			if (componentType.isAssignableFrom(String.class)) {
				return getDateValues((String[]) value, dateFormat, defaultValue);
			}
			if (componentType.isAssignableFrom(Date.class)) {
				return (Date[]) value;
			}
		}

		return defaultValue;
	}

	public static Date[] getDateValues(String[] values, DateFormat dateFormat) {
		return getDateValues(values, dateFormat, DEFAULT_DATE_VALUES);
	}

	public static Date[] getDateValues(String[] values, DateFormat dateFormat,
			Date[] defaultValue) {
		if (values == null) {
			return defaultValue;
		}

		Date[] dateValues = new Date[values.length];

		for (int i = 0; i < values.length; i++) {
			dateValues[i] = getDate(values[i], dateFormat);
		}

		return dateValues;
	}

	public static double getDouble(Serializable value) {
		return getDouble(value, 0.0D);
	}

	public static double getDouble(Serializable value, double defaultValue) {
		return get(value, defaultValue);
	}

	public static double getDouble(String value) {
		return getDouble(value, 0.0D);
	}

	public static double getDouble(String value, double defaultValue) {
		return get(value, defaultValue);
	}

	public static double[] getDoubleValues(Serializable value) {
		return getDoubleValues(value, DEFAULT_DOUBLE_VALUES);
	}

	public static double[] getDoubleValues(Serializable value,
			double[] defaultValue) {
		Class classObject = value.getClass();

		if (classObject.isArray()) {
			Class componentType = classObject.getComponentType();

			if (componentType.isAssignableFrom(String.class)) {
				return getDoubleValues((String[]) value, defaultValue);
			}
			if (componentType.isAssignableFrom(Double.class)) {
				return (double[]) value;
			}
		}

		return defaultValue;
	}

	public static double[] getDoubleValues(String[] values) {
		return getDoubleValues(values, DEFAULT_DOUBLE_VALUES);
	}

	public static double[] getDoubleValues(String[] values,
			double[] defaultValue) {
		if (values == null) {
			return defaultValue;
		}

		double[] doubleValues = new double[values.length];

		for (int i = 0; i < values.length; i++) {
			doubleValues[i] = getDouble(values[i]);
		}

		return doubleValues;
	}

	public static float getFloat(Serializable value) {
		return getFloat(value, 0.0F);
	}

	public static float getFloat(Serializable value, float defaultValue) {
		return get(value, defaultValue);
	}

	public static float getFloat(String value) {
		return getFloat(value, 0.0F);
	}

	public static float getFloat(String value, float defaultValue) {
		return get(value, defaultValue);
	}

	public static float[] getFloatValues(Serializable value) {
		return getFloatValues(value, DEFAULT_FLOAT_VALUES);
	}

	public static float[] getFloatValues(Serializable value,
			float[] defaultValue) {
		Class classObject = value.getClass();

		if (classObject.isArray()) {
			Class componentType = classObject.getComponentType();

			if (componentType.isAssignableFrom(String.class)) {
				return getFloatValues((String[]) value, defaultValue);
			}
			if (componentType.isAssignableFrom(Float.class)) {
				return (float[]) value;
			}
		}

		return defaultValue;
	}

	public static float[] getFloatValues(String[] values) {
		return getFloatValues(values, DEFAULT_FLOAT_VALUES);
	}

	public static float[] getFloatValues(String[] values, float[] defaultValue) {
		if (values == null) {
			return defaultValue;
		}

		float[] floatValues = new float[values.length];

		for (int i = 0; i < values.length; i++) {
			floatValues[i] = getFloat(values[i]);
		}

		return floatValues;
	}

	public static int getInteger(Serializable value) {
		return getInteger(value, 0);
	}

	public static int getInteger(Serializable value, int defaultValue) {
		return get(value, defaultValue);
	}

	public static int getInteger(String value) {
		return getInteger(value, 0);
	}

	public static int getInteger(String value, int defaultValue) {
		return get(value, defaultValue);
	}

	public static int[] getIntegerValues(Serializable value) {
		return getIntegerValues(value, DEFAULT_INTEGER_VALUES);
	}

	public static int[] getIntegerValues(Serializable value, int[] defaultValue) {
		Class classObject = value.getClass();

		if (classObject.isArray()) {
			Class componentType = classObject.getComponentType();

			if (componentType.isAssignableFrom(String.class)) {
				return getIntegerValues((String[]) value, defaultValue);
			}
			if (componentType.isAssignableFrom(Integer.class)) {
				return (int[]) value;
			}
		}

		return defaultValue;
	}

	public static int[] getIntegerValues(String[] values) {
		return getIntegerValues(values, DEFAULT_INTEGER_VALUES);
	}

	public static int[] getIntegerValues(String[] values, int[] defaultValue) {
		if (values == null) {
			return defaultValue;
		}

		int[] intValues = new int[values.length];

		for (int i = 0; i < values.length; i++) {
			intValues[i] = getInteger(values[i]);
		}

		return intValues;
	}

	public static long getLong(Serializable value) {
		return getLong(value, 0L);
	}

	public static long getLong(Serializable value, long defaultValue) {
		return get(value, defaultValue);
	}

	public static long getLong(String value) {
		return getLong(value, 0L);
	}

	public static long getLong(String value, long defaultValue) {
		return get(value, defaultValue);
	}

	public static long[] getLongValues(Serializable value) {
		return getLongValues(value, DEFAULT_LONG_VALUES);
	}

	public static long[] getLongValues(Serializable value, long[] defaultValue) {
		Class classObject = value.getClass();

		if (classObject.isArray()) {
			Class componentType = classObject.getComponentType();

			if (componentType.isAssignableFrom(String.class)) {
				return getLongValues((String[]) value, defaultValue);
			}
			if (componentType.isAssignableFrom(Long.class)) {
				return (long[]) value;
			}
		}

		return defaultValue;
	}

	public static long[] getLongValues(String[] values) {
		return getLongValues(values, DEFAULT_LONG_VALUES);
	}

	public static long[] getLongValues(String[] values, long[] defaultValue) {
		if (values == null) {
			return defaultValue;
		}

		long[] longValues = new long[values.length];

		for (int i = 0; i < values.length; i++) {
			longValues[i] = getLong(values[i]);
		}

		return longValues;
	}

	public static short getShort(Serializable value) {
		return getShort(value, (short)0);
	}

	public static short getShort(Serializable value, short defaultValue) {
		return get(value, defaultValue);
	}

	public static short getShort(String value) {
		return getShort(value, (short)0);
	}

	public static short getShort(String value, short defaultValue) {
		return get(value, defaultValue);
	}

	public static short[] getShortValues(Serializable value) {
		return getShortValues(value, DEFAULT_SHORT_VALUES);
	}

	public static short[] getShortValues(Serializable value,
			short[] defaultValue) {
		Class classObject = value.getClass();

		if (classObject.isArray()) {
			Class componentType = classObject.getComponentType();

			if (componentType.isAssignableFrom(String.class)) {
				return getShortValues((String[]) value, defaultValue);
			}
			if (componentType.isAssignableFrom(Short.class)) {
				return (short[]) value;
			}
		}

		return defaultValue;
	}

	public static short[] getShortValues(String[] values) {
		return getShortValues(values, DEFAULT_SHORT_VALUES);
	}

	public static short[] getShortValues(String[] values, short[] defaultValue) {
		if (values == null) {
			return defaultValue;
		}

		short[] shortValues = new short[values.length];

		for (int i = 0; i < values.length; i++) {
			shortValues[i] = getShort(values[i]);
		}

		return shortValues;
	}

	public static String getString(Serializable value) {
		return getString(value, "");
	}

	public static String getString(Serializable value, String defaultValue) {
		return get(value, defaultValue);
	}

	public static String getString(String value) {
		return getString(value, "");
	}

	public static String getString(String value, String defaultValue) {
		return get(value, defaultValue);
	}

	private static int _parseInt(String value, int defaultValue) {
		int length = value.length();

		if (length <= 0) {
			return defaultValue;
		}

		int pos = 0;
		int limit = -2147483647;
		boolean negative = false;

		char c = value.charAt(0);

		if (c < '0') {
			if (c == '-') {
				limit = -2147483648;
				negative = true;
			} else if (c != '+') {
				return defaultValue;
			}

			if (length == 1) {
				return defaultValue;
			}

			pos++;
		}

		int smallLimit = limit / 10;

		int result = 0;

		while (pos < length) {
			if (result < smallLimit) {
				return defaultValue;
			}

			c = value.charAt(pos++);

			if ((c < '0') || (c > '9')) {
				return defaultValue;
			}

			int number = c - '0';

			result *= 10;

			if (result < limit + number) {
				return defaultValue;
			}

			result -= number;
		}

		if (negative) {
			return result;
		}

		return -result;
	}

	private static long _parseLong(String value, long defaultValue) {
		int length = value.length();

		if (length <= 0) {
			return defaultValue;
		}

		int pos = 0;
		long limit = -9223372036854775807L;
		boolean negative = false;

		char c = value.charAt(0);

		if (c < '0') {
			if (c == '-') {
				limit = -9223372036854775808L;
				negative = true;
			} else if (c != '+') {
				return defaultValue;
			}

			if (length == 1) {
				return defaultValue;
			}

			pos++;
		}

		long smallLimit = limit / 10L;

		long result = 0L;

		while (pos < length) {
			if (result < smallLimit) {
				return defaultValue;
			}

			c = value.charAt(pos++);

			if ((c < '0') || (c > '9')) {
				return defaultValue;
			}

			int number = c - '0';

			result *= 10L;

			if (result < limit + number) {
				return defaultValue;
			}

			result -= number;
		}

		if (negative) {
			return result;
		}

		return -result;
	}

	private static short _parseShort(String value, short defaultValue) {
		int i = _parseInt(value, defaultValue);

		if ((i < -32768) || (i > 32767)) {
			return defaultValue;
		}

		return (short) i;
	}

	private static String _trim(String value) {
		value = value.trim();

		int length = value.length();

		StringBuilder sb = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
			char c = value.charAt(i);

			if ((!Character.isDigit(c)) && ((c != '-') || (i != 0))
					&& (c != '.') && (c != 'E') && (c != 'e'))
				continue;
			sb.append(c);
		}

		return sb.toString();
	}
}
