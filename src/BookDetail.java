import java.util.Scanner;

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
