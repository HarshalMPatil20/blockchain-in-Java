package blockchain.config;
public class ConsensusConfig {

    // Fetch the consensus mechanism from the config file
    public static String getConsensusAlgorithm() {
        return Config.get("consensus.algorithm", "ProofOfWork");
    }

    // Fetch the difficulty level for Proof of Work (used in PoW consensus)
    public static int getProofOfWorkDifficulty() {
        return Integer.parseInt(Config.get("consensus.pow.difficulty", "4"));
    }

    // Fetch any other consensus-related settings here
    public static int getStakeThreshold() {
        return Integer.parseInt(Config.get("consensus.pos.stakeThreshold", "100"));
    }
}
