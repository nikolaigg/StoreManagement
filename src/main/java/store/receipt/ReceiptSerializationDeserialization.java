package store.receipt;

import java.io.*;

public class ReceiptSerializationDeserialization implements ReceiptSerializationDeserializationService {

    @Override
    public void serialize(String filePath, Receipt receipt){
        try (FileOutputStream fos = new FileOutputStream(filePath);
             ObjectOutputStream outputStream = new ObjectOutputStream(fos);) {
            outputStream.writeObject(receipt);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Receipt deserialize(String filePath){
        Receipt receipt = null;
        try (FileInputStream fis = new FileInputStream(filePath);
             ObjectInputStream inputStream = new ObjectInputStream(fis);) {

            receipt = (Receipt) inputStream.readObject();

        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }

        return receipt;
    }
}
