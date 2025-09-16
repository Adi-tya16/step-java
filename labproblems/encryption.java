package week2.labproblems;

import java.util.Scanner;

public class encryption {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str = sc.nextLine();
        int shift = sc.nextInt();

        String encrypted = encrypt(str.toCharArray(), shift);
        System.out.println("Encrypted: " + encrypted);

        String decrypted = decrypt(encrypted.toCharArray(), shift);
        System.out.println("Decrypted: " + decrypted);
    }


    public static String encrypt(char[] arr, int shift) {
        int[] newarr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (('A' <= arr[i] && arr[i] <= 'Z') || ('a' <= arr[i] && arr[i] <= 'z')) {
                int ascii = (int) arr[i];
                newarr[i] = ascii + shift;
            } else {
                newarr[i] = arr[i];
            }
        }


        StringBuilder sb = new StringBuilder();
        for (int val : newarr) {
            sb.append((char) val);
        }
        return sb.toString();
    }

    public static String decrypt(char[] arr, int shift) {
        int[] newarr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (('A' <= arr[i] && arr[i] <= 'Z') || ('a' <= arr[i] && arr[i] <= 'z')) {
                int ascii = (int) arr[i];
                newarr[i] = ascii - shift;
            } else {
                newarr[i] = arr[i];
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int val : newarr) {
            sb.append((char) val);
        }
        return sb.toString();
    }
}
