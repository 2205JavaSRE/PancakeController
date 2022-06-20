package com.teamproject;

import io.javalin.Javalin;
import io.javalin.plugin.metrics.MicrometerPlugin;
import io.micrometer.prometheus.PrometheusMeterRegistry;

import com.teamproject.util.*;
import com.teamproject.controller.RequestMapping;
import com.teamproject.util.Prometheus;
import com.teamproject.controller.*;




public class MainDriver {


	public static void main(String...args) { //jenkins

		Prometheus.monitoring();
		
		Javalin app = Javalin.create( config -> {
			config.registerPlugin(new MicrometerPlugin(Prometheus.registry));
				}
				).start(7070);
		
		 MicrometerMonitorController.MoniteringPaths(app, Prometheus.registry);

		 RequestMapping.configureRoutes(app);
	
		
		 
		 
		
//using different class		 
//			
//		 app.get("/prometheus", ctx -> {
//				 
//				 ctx.result(Prometheus.registry.scrape());
//			 });
//	}
//	
	 

	}
}
