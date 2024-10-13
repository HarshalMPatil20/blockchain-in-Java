// File: src/consensus/Consensus.java
package blockchain.consensus;
import blockchain.Block;

public interface Consensus {
    void addBlock(Block block);
    boolean validateBlock(Block block);
}
