package questions.ratelimiter.strategy;

public class TokenBucketStrategy implements RateLimitingStrategy {
    @Override
    public boolean allowRequest(String userId) {
        return false;
    }

    private static class TokenBucket{

    }
}
