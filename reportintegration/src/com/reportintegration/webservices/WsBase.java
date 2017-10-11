package com.reportintegration.webservices;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WsBase {

	protected Response badRequestReponse() {

		return Response.status(Status.BAD_REQUEST.getStatusCode()).build();
		
	}
		
	protected Response successResponse() {

		return Response.status(Status.OK.getStatusCode()).build();
		
	}

	protected Response successResponse(Object o) {

		ObjectMapper mapper = new ObjectMapper();

		try {
//			System.out.println(mapper.writeValueAsString(o));
			return Response.status(Status.OK.getStatusCode()).entity(mapper.writeValueAsString(o)).build();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			return Response.status(Status.INTERNAL_SERVER_ERROR.getStatusCode()).build();
		}
		
	}


	protected Response handleBadRequestException() {

		ObjectMapper mapper = new ObjectMapper();
		try {
//			String returnValue = mapper.writeValueAsString();
			return Response.status(Status.BAD_REQUEST.getStatusCode()).entity(mapper.writeValueAsString(Status.BAD_REQUEST.name())).build();
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			return Response.status(Status.FORBIDDEN.getStatusCode()).build();
		}
		
	}
	
	public static boolean isParmTrue(UriInfo uriInfo, String parm) {

		for (Entry<String, List<String>> queryParm : uriInfo.getQueryParameters(true).entrySet()) {
			if (parm.equals(queryParm.getKey())) {
				for(String value : queryParm.getValue()) {
					if ("true".equalsIgnoreCase(value)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public static List<String> getParameters(UriInfo uriInfo) {
		
		List<String> parameters = new ArrayList<String>();
		for (Entry<String, List<String>> queryParm : uriInfo.getQueryParameters(true).entrySet()) {
			parameters.add(queryParm.getKey());
		}
		
		return parameters;
	}
	
	public static HashSet<String> getParameterValues(String parameter, UriInfo uriInfo) {
		
		HashSet<String> parameterValues = new HashSet<String>();
		for (Entry<String, List<String>> queryParm : uriInfo.getQueryParameters(true).entrySet()) {
			if (parameter.equalsIgnoreCase(queryParm.getKey())) {
				for (String value : queryParm.getValue()) {
					if (value.length() > 0) {
						String[] values = value.split(",");
						for (String parameterValue : values) {
							parameterValues.add(parameterValue.toUpperCase());
						}
					}
				}
			}
		}
		return parameterValues;
	}
	
	public static String getParameterValue(String parameter, UriInfo uriInfo) {
		
		for (Entry<String, List<String>> queryParm : uriInfo.getQueryParameters(true).entrySet()) {
			if (parameter.equalsIgnoreCase(queryParm.getKey())) {
				for (String value : queryParm.getValue()) {
					if (value.length() > 0) {
						return value;
					}
				}
			}
		}
		return null;
	}

	public static HashSet<Integer> getIntegerParameters(String parameter, UriInfo uriInfo) {
		
		HashSet<Integer> parameterValues = new HashSet<Integer>();
		for (Entry<String, List<String>> queryParm : uriInfo.getQueryParameters(true).entrySet()) {
			if (parameter.equalsIgnoreCase(queryParm.getKey())) {
				for (String value : queryParm.getValue()) {
					if (value.length() > 0) {
						String[] values = value.split(",");
						for (String parameterValue : values) {
							Integer integerValue = Integer.valueOf(parameterValue);
							if (integerValue != null) {
								parameterValues.add(integerValue);
							}
						}
					}
				}
			}
		}
		return parameterValues;
	}
	
	public static Integer getIntegerParameter(String parameter, UriInfo uriInfo) {
		
		for (Entry<String, List<String>> queryParm : uriInfo.getQueryParameters(true).entrySet()) {
			if (parameter.equalsIgnoreCase(queryParm.getKey())) {
				for (String value : queryParm.getValue()) {
					if (value.length() > 0) {
						Integer integerValue = Integer.valueOf(value);
						if (integerValue != null) {
							return integerValue;
						}
					}
				}
			}
		}
		return null;
	}

	public static HashSet<BigDecimal> getBigDecimalParameters(String parameter, UriInfo uriInfo) {
		
		HashSet<BigDecimal> parameterValues = new HashSet<BigDecimal>();
		for (Entry<String, List<String>> queryParm : uriInfo.getQueryParameters(true).entrySet()) {
			if (parameter.equalsIgnoreCase(queryParm.getKey())) {
				for (String value : queryParm.getValue()) {
					if (value.length() > 0) {
						String[] values = value.split(",");
						for (String parameterValue : values) {
							BigDecimal bigDecimalValue = new BigDecimal(parameterValue);
							if (bigDecimalValue != null) {
								parameterValues.add(bigDecimalValue);
							}
						}
					}
				}
			}
		}
		return parameterValues;
	}
	
	public static BigDecimal getBigDecimalParameter(String parameter, UriInfo uriInfo) {
		
		for (Entry<String, List<String>> queryParm : uriInfo.getQueryParameters(true).entrySet()) {
			if (parameter.equalsIgnoreCase(queryParm.getKey())) {
				for (String value : queryParm.getValue()) {
					if (value.length() > 0) {
						BigDecimal bigDecimalValue = new BigDecimal(value);
						if (bigDecimalValue != null) {
							return bigDecimalValue;
						}
					}
				}
			}
		}
		return null;
	}
	
	public static HashSet<Date> getDateParameters(String parameter, UriInfo uriInfo) {
		
		HashSet<Date> parameterValues = new HashSet<Date>();
		for (Entry<String, List<String>> queryParm : uriInfo.getQueryParameters(true).entrySet()) {
			if (parameter.equalsIgnoreCase(queryParm.getKey())) {
				for (String value : queryParm.getValue()) {
					if (value.length() > 0) {
						String[] values = value.split(",");
						for (String parameterValue : values) {
							Date dateValue = getDate(parameterValue);
							if (dateValue != null) {
								parameterValues.add(dateValue);
							}
						}
					}
				}
			}
		}
		return parameterValues;
	}
	
	public static Date getDateParameter(String parameter, UriInfo uriInfo) {
		
		for (Entry<String, List<String>> queryParm : uriInfo.getQueryParameters(true).entrySet()) {
			if (parameter.equalsIgnoreCase(queryParm.getKey())) {
				for (String value : queryParm.getValue()) {
					if (value.length() > 0) {
						Date dateValue = getDate(value);
						if (dateValue != null) {
							return dateValue;
						}
					}
				}
			}
		}
		return null;
	}
	
	static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
	static SimpleDateFormat tf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 

	public static Date getDate(String value) {
	
		if (value.contains(":")) {
			try {
				Date date = tf.parse(value);
				return date;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (value.contains("-")) {
			// Text format
			try {
				Date date = df.parse(value);
				return date;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			Date date = new Date();
			date.setTime(Long.valueOf(value));
			return date;
		}
		return null;
	}
	
}
