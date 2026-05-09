package structural.facade;

class DeploymentFacade {
    private final GithubSystem githubSystem;
    private final TestingSystem testingSystem;
    private final BuildSystem buildSystem;
    private final DeploymentTarget deploymentTarget;

    public DeploymentFacade() {
        this.githubSystem = new GithubSystem();
        this.testingSystem = new TestingSystem();
        this.buildSystem = new BuildSystem();
        this.deploymentTarget = new DeploymentTarget();
    }

    public boolean deployApplication(String branch, String serverAddress) {

        System.out.println("\nFACADE: --- Initiating FULL DEPLOYMENT for branch: " + branch + " to " + serverAddress + " ---");
        boolean success = true;

        try {
            githubSystem.pullLatestChanges(branch);

            if (!buildSystem.compileProject()) {
                System.err.println("FACADE: DEPLOYMENT FAILED - Build compilation failed.");
                return false;
            }

            String artifactPath = buildSystem.getArtifactPath();

            if (!testingSystem.runUnitTests()) {
                System.err.println("FACADE: DEPLOYMENT FAILED - Unit tests failed.");
                return false;
            }

            if (!testingSystem.runIntegrationTests()) {
                System.err.println("FACADE: DEPLOYMENT FAILED - Integration tests failed.");
                return false;
            }

            deploymentTarget.transferArtifact(artifactPath, serverAddress);
            deploymentTarget.activateNewVersion(serverAddress);

            System.out.println("FACADE: APPLICATION DEPLOYED SUCCESSFULLY to " + serverAddress + "!");


        } catch (Exception e) {
            System.err.println("FACADE: DEPLOYMENT FAILED - An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
            success = false;
        }
        return success;
    }
}

public class ThisIsWhyFacade {
    static void main() {
        DeploymentFacade deploymentFacade = new DeploymentFacade();
        deploymentFacade.deployApplication("master", "prod.server.example.com");
    }
}
