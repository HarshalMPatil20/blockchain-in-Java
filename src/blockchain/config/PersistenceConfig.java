package blockchain.config;
public class PersistenceConfig {

    // Fetch the path where the blockchain data will be saved
    public static String getBlockchainFilePath() {
        return Config.get("persistence.blockchainFilePath", "blockchain_data.dat");
    }

    // Fetch backup or other persistence-related paths/settings
    public static String getBackupFilePath() {
        return Config.get("persistence.backupFilePath", "backup/blockchain_backup.dat");
    }

    // Define any additional persistence-related settings
    public static boolean isAutoBackupEnabled() {
        return Boolean.parseBoolean(Config.get("persistence.autoBackup", "true"));
    }
}
