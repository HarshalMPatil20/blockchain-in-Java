// File: src/blockchain/consensus/ProofOfAuthority.java

package blockchain.consensus;

import blockchain.Block;
import java.util.List;

public class ProofOfAuthority implements Consensus {
    private List<String> validators;
    private int currentValidatorIndex;  // Index of the current validator

    // Constructor
    public ProofOfAuthority(List<String> validators) {
        this.validators = validators;
        this.currentValidatorIndex = 0; // Start with the first validator
    }

    @Override
    public void addBlock(Block block) {
        // give logic for adding a block
        String selectedValidator = selectValidator();
        System.out.println("Validator selected: " + selectedValidator);
        
        if (validateBlock(block, selectedValidator)) {
            block.setHash(block.calculateHash()); // Calculate hash before adding to the blockchain
            BlockService.addBlock(block);
            System.out.println("Block added by validator: " + selectedValidator);
        } else {
            System.out.println("Block validation failed.");
        }
        System.out.println("Block added by a validator: " + block.getData());
    }

    public String selectValidator() {
        String selectedValidator = validators.get(currentValidatorIndex);
        currentValidatorIndex = (currentValidatorIndex + 1) % validators.size(); // Move to the next validator
        return selectedValidator;
    }

    @Override
    public boolean validateBlock(Block block) {
        return validator.equals(selectValidator());
    }
}
