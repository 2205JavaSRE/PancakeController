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


public class MicrometerMonitor {	


	public static void MoniteringPaths(Javalin app, PrometheusMeterRegistry registry) {

		app.get("/metrics", ctx ->{
			ctx.result(registry.scrape());
		});
		
		
	}
	
}
