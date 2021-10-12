package sv.udb.edu.catedraframeworks.utils;

import java.util.Random;

public class RamdomString {

    public String password() {
        String password = "";
        Random r = new Random();

        String alphabet = "abcdefghijklmnopqrstuvwxyz"
                + "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        for (int i = 0; i < 8; i++) {
            password += (alphabet.charAt(r.nextInt(alphabet.length())));
        }

        return password;
    }

    public String codigoCita() {
        String password = "";
        Random r = new Random();

        String alphabet = "abcdefghijklmnopqrstuvwxyz"
                + "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        for (int i = 0; i < 5; i++) {
            password += (alphabet.charAt(r.nextInt(alphabet.length())));
        }

        return password;
    }


}
