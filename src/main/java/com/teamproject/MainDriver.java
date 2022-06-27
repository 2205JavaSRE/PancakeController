package com.teamproject;

import io.javalin.Javalin;
import io.javalin.plugin.metrics.MicrometerPlugin;
import com.teamproject.controller.RequestMapping;
import com.teamproject.util.Prometheus;


public class MainDriver {


	public static void main(String...args) { 

		
		
		Javalin app = Javalin.create( config -> {
			config.registerPlugin(new MicrometerPlugin(Prometheus.registry));
				}
				).start(7070);
		
		 

		 RequestMapping.configureRoutes(app);
		 
		 
		 Prometheus.monitoring();
		 Prometheus.monitoringPath(app, Prometheus.registry);
	
		
		 
		 
	}
}