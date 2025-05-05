package store.receipt;

public interface ReceiptSerializationDeserializationService {
    void serialize(String filePath, Receipt receipt);
    Receipt deserialize(String filePath);
}
