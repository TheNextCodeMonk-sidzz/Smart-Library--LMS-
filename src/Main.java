/******************** Importing Essential Libraries ************************/

import java.util.*;
import java.io.*;

class MainMenu {
    public void menu() {
        System.out.println("**************************************************************************************");
        System.out.println("\t\t\t\t\t  Smart Library :) ");

        System.out.println("\t\t\t-----------------------------------------");
        System.out.println("\t\t\t\t Created By : Siddhant Bhujbal ");
        System.out.println("\t\t\t-----------------------------------------");
        System.out.println("\t  Its time to Manage Library more Efficiently and Conveniently ");
        System.out.println("\t\t\t-----------------------------------------");

        System.out.println("\n\nPress 1 : To Add a Book");
        System.out.println("Press 2 : To View Book Details");
        System.out.println("Press 3 : To Remove a Book");
        System.out.println("Press 4 : To Update Book Details");
        System.out.println("Press 5 : To Issue a Book");
        System.out.println("Press 6 : To Return a Book");
        System.out.println("Press 7 : To View All Books");
        System.out.println("Press 8 : To View All Transactions");
        System.out.println("Press 9 : To Exit the Library Portal");
    }
}

class Book_Add {
    public void createFile() {
        Scanner sc = new Scanner(System.in);

        BookDetail book = new BookDetail();
        book.getInfo();

        try {
            File f1 = new File("book" + book.book_id + ".txt");
            if (f1.createNewFile()) {
                FileWriter myWriter = new FileWriter("book" + book.book_id + ".txt");
                myWriter.write("Book ID       : " + book.book_id + "\n" +
                        "Book Title    : " + book.title + "\n" +
                        "Author Name   : " + book.author + "\n" +
                        "Genre         : " + book.genre + "\n" +
                        "Publisher     : " + book.publisher + "\n" +
                        "Publication Year : " + book.pub_year + "\n" +
                        "Total Copies  : " + book.total_copies + "\n" +
                        "Available Copies : " + book.available_copies + "\n" +
                        "Status        : Available");
                myWriter.close();
                System.out.println("\nBook has been Added Successfully :)\n");

                System.out.print("\nPress Enter to Continue...");
                sc.nextLine();
            } else {
                System.out.println("\nBook with this ID already exists :(");
                System.out.print("\nPress Enter to Continue...");
                sc.nextLine();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

class BookDetail {
    String title;
    String author;
    String genre;
    String publisher;
    String book_id;
    String pub_year;
    String total_copies;
    String available_copies;

    public void getInfo() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Book ID ---------------: ");
        book_id = sc.nextLine().trim();
        System.out.print("Enter Book Title ------------: ");
        title = sc.nextLine().trim();
        System.out.print("Enter Author Name -----------: ");
        author = sc.nextLine().trim();
        System.out.print("Enter Genre -----------------: ");
        genre = sc.nextLine().trim();
        System.out.print("Enter Publisher -------------: ");
        publisher = sc.nextLine().trim();
        System.out.print("Enter Publication Year ------: ");
        pub_year = sc.nextLine().trim();
        System.out.print("Enter Total Copies ----------: ");
        total_copies = sc.nextLine().trim();
        available_copies = total_copies; // Initially all copies are available
    }
}

class Book_Show {
    public void viewFile(String s) {
        try {
            File file = new File("book" + s + ".txt");
            if (!file.exists()) {
                System.out.println("\nBook with ID '" + s + "' does not exist :(");
                return;
            }

            Scanner sc = new Scanner(file);
            System.out.println("\n--- Book Details ---");
            while (sc.hasNextLine()) {
                System.out.println(sc.nextLine());
            }
            sc.close();
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public void viewAllBooks() {
        File currentDir = new File(".");
        File[] files = currentDir.listFiles();
        boolean foundBooks = false;

        System.out.println("\n========== ALL BOOKS IN LIBRARY ==========");
        System.out.println("-------------------------------------------");

        if (files != null) {
            for (File file : files) {
                if (file.getName().startsWith("book") && file.getName().endsWith(".txt")) {
                    foundBooks = true;
                    try {
                        Scanner sc = new Scanner(file);
                        System.out.println("\n📚 " + file.getName().toUpperCase() + ":");
                        System.out.println("-------------------");
                        while (sc.hasNextLine()) {
                            System.out.println(sc.nextLine());
                        }
                        sc.close();
                        System.out.println("-------------------------------------------");
                    } catch (Exception e) {
                        System.out.println("Error reading " + file.getName() + ": " + e.getMessage());
                    }
                }
            }
        }

        if (!foundBooks) {
            System.out.println("📭 No books found in the library!");
            System.out.println("Add some books first using option 1.");
        }

        System.out.println("=========================================");
    }
}

class Book_Remove {
    public void removeFile(String ID) {
        File file = new File("book" + ID + ".txt");
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("\nBook has been removed Successfully");
            } else {
                System.out.println("\nFailed to remove the book");
            }
        } else {
            System.out.println("\nBook does not exist :(");
        }
    }
}

class Book_Update {
    public void updateFile(String s, String o, String n) {
        try {
            File file = new File("book" + s + ".txt");
            if (!file.exists()) {
                System.out.println("\nBook with ID '" + s + "' does not exist :(");
                return;
            }

            Scanner sc = new Scanner(file);
            String fileContext = "";
            while (sc.hasNextLine()) {
                fileContext = fileContext + sc.nextLine() + "\n";
            }
            sc.close();

            FileWriter myWriter = new FileWriter("book" + s + ".txt");
            fileContext = fileContext.replaceAll(o, n);
            myWriter.write(fileContext);
            myWriter.close();

            System.out.println("\nBook details updated successfully!");

        } catch (IOException e) {
            System.out.println("Error updating file: " + e.getMessage());
        }
    }
}

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

class SystemExit {
    public void out() {
        System.out.println("\n*****************************************");
        System.out.println("$ Thank you for using Smart Library!");
        System.out.println("*****************************************");
        System.out.println("\t\t~ Have a Great Day! \n");
        System.exit(0);
    }
}

class LibraryManagementSystem {
    public static void main(String args[]) {
        // Clear screen
        System.out.print("\033[H\033[2J");

        Scanner sc = new Scanner(System.in);
        Book_Show bookView = new Book_Show();
        Book_Transaction transaction = new Book_Transaction();

        int choice = 0;

        // Display main menu
        MainMenu menu = new MainMenu();
        menu.menu();

        // Main program loop
        while (choice != 9) {
            System.out.print("\nPlease Enter your choice: ");
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
                continue;
            }

            switch (choice) {
                case 1: {
                    // Add Book
                    Book_Add bookAdd = new Book_Add();
                    bookAdd.createFile();

                    System.out.print("\033[H\033[2J");
                    menu.menu();
                    break;
                }

                case 2: {
                    // View Book
                    System.out.print("\nPlease Enter Book ID: ");
                    String bookId = sc.nextLine().trim();
                    bookView.viewFile(bookId);

                    System.out.print("\nPress Enter to Continue...");
                    sc.nextLine();
                    System.out.print("\033[H\033[2J");
                    menu.menu();
                    break;
                }

                case 3: {
                    // Remove Book
                    System.out.print("\nPlease Enter Book ID to Remove: ");
                    String bookId = sc.nextLine().trim();
                    Book_Remove bookRemove = new Book_Remove();
                    bookRemove.removeFile(bookId);

                    System.out.print("\nPress Enter to Continue...");
                    sc.nextLine();
                    System.out.print("\033[H\033[2J");
                    menu.menu();
                    break;
                }

                case 4: {
                    // Update Book
                    System.out.print("\nPlease Enter Book ID: ");
                    String bookId = sc.nextLine().trim();

                    bookView.viewFile(bookId);

                    Book_Update bookUpdate = new Book_Update();
                    System.out.println("\nPlease Enter the detail you want to Update:");
                    System.out.println("For Example:");
                    System.out.println("To change the Title, enter current title and press Enter.");
                    System.out.println("Then enter the new title and press Enter.\n");

                    System.out.print("Enter current value: ");
                    String oldValue = sc.nextLine().trim();
                    System.out.print("Enter new value: ");
                    String newValue = sc.nextLine().trim();

                    bookUpdate.updateFile(bookId, oldValue, newValue);

                    System.out.print("\nPress Enter to Continue...");
                    sc.nextLine();
                    System.out.print("\033[H\033[2J");
                    menu.menu();
                    break;
                }

                case 5: {
                    // Issue Book
                    System.out.print("\nPlease Enter Book ID to Issue: ");
                    String bookId = sc.nextLine().trim();
                    System.out.print("Enter Member Name: ");
                    String memberName = sc.nextLine().trim();
                    System.out.print("Enter Member Contact: ");
                    String memberContact = sc.nextLine().trim();

                    transaction.issueBook(bookId, memberName, memberContact);

                    System.out.print("\nPress Enter to Continue...");
                    sc.nextLine();
                    System.out.print("\033[H\033[2J");
                    menu.menu();
                    break;
                }

                case 6: {
                    // Return Book
                    System.out.print("\nPlease Enter Book ID to Return: ");
                    String bookId = sc.nextLine().trim();
                    System.out.print("Enter Member Name: ");
                    String memberName = sc.nextLine().trim();

                    transaction.returnBook(bookId, memberName);

                    System.out.print("\nPress Enter to Continue...");
                    sc.nextLine();
                    System.out.print("\033[H\033[2J");
                    menu.menu();
                    break;
                }

                case 7: {
                    // View All Books
                    bookView.viewAllBooks();

                    System.out.print("\nPress Enter to Continue...");
                    sc.nextLine();
                    System.out.print("\033[H\033[2J");
                    menu.menu();
                    break;
                }

                case 8: {
                    // View All Transactions
                    transaction.viewAllTransactions();

                    System.out.print("\nPress Enter to Continue...");
                    sc.nextLine();
                    System.out.print("\033[H\033[2J");
                    menu.menu();
                    break;
                }

                case 9: {
                    // Exit
                    SystemExit exit = new SystemExit();
                    exit.out();
                    break;
                }

                default: {
                    System.out.println("\nPlease enter a valid choice (1-9)!");
                    break;
                }
            }
        }

        sc.close();
    }
}