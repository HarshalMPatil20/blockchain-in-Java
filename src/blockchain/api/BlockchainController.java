package blockchain.api;
import static spark.Spark.*;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;

public class BlockchainController {

    private Blockchain blockchain;
    private Gson gson = new Gson();

    public BlockchainController() {
        blockchain = new Blockchain(); // Initialize blockchain
    }

    // Initialize API routes
    public void initRoutes() {
        // Route to get the current blockchain
        get("/blockchain", (req, res) -> {
            res.type("application/json");
            return gson.toJson(blockchain.getChain());
        });

        // Route to add a new block to the blockchain
        post("/addBlock", (req, res) -> {
            Map<String, String> body = gson.fromJson(req.body(), HashMap.class);
            String data = body.get("data");
            Block newBlock = new Block(data, blockchain.getLatestBlock().getHash());
            blockchain.addBlock(newBlock);

            res.type("application/json");
            return gson.toJson(newBlock);
        });

        // Route to retrieve a specific block by index
        get("/block/:index", (req, res) -> {
            int index = Integer.parseInt(req.params(":index"));
            res.type("application/json");

            if (index < blockchain.getChain().size()) {
                Block block = blockchain.getChain().get(index);
                return gson.toJson(block);
            } else {
                res.status(404);
                return gson.toJson("Block not found.");
            }
        });

        // Route to check the blockchain validity (basic example)
        get("/isChainValid", (req, res) -> {
            res.type("application/json");
            boolean isValid = validateBlockchain();
            return gson.toJson(isValid ? "Blockchain is valid." : "Blockchain is invalid!");
        });
    }

    // Simple validation method to check the integrity of the blockchain
    private boolean validateBlockchain() {
        for (int i = 1; i < blockchain.getChain().size(); i++) {
            Block currentBlock = blockchain.getChain().get(i);
            Block previousBlock = blockchain.getChain().get(i - 1);

            // Check if the current block's hash is correct
            if (!currentBlock.getHash().equals(currentBlock.calculateHash())) {
                return false;
            }

            // Check if the current block points to the correct previous hash
            if (!currentBlock.getPreviousHash().equals(previousBlock.getHash())) {
                return false;
            }
        }
        return true;
    }
}
