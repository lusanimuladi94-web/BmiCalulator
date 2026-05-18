


import java.io.*;
import java.util.*;

public class AuthService {

    private final File file = new File("users.txt");

    public boolean login(String user, String pass) {
        try (Scanner sc = new Scanner(file)) {

            while (sc.hasNextLine()) {
                String[] parts = sc.nextLine().split(",");

                if (parts[0].equals(user) && parts[1].equals(pass)) {
                    return true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean register(String user, String pass) {
        try (FileWriter fw = new FileWriter(file, true)) {

            fw.write(user + "," + pass + "\n");
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}