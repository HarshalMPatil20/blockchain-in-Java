// File: src/blockchain/validation/Validator.java

package validation;

import blockchain.Block;
import blockchain.Transaction;
import java.util.List;

public class Validator {
    
    // Validate a block
    public boolean validateBlock(Block block, String previousHash) {
        // Check if the block's previous hash matches the expected previous hash
        if (!block.getPreviousHash().equals(previousHash)) {
            System.out.println("Block validation failed: Previous hash does not match.");
            return false;
        }

        // Check if the block's hash is valid
        String calculatedHash = block.calculateHash();
        if (!block.getHash().equals(calculatedHash)) {
            System.out.println("Block validation failed: Hash does not match.");
            return false;
        }

        // Block is valid
        return true;
    }

    // Validate a transaction
    public boolean validateTransaction(Transaction transaction) {
        // Add validation logic for transactions (e.g., check for double spending)
        if (transaction.getTransactionId() == null || transaction.getData() == null) {
            System.out.println("Transaction validation failed: Transaction ID or data is null.");
            return false;
        }

        // Transaction is valid
        return true;
    }
    
    // Validate a list of transactions
    public boolean validateTransactionList(List<Transaction> transactions) {
        for (Transaction transaction : transactions) {
            if (!validateTransaction(transaction)) {
                return false; // Invalid transaction found
            }
        }
        return true; // All transactions are valid
    }
}
