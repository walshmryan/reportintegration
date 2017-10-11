package com.reportintegration.webservices;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/{report : (?i)report}")
public class WsReport extends WsBase {

	@Context ServletContext context;
	@Context ContainerRequestContext request;
	
	@GET
	@Path("/{reportName}")
	@Produces( MediaType.TEXT_PLAIN)
	public Response getKey(@PathParam("reportName") String reportName,
			@Context UriInfo uriInfo)
	{
		ServletContext ctx = context;
//		if (!isAuthorized(request)) return unauthorizedReponse();
		
		StringBuffer sb = new StringBuffer();
		
		sb.append(reportName + " parameters=");
		
	    for (String parameter : getParameters(uriInfo)) {
	    	sb.append(parameter);
	    	if (parameter.contains("integer")) {
	    		sb.append(":" + getIntegerParameter(parameter, uriInfo));
	    	} else if (parameter.contains("bigdecimal")) {
	    		sb.append(":" + getBigDecimalParameter(parameter, uriInfo));
	    	} else if (parameter.contains("date")) {
	    		sb.append(":" + getDateParameter(parameter, uriInfo));
	    	} else {
	    		sb.append(":" + getParameterValue(parameter, uriInfo));
	    	}
	    }

		return successResponse(sb.toString());
	}


}
