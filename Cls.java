import java.io.IOException;

public class Cls {
    public static void cls() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } 
        catch (IOException | InterruptedException e) {
            System.out.println("Error clearing the screen: " + e.getMessage());
        }
    }
}
