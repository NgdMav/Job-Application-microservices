package com.mav.microservicejob.health;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreaker.Metrics;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.health.contributor.CompositeHealthContributor;
import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.HealthContributor;
import org.springframework.boot.health.contributor.HealthIndicator;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

@Component("circuitBreakers")
public class CircuitBreakersHealthIndicator
        implements HealthIndicator, CompositeHealthContributor {

    private final CircuitBreakerRegistry registry;

    public CircuitBreakersHealthIndicator(CircuitBreakerRegistry registry) {
        this.registry = registry;
    }

    @Override
    public Health health() {
        boolean anyOpen = registry.getAllCircuitBreakers()
                .stream()
                .anyMatch(cb -> cb.getState() == CircuitBreaker.State.OPEN);

        return anyOpen ? Health.down().build() : Health.up().build();
    }

    public Map<String, HealthContributor> getContributors() {
        Map<String, HealthContributor> contributors = new LinkedHashMap<>();

        registry.getAllCircuitBreakers().forEach(cb ->
                contributors.put(cb.getName(), new CircuitBreakerIndicator(cb))
        );

        return contributors;
    }

    @Override
    public HealthContributor getContributor(@NonNull String name) {
        CircuitBreaker cb = registry.find(name).orElse(null);
        return cb != null ? new CircuitBreakerIndicator(cb) : null;
    }

    @Override
    public @NonNull Stream<Entry> stream() {
        return registry.getAllCircuitBreakers()
                .stream()
                .map(cb -> new Entry(cb.getName(), new CircuitBreakerIndicator(cb)));
    }

    static final class CircuitBreakerIndicator implements HealthIndicator {

        private final CircuitBreaker cb;

        CircuitBreakerIndicator(CircuitBreaker cb) {
            this.cb = cb;
        }

        @Override
        public Health health() {
            Metrics m = cb.getMetrics();

            Map<String, Object> details = new LinkedHashMap<>();
            details.put("state", cb.getState().name());
            details.put("failureRate", m.getFailureRate());
            details.put("failureRateThreshold",
                    cb.getCircuitBreakerConfig().getFailureRateThreshold());
            details.put("slowCallRate", m.getSlowCallRate());
            details.put("slowCallRateThreshold",
                    cb.getCircuitBreakerConfig().getSlowCallRateThreshold());
            details.put("bufferedCalls", m.getNumberOfBufferedCalls());
            details.put("failedCalls", m.getNumberOfFailedCalls());
            details.put("slowCalls", m.getNumberOfSlowCalls());
            details.put("slowFailedCalls", m.getNumberOfSlowFailedCalls());
            details.put("notPermittedCalls", m.getNumberOfNotPermittedCalls());

            Health.Builder builder =
                    cb.getState() == CircuitBreaker.State.OPEN
                            ? Health.down()
                            : Health.up();

            return builder.withDetails(details).build();
        }
    }
}
