// File: src/main/Main.java

package main;

import blockchain.Blockchain;
import blockchain.Transaction;
import blockchain.consensus.ProofOfAuthority;
import blockchain.validation.Validator;
import blockchain.api.ApiServer;

import java.util.ArrayList;
import java.util.List;

public class Main {
    
    public static void main(String[] args) {
        // Initialize the blockchain
        Blockchain blockchain = new Blockchain();
        Validator validator = new Validator();
        
        // Create and add a sample transaction
        Transaction transaction1 = new Transaction("tx1", "Farmer A sells 100kg of wheat");
        Transaction transaction2 = new Transaction("tx2", "Farmer B sells 200kg of rice");
        
        // Add transactions to a list
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction1);
        transactions.add(transaction2);

        // Validate transactions
        if (validator.validateTransactionList(transactions)) {
            System.out.println("All transactions are valid.");
        } else {
            System.out.println("Some transactions are invalid.");
            return; // Exit if transactions are invalid
        }

        // Create consensus mechanism
        ProofOfAuthority consensus = new ProofOfAuthority(new ArrayList<>());
        
        // Create and add blocks based on transactions
        for (Transaction transaction : transactions) {
            String previousHash = blockchain.getChain().get(blockchain.getChain().size() - 1).getHash();
            Block block = new Block(transaction.getData(), previousHash);
            
            if (validator.validateBlock(block, previousHash)) {
                consensus.addBlock(block);
                blockchain.addBlock(block);
                System.out.println("Block added: " + block.getData());
            } else {
                System.out.println("Block validation failed: " + block.getData());
            }
        }

        // Start the API server (if implemented)
        ApiServer apiServer = new ApiServer(blockchain);
        apiServer.start();

        // Print the current blockchain
        System.out.println("\nCurrent Blockchain:");
        for (Block block : blockchain.getChain()) {
            System.out.println("Block Hash: " + block.getHash() + " | Previous Hash: " + block.getPreviousHash() + " | Data: " + block.getData());
        }
    }
}
