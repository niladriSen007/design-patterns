package structural.facade;

class GithubSystem {
    public void pullLatestChanges(String branchName) {
        System.out.println("Github Pull Latest Changes from - " + branchName);
        simulateDelay();
        System.out.println("Code pull complete");
    }

    private void simulateDelay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class BuildSystem {
    public boolean compileProject() {
        System.out.println("BuildSystem: Compiling project...");
        simulateDelay(2000);
        System.out.println("BuildSystem: Build successful.");
        return true;
    }

    public String getArtifactPath() {
        String path = "target/myapplication-1.0.jar";
        System.out.println("BuildSystem: Artifact located at " + path);
        return path;
    }

    private void simulateDelay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class TestingSystem {
    public boolean runUnitTests() {
        System.out.println("Testing: Running unit tests...");
        simulateDelay(1500);
        System.out.println("Testing: Unit tests passed.");
        return true;
    }

    public boolean runIntegrationTests() {
        System.out.println("Testing: Running integration tests...");
        simulateDelay(3000);
        System.out.println("Testing: Integration tests passed.");
        return true;
    }

    private void simulateDelay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class DeploymentTarget {
    public void transferArtifact(String artifactPath, String server) {
        System.out.println("Deployment: Transferring " + artifactPath + " to " + server + "...");
        simulateDelay(1000);
        System.out.println("Deployment: Transfer complete.");
    }

    public void activateNewVersion(String server) {
        System.out.println("Deployment: Activating new version on " + server + "...");
        simulateDelay(500);
        System.out.println("Deployment: Now live on " + server + "!");
    }

    private void simulateDelay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class WhyFacade {
    static void main() {

        String branch = "main";
        String prodServer = "prod.server.example.com";

        // Client must create and manage all subsystems
        GithubSystem vcs = new GithubSystem();
        BuildSystem buildSystem = new BuildSystem();
        TestingSystem testFramework = new TestingSystem();
        DeploymentTarget deployTarget = new DeploymentTarget();

        System.out.println("\n[Client] Starting deployment for branch: " + branch);

        // Step 1: Pull latest code
        vcs.pullLatestChanges(branch);

        // Step 2: Build the project
        if (!buildSystem.compileProject()) {
            System.err.println("[Client] Build failed. Deployment aborted.");
            return;
        }
        String artifact = buildSystem.getArtifactPath();

        // Step 3: Run tests
        if (!testFramework.runUnitTests()) {
            System.err.println("[Client] Unit tests failed. Deployment aborted.");
            return;
        }
        if (!testFramework.runIntegrationTests()) {
            System.err.println("[Client] Integration tests failed. Deployment aborted.");
            return;
        }

        // Step 4: Deploy to production
        deployTarget.transferArtifact(artifact, prodServer);
        deployTarget.activateNewVersion(prodServer);

        System.out.println("[Client] Deployment successful!");

    }
}
