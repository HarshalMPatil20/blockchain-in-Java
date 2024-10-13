
package blockchain.api;

import static spark.Spark.*;

public class ApiServer {

    public static void main(String[] args) {
        // Start the API server
        port(4567); // Define the port number (e.g., 4567)

        // Set up routes
        BlockchainController blockchainController = new BlockchainController();
        blockchainController.initRoutes();

        System.out.println("API Server started on http://localhost:4567");
    }
}
