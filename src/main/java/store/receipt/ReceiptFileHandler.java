    package store.receipt;

    import customer.CartItem;
    import java.io.*;
    import java.math.BigDecimal;
    import java.nio.file.Files;
    import java.nio.file.Paths;
    import java.util.List;

    public class ReceiptFileHandler {
        public static void writeToFile(String fileName, Receipt receipt) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {

                writer.write("--------------------------------------------\n");
                writer.write("                RECEIPT\n");
                writer.write("--------------------------------------------\n");
                writer.write("Receipt ID: " + receipt.getId() + "\n");
                writer.write("Store: " + receipt.getStore().getStoreName() + "\n");
                writer.write("Issued by: " + receipt.getCashierIssuedBy().getName() + " #" + receipt.getCashierIssuedBy().getId() + "\n");
                writer.write(receipt.getIssuedOn() + "\n");
                writer.write("\n");
                writer.write("--------------------------------------------\n");
                writer.write("Items Purchased:\n");
                writer.write("--------------------------------------------\n");
                writer.write("ID     | Item Name          | Price   | Quantity | Total\n");
                writer.write("------------------------------------------------------\n");

                for (CartItem item : receipt.getBoughtItems()) {
                    writer.write(String.format("%-6s| %-18s| %-8.2f| %-9d| %.2f\n",
                            item.getCartItemProduct().getProduct().getId(),
                            item.getCartItemProduct().getProduct().getName(),
                            item.getCartItemProduct().getProduct().getSellPrice(),
                            item.getAmount(),
                            item.getCartItemProduct().getProduct().getSellPrice().multiply(BigDecimal.valueOf(item.getAmount()))));
                }

                writer.write("--------------------------------------------\n");
                writer.write("TOTAL:                                " + String.format("%.2f", receipt.getTotalPrice()) + "\n");
                writer.write("--------------------------------------------\n");
                writer.write("\nThank you for shopping with us!\n");
                writer.write("--------------------------------------------\n");
            } catch (IOException e) {
                System.out.println("Error writing receipt to file: " + e.getMessage());
            }
        }

        public static void readReceipt(String fileName) {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line;

                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                System.out.println("Error reading receipt from file: " + e.getMessage());
            }
        }

        public static void readAllTextFilesInFolder(String folderPath) {
            File folder = new File(folderPath);

            if (folder.exists() && folder.isDirectory()) {
                File[] files = folder.listFiles();

                if (files != null) {
                    for (File file : files) {
                        if (file.isFile() && file.getName().endsWith(".txt")) {
                            System.out.println("Reading file: " + file.getName());
                            try {
                                List<String> lines = Files.readAllLines(Paths.get(file.getAbsolutePath()));
                                for (String line : lines) {
                                    System.out.println(line);
                                }
                            } catch (IOException e) {
                                System.err.println("Error reading file " + file.getName() + ": " + e.getMessage());
                            }
                            System.out.println("-----");
                        }
                    }
                }
            } else {
                System.err.println("Invalid folder path: " + folderPath);
            }

        }

        public static void deleteAllTxtFiles(String folderPath) {
            File folder = new File(folderPath);

            if (!folder.exists() || !folder.isDirectory()) {
                System.out.println("The provided path is not a valid directory.");
                return;
            }

            File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));

            if (files == null || files.length == 0) {
                System.out.println("No .txt files found in the directory.");
                return;
            }

            for (File file : files) {
                if (file.delete()) {
                    System.out.println("Deleted: " + file.getName());
                } else {
                    System.out.println("Failed to delete: " + file.getName());
                }
            }
        }
    }
