import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

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
