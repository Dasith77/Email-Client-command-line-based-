

import java.util.Objects;

public class AddToArray {
    //this class is used to create respective objects

    public static Recepients addToArray(String recipients) {
        String[] split = recipients.split(":");
        String a = split[0].trim();
        String b = split[1].trim();
        //System.out.println(b);
        String[] split2 = b.split(",");

      
        if (Objects.equals(a, "Officials")) {
            return new Officials(split2[0], split2[1], split2[2]);
        }

        if (Objects.equals(a, "Official friends")) {
            return new Official_friends(split2[0], split2[1], split2[2], split2[3]);

        }

        if (Objects.equals(a, "Personal")) {
             return new Personal(split2[0], split2[1], split2[2], split2[3]);



        }

        return null;
    }
}