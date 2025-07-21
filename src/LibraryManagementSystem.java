import java.util.Scanner;

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
