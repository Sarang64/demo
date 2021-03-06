package com.nexclipper.demo;

import com.nexclipper.demo.bean.Order;
import com.nexclipper.demo.service.CustomMetricService;
import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import reactor.core.publisher.Flux;

import java.time.Duration;

@SpringBootApplication
@EnableScheduling
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	static int i =0;

	@Bean
	public TimedAspect timedAspect(MeterRegistry registry) {
		return new TimedAspect(registry);
	}

	@Autowired
	CustomMetricService beerService;
	@EventListener(ApplicationReadyEvent.class)
	public void order() {
			Flux.interval(Duration.ofSeconds(1))
					.map(DemoApplication::toOrder)
					.doOnEach(o -> beerService.orderBeer(o.get()))
					.subscribe();

	}

	private static Order toOrder(Long l) {
		double amount = l % 5;
		String type = l % 2 == 0 ? "ale" : "light";
		return new Order(amount, type, i);
	}
}
