package com.reportintegration.webservices;

import org.glassfish.jersey.server.ResourceConfig;

public class WsApplicationReportIntegration extends ResourceConfig {
	
   public WsApplicationReportIntegration () {
       packages("com.reportintegration.webservices");
//	   register(AuthenticationFilter.class);
   }

}
