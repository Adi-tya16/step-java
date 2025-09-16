package week2.labproblems;


import java.util.Scanner;
public class StringTypes {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String s;
        StringBuilder s1 = new StringBuilder();
        StringBuffer s2 = new StringBuffer();
        System.out.println("Choose a method: 1. String  2. StringBuilder  3. StringBuffer ");
        int n = scan.nextInt();
        switch (n) {
            case 1:
                long start = System.currentTimeMillis();
                String str1 ="";
                s = scan.nextLine();
                while(!s.equals("end")){
                s = scan.nextLine();
                str1 += s;
            }
                long end = System.currentTimeMillis();
                System.out.println("Time:" +(end-start));
                System.out.println(str1);
        break;
        case 2:
            long start1 = System.currentTimeMillis();

            String sl = scan.nextLine();
            while(!sl.equals("end")){
                sl = scan.nextLine();
                s1.append(sl);
            }
            long end1 = System.currentTimeMillis();
            System.out.println("Time:" +(end1-start1));
            System.out.println(s1);
            break;

            case 3:
                long start2 = System.currentTimeMillis();

                String sl1 = scan.nextLine();
                while(!sl1.equals("end")){
                    sl1 = scan.nextLine();
                    s2.append(sl1);
                }
                long end2 = System.currentTimeMillis();
                System.out.println("Time:" +(end2-start2));
                System.out.println(s2);
                break;


        }

        scan.close();

            }

        }

