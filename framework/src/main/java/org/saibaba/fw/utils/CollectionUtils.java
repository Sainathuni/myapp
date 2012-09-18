package org.saibaba.fw.utils;

import java.util.Collection;


public class CollectionUtils {
   
	public static String[] toStringArray(Collection<String> list) {
		String[] stringArray = null;
		if(list != null && list.size() > 0) {
			stringArray = (String[])list.toArray(new String[list.size()]);
		}
		return stringArray;
	}
	
	public static Object[] toObjectArray(Collection<Object> list) {
		Object[] objArray = null;
		if(list != null && list.size() > 0) {
			objArray = (Object[])list.toArray(new Object[list.size()]);
		}
		return objArray;
	}
	
	public static Float[] toFloatArray(Collection<Float> list) {
		Float[] floatArray = null;
		if(list != null && list.size() > 0) {
			floatArray = (Float[])list.toArray(new Float[list.size()]);
		}
		return floatArray;
	}
	
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Collection list) {
		return list == null || list.isEmpty();
	}
	
	@SuppressWarnings("rawtypes")
	public static boolean isNotEmpty(Collection list) {
		return list != null && list.size() > 0;
	}
}
