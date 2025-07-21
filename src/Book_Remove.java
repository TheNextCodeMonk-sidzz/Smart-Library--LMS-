import java.io.File;

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
