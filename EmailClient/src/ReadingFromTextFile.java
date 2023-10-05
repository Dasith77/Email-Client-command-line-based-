import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;



public class ReadingFromTextFile {
    //this class is used to get person names who have birthdays on entered date

    public static void getBirthdays(String month,String dat) {

        int x =0;
        try {
            File obj = new File("clientList.txt");
            Scanner sc = new Scanner(obj);

            while (sc.hasNextLine()) {
                String strLine = sc.nextLine();
                String[] b = strLine.split(":");
                String pos = b[0].strip();
                if ((Objects.equals(pos, "Official friends")) || (Objects.equals(pos, "Personal"))) {
                    String other = b[1].strip();
                    String[] details = other.split(",");
                    //int x = details.length;
                    String[] date = details[details.length - 1].split("/");
                    //System.out.println(date);

                    if ((Objects.equals(date[1], month)) && (Objects.equals(date[2], dat))) {
                        System.out.println(details[0]);
                        x++;
                    }
                }

            }
            if(x==0){
                System.out.println("There are no any birthdays today.");
            }
            sc.close();
        }

         catch (FileNotFoundException e) {
            System.out.println("An error occured");
            //throw new RuntimeException(e);
        }





    }



}