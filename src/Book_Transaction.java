import java.io.File;
import java.io.FileWriter;
import java.util.Date;
import java.util.Scanner;

class Book_Transaction {
    public void issueBook(String bookId, String memberName, String memberContact) {
        try {
            File file = new File("book" + bookId + ".txt");
            if (!file.exists()) {
                System.out.println("\nBook with ID '" + bookId + "' does not exist :(");
                return;
            }

            Scanner sc = new Scanner(file);
            String fileContent = "";
            boolean canIssue = false;

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.contains("Available Copies : ")) {
                    String[] parts = line.split(": ");
                    if (parts.length > 1) {
                        try {
                            int availableCopies = Integer.parseInt(parts[1].trim());
                            if (availableCopies > 0) {
                                availableCopies--;
                                line = "Available Copies : " + availableCopies;
                                canIssue = true;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Error reading available copies");
                        }
                    }
                }
                if (line.contains("Status        : Available") && canIssue) {
                    line = "Status        : Issued to " + memberName;
                }
                fileContent += line + "\n";
            }
            sc.close();

            if (canIssue) {
                FileWriter myWriter = new FileWriter("book" + bookId + ".txt");
                myWriter.write(fileContent);
                myWriter.close();

                // Create transaction record
                createTransactionRecord(bookId, memberName, memberContact, "ISSUED");
                System.out.println("\nBook issued successfully to " + memberName + "!");
            } else {
                System.out.println("\nSorry, no copies available for this book :(");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void returnBook(String bookId, String memberName) {
        try {
            File file = new File("book" + bookId + ".txt");
            if (!file.exists()) {
                System.out.println("\nBook with ID '" + bookId + "' does not exist :(");
                return;
            }

            Scanner sc = new Scanner(file);
            String fileContent = "";
            boolean bookReturned = false;

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.contains("Available Copies : ")) {
                    String[] parts = line.split(": ");
                    if (parts.length > 1) {
                        try {
                            int availableCopies = Integer.parseInt(parts[1].trim());
                            availableCopies++;
                            line = "Available Copies : " + availableCopies;
                            bookReturned = true;
                        } catch (NumberFormatException e) {
                            System.out.println("Error reading available copies");
                        }
                    }
                }
                if (line.contains("Status        : Issued to ") && bookReturned) {
                    line = "Status        : Available";
                }
                fileContent += line + "\n";
            }
            sc.close();

            if (bookReturned) {
                FileWriter myWriter = new FileWriter("book" + bookId + ".txt");
                myWriter.write(fileContent);
                myWriter.close();

                // Create transaction record
                createTransactionRecord(bookId, memberName, "", "RETURNED");
                System.out.println("\nBook returned successfully by " + memberName + "!");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void viewAllTransactions() {
        try {
            File transFile = new File("transactions.txt");
            if (!transFile.exists()) {
                System.out.println("\n📋 No transactions found!");
                System.out.println("Issue or return some books first to see transaction history.");
                return;
            }

            Scanner sc = new Scanner(transFile);
            System.out.println("\n========== TRANSACTION HISTORY ==========");
            System.out.println("------------------------------------------");

            boolean hasTransactions = false;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (!line.trim().isEmpty()) {
                    System.out.println("📝 " + line);
                    hasTransactions = true;
                }
            }
            sc.close();

            if (!hasTransactions) {
                System.out.println("📋 No transactions recorded yet!");
            }

            System.out.println("==========================================");

        } catch (Exception e) {
            System.out.println("Error reading transactions: " + e.getMessage());
        }
    }

    private void createTransactionRecord(String bookId, String memberName, String memberContact, String action) {
        try {
            FileWriter transWriter = new FileWriter("transactions.txt", true);
            Date date = new Date();
            String record = "Date: " + date + " | Book ID: " + bookId + " | Member: " + memberName;
            if (!memberContact.isEmpty()) {
                record += " | Contact: " + memberContact;
            }
            record += " | Action: " + action + "\n";
            transWriter.write(record);
            transWriter.close();
        } catch (Exception e) {
            System.out.println("Error creating transaction record: " + e.getMessage());
        }
    }
}
