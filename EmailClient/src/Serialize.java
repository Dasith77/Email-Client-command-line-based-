import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Serialize implements Serializable{
    public static void serial(HashMap<String, ArrayList<Email>> medallist) {
        //this method is use to serialize EmailList hashmap
        try {
            //File ser_objects = new File("serialized_email.ser");
            FileOutputStream a = new FileOutputStream("serialized_email.ser");
            ObjectOutputStream b = new ObjectOutputStream(a);
            b. writeObject(medallist);
            b.close();



        }
        catch (IOException e) {
            System.out.println(e);
        }

    }
    public static void serial_wishList(ArrayList<Recepients> ab){
        try{
            FileOutputStream c = new FileOutputStream("serialized_wishList.ser");
            ObjectOutputStream d = new ObjectOutputStream(c);
            d.writeObject(ab);
            d.close();


        }
        catch(IOException ee){
            System.out.println(ee);

        }


    }





}
