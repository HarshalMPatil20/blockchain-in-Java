package blockchain.util;
import java.io.*;

public class PersistenceUtil {

    // Save an object to a file
    public static void saveObjectToFile(Object object, String fileName) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(fileName);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(object);
        out.close();
        fileOut.close();
        System.out.println("Data has been saved to " + fileName);
    }

    // Load an object from a file
    public static Object loadObjectFromFile(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream(fileName);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        Object object = in.readObject();
        in.close();
        fileIn.close();
        System.out.println("Data has been loaded from " + fileName);
        return object;
    }

    // Check if a file exists
    public static boolean fileExists(String fileName) {
        File file = new File(fileName);
        return file.exists();
    }
}
