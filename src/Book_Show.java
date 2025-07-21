import java.io.File;
import java.util.Scanner;

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
