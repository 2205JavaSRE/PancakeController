package com.teamproject.util;

import java.io.File;
import java.util.concurrent.TimeUnit;

import com.teamproject.controller.RequestMapping;

import io.javalin.Javalin;
import io.javalin.plugin.metrics.MicrometerPlugin;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Timer;
import io.micrometer.core.instrument.binder.jvm.ClassLoaderMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmGcMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmMemoryMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmThreadMetrics;
import io.micrometer.core.instrument.binder.system.DiskSpaceMetrics;
import io.micrometer.core.instrument.binder.system.ProcessorMetrics;
import io.micrometer.core.instrument.binder.system.UptimeMetrics;
import io.micrometer.prometheus.PrometheusConfig;
import io.micrometer.prometheus.PrometheusMeterRegistry;

public class Prometheus {
	
	public Prometheus(){
		super();
	}
	
	public static PrometheusMeterRegistry registry = new PrometheusMeterRegistry(PrometheusConfig.DEFAULT);
	
	
	static Counter counter = Counter.builder("total_login_attempts")
			.description("The number of login attempts")
			.tag("purpose", "tracking").register(registry);
	
	
	static Timer loginLatencyTimer = Timer.builder("login_response_latency")
			.description("How long it takes to execute login")
			.tag("purpose", "measure any abnormal login response times").register(registry);
	
	public static double counter() {
			counter.increment(1);
			return counter.count();
	}
	
	public static void measureLatency() {
		loginLatencyTimer.record(() ->{
			try {
				TimeUnit.MILLISECONDS.sleep(40);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		
		loginLatencyTimer.record(30, TimeUnit.MILLISECONDS);
		
	}
	
	
	public static void monitoring() {
		
			
		
	 registry.config().commonTags("app","monitored-app");
	 
	 
	 
		
	 new ClassLoaderMetrics().bindTo(registry);
	 new JvmMemoryMetrics().bindTo(registry);
	 new JvmGcMetrics().bindTo(registry);
	 new JvmThreadMetrics().bindTo(registry);
	 new UptimeMetrics().bindTo(registry);
	 new ProcessorMetrics().bindTo(registry);
	 new DiskSpaceMetrics(new File(System.getProperty("user.dir"))).bindTo(registry);
	 


	}

}
