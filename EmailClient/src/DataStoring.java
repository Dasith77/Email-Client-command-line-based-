import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class DataStoring {
    //this class is use to write recepients details and wishlist into text files
    public void datastore(String data) throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter(new File("clientList.txt"), true));
        out.write(data+"\n");
        out.close();

    }
    public void ListStore(ArrayList<Recepients> WishAndRecipient) throws IOException {
        BufferedWriter out1 = new BufferedWriter(new FileWriter(new File("WishList_&_RecipientList.txt"),true));
        out1.write(WishAndRecipient+"\n");
        out1.close();

    }



}
