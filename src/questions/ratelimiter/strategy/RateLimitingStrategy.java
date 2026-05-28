package questions.ratelimiter.strategy;

public interface RateLimitingStrategy {
    boolean allowRequest(String userId);
}
