
package tests;
import blockchain.Block;
import blockchain.Blockchain;
import blockchain.Transaction;
import blockchain.validation.Validator;
import blockchain.consensus.ProofOfAuthority;

import java.util.ArrayList;
import java.util.List;

public class BlockchainTest {
    
    public static void main(String[] args) {
        testBlockchain();
    }

    public static void testBlockchain() {
        // Create a new blockchain
        Blockchain blockchain = new Blockchain();
        Validator validator = new Validator();

        // Create a sample transaction
        Transaction transaction1 = new Transaction("tx1", "Farmer A sells 100kg of wheat");
        Transaction transaction2 = new Transaction("tx2", "Farmer B sells 200kg of rice");

        // Add the transactions to a list
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction1);
        transactions.add(transaction2);

        // Validate transactions
        if (validator.validateTransactionList(transactions)) {
            System.out.println("All transactions are valid.");
        } else {
            System.out.println("Some transactions are invalid.");
        }

        // Create and add blocks
        Block block1 = new Block("Block 1: " + transaction1.getData(), blockchain.getChain().get(blockchain.getChain().size() - 1).getHash());
        Block block2 = new Block("Block 2: " + transaction2.getData(), blockchain.getChain().get(blockchain.getChain().size() - 1).getHash());

        // Validate and add blocks to the blockchain
        if (validator.validateBlock(block1, blockchain.getChain().get(blockchain.getChain().size() - 1).getHash())) {
            blockchain.addBlock(block1);
            System.out.println("Block 1 added: " + block1.getData());
        } else {
            System.out.println("Block 1 validation failed.");
        }

        if (validator.validateBlock(block2, blockchain.getChain().get(blockchain.getChain().size() - 1).getHash())) {
            blockchain.addBlock(block2);
            System.out.println("Block 2 added: " + block2.getData());
        } else {
            System.out.println("Block 2 validation failed.");
        }

        // Print the blockchain
        System.out.println("\nCurrent Blockchain:");
        for (Block block : blockchain.getChain()) {
            System.out.println("Block Hash: " + block.getHash() + " | Previous Hash: " + block.getPreviousHash() + " | Data: " + block.getData());
        }
    }
}
