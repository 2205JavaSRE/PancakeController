package com.teamproject.util;

import java.io.File;

import io.javalin.Javalin;
import io.micrometer.core.instrument.binder.jvm.ClassLoaderMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmGcMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmMemoryMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmThreadMetrics;
import io.micrometer.core.instrument.binder.system.DiskSpaceMetrics;
import io.micrometer.core.instrument.binder.system.ProcessorMetrics;
import io.micrometer.core.instrument.binder.system.UptimeMetrics;
import io.micrometer.prometheus.PrometheusConfig;
import io.micrometer.prometheus.PrometheusMeterRegistry;

<<<<<<< HEAD:src/main/java/com/teamproject/controller/MicrometerMonitorController.java
public class MicrometerMonitorController {
//	public static PrometheusMeterRegistry StartMonitoringRegistry() {
//		PrometheusMeterRegistry registry = new PrometheusMeterRegistry(PrometheusConfig.DEFAULT);
//		
//		registry.config().commonTags("application", "Pancake Bank Monitor");
//		
//		new ClassLoaderMetrics().bindTo(registry);
//		new JvmMemoryMetrics().bindTo(registry);
//		new JvmGcMetrics().bindTo(registry);
//		new JvmThreadMetrics().bindTo(registry);
//		new UptimeMetrics().bindTo(registry);
//		new ProcessorMetrics().bindTo(registry);
//		new DiskSpaceMetrics(new File(System.getProperty("user.dir"))).bindTo(registry);
//		
//		
//		return registry;
//		
//	}
	//redundant
=======
public class MicrometerMonitor {
	public static PrometheusMeterRegistry StartMonitoringRegistry() {
		PrometheusMeterRegistry registry = new PrometheusMeterRegistry(PrometheusConfig.DEFAULT);
		
		registry.config().commonTags("application", "Pancake Bank Monitor");
		
		new ClassLoaderMetrics().bindTo(registry);
		new JvmMemoryMetrics().bindTo(registry);
		new JvmGcMetrics().bindTo(registry);
		new JvmThreadMetrics().bindTo(registry);
		new UptimeMetrics().bindTo(registry);
		new ProcessorMetrics().bindTo(registry);
		new DiskSpaceMetrics(new File(System.getProperty("user.dir"))).bindTo(registry);
		
		
		return registry;
		
	}
>>>>>>> 1633c40bd0f9a988a7f1e56d20ae1c96d35ddc16:src/main/java/com/teamproject/util/MicrometerMonitor.java
	
	public static void MoniteringPaths(Javalin app, PrometheusMeterRegistry registry) {

		app.get("/metrics", ctx ->{
			ctx.result(registry.scrape());
		});
		
		
	}
}
