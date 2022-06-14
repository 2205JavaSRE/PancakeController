package com.teamproject;

import io.javalin.Javalin;
import io.javalin.plugin.metrics.MicrometerPlugin;
import com.teamproject.util.*;
import com.teamproject.controller.*;




public class MainDriver {
	

	


	public static void main(String...args) {


		Javalin app = Javalin.create( config -> {
			config.registerPlugin(new MicrometerPlugin(Prometheus.registry));
				}
				).start(7070);
		
		 RequestMapping.configureRoutes(app);
	
		
		
		 
			
		 app.get("/prometheus", ctx -> {
				 
				 ctx.result(Prometheus.registry.scrape());
			 });
		
	}
	
	 

}
