package questions.ratelimiter;

import questions.ratelimiter.facade.RateLimiterService;
import questions.ratelimiter.strategy.FixedWindowStrategy;
import questions.ratelimiter.strategy.RateLimitingStrategy;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {
    static void main() {
        String userId = "user123";

        System.out.println("=== Fixed Window Demo ===");
        runFixedWindowDemo(userId);

//        System.out.println("\n=== Token Bucket Demo ===");
//        runTokenBucketDemo(userId);
    }

    private static void runFixedWindowDemo(String userId) {
        int maxRequests = 5;
        int windowSeconds = 10;

        RateLimitingStrategy fixedWindowStrategy = new FixedWindowStrategy(maxRequests, windowSeconds);
        RateLimiterService service = RateLimiterService.getInstance();
        service.setRateLimitingStrategy(fixedWindowStrategy);

        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 10; i++) {
            executor.submit(() -> service.handleRequest(userId));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        executor.shutdown();
    }
}
