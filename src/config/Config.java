package config;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import static config.Color.*;

public class Config<T> {

    public static Scanner scanner() {
        return new Scanner(System.in);
    }
    public T read(String path) {
        T data = null;
        try (
                FileInputStream fis = new FileInputStream(path);
                ObjectInputStream ois = new ObjectInputStream(fis);
        ) {
            data = (T) ois.readObject();
        } catch (Exception e) {
            System.out.println(RED+"        Read error!!!"+RESET);
        }
        return data;
    }

    public void write(String path, T data) {
        try (
                FileOutputStream fos = new FileOutputStream(path);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
        ) {
            oos.writeObject(data);
        } catch (Exception e) {
            System.out.println(RED+"        Write error!!!"+RESET);
        }
    }

    public static int getValidInteger() {
        int integer;
        while (true) {
            System.out.print("     Enter choice : ");
            String s = scanner().nextLine();
            if (s.matches("\\d+")) {
                integer = Integer.parseInt(s);
                break;
            } else {
                System.out.println(RED+"      Invalid number !!!"+RESET);
            }
        }
        return integer;
    }

}
