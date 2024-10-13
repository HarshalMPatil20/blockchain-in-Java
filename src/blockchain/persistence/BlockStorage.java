package blockchain.persistence;
import java.io.*;
import java.util.List;

public class BlockStorage {
    private static final String FILE_PATH = "blockchain_data.dat";

    public static void saveBlockchain(List<Block> blockchain) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(FILE_PATH);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(blockchain);
        out.close();
        fileOut.close();
    }

    public static List<Block> loadBlockchain() throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream(FILE_PATH);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        List<Block> blockchain = (List<Block>) in.readObject();
        in.close();
        fileIn.close();
        return blockchain;
    }
}
