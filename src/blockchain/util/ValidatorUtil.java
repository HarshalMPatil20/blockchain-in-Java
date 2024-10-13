package blockchain.util;
import java.security.PublicKey;

public class ValidatorUtil {
    private PublicKey publicKey;

    public ValidatorUtil(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    public boolean validateBlock(Block block) {
        // Example validation logic
        return true;
    }
}
