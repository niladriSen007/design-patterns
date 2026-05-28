package questions.ratelimiter.strategy;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class FixedWindowStrategy implements RateLimitingStrategy {

    private final int maxRequest;
    private final long windowTimeInMilis;
    private final Map<String, UserRequest> userRequestMap;

    public FixedWindowStrategy(int maxRequest, long windowTimeInSec) {
        this.maxRequest = maxRequest;
        this.windowTimeInMilis = windowTimeInSec * 1000;
        userRequestMap = new ConcurrentHashMap<>();
    }

    @Override
    public boolean allowRequest(String userId) {
        long currentTime = System.currentTimeMillis();
        userRequestMap.putIfAbsent(userId, new UserRequest(currentTime));

        UserRequest userRequest = userRequestMap.get(userId);
        synchronized (userRequest) {
            if ((currentTime - userRequest.windowStart) >= windowTimeInMilis) {
                userRequest.reset(currentTime);
            }

            if (userRequest.requestCount.get() < maxRequest) {
                userRequest.requestCount.incrementAndGet();
                return true;
            }
            return false;
        }
    }

    private static class UserRequest {
        long windowStart;
        AtomicInteger requestCount;

        public UserRequest(long windowStart) {
            this.windowStart = windowStart;
            this.requestCount = new AtomicInteger(0);
        }

        public void reset(long newWindowStart) {
            this.requestCount.set(0);
            this.windowStart = newWindowStart;
        }
    }
}
