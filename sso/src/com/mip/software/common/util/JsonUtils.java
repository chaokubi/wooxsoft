package com.mip.software.common.util;

import java.io.IOException;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

public class JsonUtils {
	private static JsonFactory jsonFactory = new JsonFactory();
	private static ObjectMapper objectMapper = new ObjectMapper();
	static {
		objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
	}

	/**
	 * object 转 json 串,异常时返回空串
	 * 
	 * @param obj
	 * @return
	 */
	public static String obj2Json(Object obj) {
		StringWriter writer = new StringWriter();
		JsonGenerator jgen = null;
		try {
			jgen = jsonFactory.createJsonGenerator(writer);
			jgen.useDefaultPrettyPrinter();
			objectMapper.writeValue(jgen, obj);
		} catch (IOException e) {
		} finally {
			IOUtils.closeQuietly(jgen);
		}
		return writer.toString();
	}

	/**
	 * json串 转 object ,异常时返回 null
	 * 
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> T json2Obj(String json, Class<T> clazz) {
		try {
			return objectMapper.readValue(json, clazz);
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * json串 转 Map, 异常时返回空Map
	 * 
	 * @param json
	 * @return
	 */
	public static Map<String, Object> json2Map(String json) {
		try {
			TypeReference<LinkedHashMap<String, Object>> typeRef = new TypeReference<LinkedHashMap<String, Object>>() {
			};
			return objectMapper.readValue(json, typeRef);
		} catch (Exception e) {
		}
		return new LinkedHashMap<String, Object>();
	}
}
