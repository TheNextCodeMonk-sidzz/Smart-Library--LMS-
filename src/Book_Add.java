import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Book_Add {
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

