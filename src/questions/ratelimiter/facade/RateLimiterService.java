package questions.ratelimiter.facade;

import questions.ratelimiter.strategy.RateLimitingStrategy;

public class RateLimiterService {
    private static RateLimiterService instance;
    private RateLimitingStrategy rateLimitingStrategy;

    private RateLimiterService() {
    }

    public static RateLimiterService getInstance() {
        if (instance == null) {
            synchronized (RateLimiterService.class) {
                if (instance == null) {
                    instance = new RateLimiterService();
                }
            }
        }
        return instance;
    }

    public void setRateLimitingStrategy(RateLimitingStrategy rateLimitingStrategy) {
        this.rateLimitingStrategy = rateLimitingStrategy;
    }

    public void handleRequest(String userId) {
        if (rateLimitingStrategy.allowRequest(userId)) {
            System.out.println("Request from user " + userId + " is allowed");
        } else {
            System.out.println("Request from user " + userId + " is rejected: Rate limit exceeded");
        }
    }
}
