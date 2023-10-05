import javax.mail.SendFailedException;
import java.io.*;
import java.util.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.HashMap;

public class Email_Client{

        static ArrayList<Recepients> recipients_array = new ArrayList<>();   //use to keep records of all recepients objects
        static ArrayList<Recepients> wishtoArray = new ArrayList<>(); //use to keep records of all Personal and Official friends objects
        static ArrayList<Email> sentEmails = new ArrayList<>(); //use to keep records of Email objects which are sent within a day

        public static HashMap<String,ArrayList<Email>> EmailList = new HashMap<>(); //use to keep records of all details of all emails

        public static boolean checkWishable =true;

        public static int num_of_reccipients; //use to keep record of number of recipients

        //Here deserialize serialized_email.ser and get EmailList hashmap and assign it to newly created empty EmailList hashmap
        @SuppressWarnings("unchecked")

        public static void deserialization() throws IOException, ClassNotFoundException {
            //Creating stream to read the object
            LocalDate now2 = LocalDate.now();
            DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            String formattedDate2 = now2.format(dtf2);

            FileInputStream saving = null;
            try {
                saving = new FileInputStream("serialized_email.ser");
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            ObjectInputStream in = null;
            try {
                in = new ObjectInputStream(saving);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            //casting deserialized object into Hashmap
            EmailList = (HashMap<String ,ArrayList<Email>> )in.readObject();
            in.close();
            saving.close();
            if(EmailList.containsKey(formattedDate2)){
                sentEmails=EmailList.get(formattedDate2);
            }

        }
        //deserializing birthday wish list

/* ------------------------------------------------------------------------------------------------------------------------------------- */





        public static void main(String[] args) throws IOException, ClassNotFoundException, SendFailedException {

            //in here deserialize the EmailList hashmap
            try {
                deserialization();
            } catch (RuntimeException yy) {
                System.out.println("RuntimeException");
            }
            /*----------------------------------------------------------------------------------------------------------*/


            LocalDate now4 = LocalDate.now();
            DateTimeFormatter dtf4 = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            String formattedDate3 = now4.format(dtf4);

            //read from clientList.txt file and put recipients details into wishtoArray and recipients_array respectively
            try {
                File ok = new File("clientList.txt");

                Scanner ac = new Scanner(ok);
                while (ac.hasNextLine()) {
                    String strLine = ac.nextLine();
                    String[] b1 = strLine.split(":");
                    String position = b1[0].strip();
                    if (Objects.equals(position, "Official friends") || Objects.equals(position, "Personal")) {
                        Recepients r = AddToArray.addToArray(strLine);
                        wishtoArray.add(r);
                    }
                    Recepients r = AddToArray.addToArray(strLine);
                    recipients_array.add(r);

                }
                num_of_reccipients=recipients_array.size();
            }
            catch(FileNotFoundException err){
                num_of_reccipients=0;
                System.out.println("Not yet created this clientList.txt file !!!");
            }
            catch(ArrayIndexOutOfBoundsException cr){
                num_of_reccipients=0;
                System.out.println("clientList.txt file is empty!!!");
            }
            /*----------------------------------------------------------------------------------------------------------*/

            //here check whether birthday wishes are already sent or not
            for(Email d : sentEmails){
                if(Objects.equals(d.getEmailSubject(), "Birthday Wish") && Objects.equals(d.getDay(), formattedDate3)){
                    checkWishable =false;
                }
            }
            if(checkWishable) {
                for (Recepients r : wishtoArray) {
                    String[] temp_Bday = r.getBirthday().split("/");
                    String[] date_format = formattedDate3.split("/");
                    if ((Objects.equals(temp_Bday[1], date_format[1])) && (Objects.equals(temp_Bday[2], date_format[2]))) {

                        String mailAdress = r.getEmail();
                        if (r.getDesignation() == null) {
                            String tempMail = r.getEmail();
                            SendEmailTLS.sendEmail(tempMail, "Birthday Wish", "hugs and love on your birthday. <Dasith>");
                            Email e1 = new Email(tempMail, "Birthday Wish", "hugs and love on your birthday. <Dasith>", formattedDate3);
                            sentEmails.add(e1);
                        } else {
                            String tempMail2 = r.getEmail();
                            SendEmailTLS.sendEmail(tempMail2, "Birthday Wish", "Wish you a Happy Birthday. <Dasith>");
                            Email e2 = new Email(tempMail2, "Birthday Wish", "Wish you a Happy Birthday. <Dasith>", formattedDate3);
                            sentEmails.add(e2);
                        }

                    }

                }
            }
            /*---------------------------------------------------------------------------------------------------------------------------------------------*/


            //here start to run main program while it ends by entering -1

            try{
                boolean flag = true;

                while(flag){
                System.out.println("""
                    Enter option type:\s
                    1 - Adding a new recipient
                    2 - Sending an email
                    3 - Printing out all the recipients who have birthdays in entered date
                    4 - Printing out details of all the emails sent in entered date
                    5 - Printing out the number of recipient objects in the application
                    -1 - To close the entire application """);

            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();
            /*====================================================================================================================*/
            switch(option){

                case 1:
                    // input format - Official: nimal,nimal@gmail.com,ceo
                        try {
                            Scanner scanner1 = new Scanner(System.in);
                            System.out.println("""
                                    __________input format______________
                                    Official: nimal,nimal@gmail.com,ceo
                                    Official friends : Kamal,kamal@gmail.com,ceo,1999/10/10
                                    Personal : Amal,Bobi,amal@gmail.com,1999/09/14""");
                            String recipients = scanner1.nextLine();
                            Recepients res = AddToArray.addToArray(recipients);
                            recipients_array.add(res);
                            if (!(res instanceof Officials)) {
                                wishtoArray.add(res);
                            }
                            num_of_reccipients = num_of_reccipients + 1;

                            // Use a single input to get all the details of a recipient
                            // code to add a new recipient
                            // store details in clientList.txt file
                            DataStoring d = new DataStoring();
                            d.datastore(recipients);
                        }
                        catch(ArrayIndexOutOfBoundsException ok){
                            System.out.println("Your input is wrong or incomplete!!!");
                        }
                        // Hint: use methods for reading and writing files
                        break;


                /*====================================================================================================================*/
                case 2:
                    try {
                        System.out.println("enter email address of recipient : ");
                        Scanner scanner2 = new Scanner(System.in);
                        String emailAddress = scanner2.nextLine();

                        System.out.println("enter email subject : ");
                        Scanner scanner3 = new Scanner(System.in);
                        String EmailSubject = scanner3.nextLine();

                        System.out.println("enter email content : ");
                        Scanner scanner4 = new Scanner(System.in);
                        String EmailContent = scanner4.nextLine();



                        LocalDate now = LocalDate.now();
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                        String formattedDate = now.format(dtf);


                        Email e =  new Email(emailAddress,EmailSubject,EmailContent,formattedDate);
                        sentEmails.add(e);

                        //SendEmailTLS ss= new SendEmailTLS(emailAddress,EmailSubject,EmailContent);
                        SendEmailTLS.sendEmail(emailAddress, EmailSubject, EmailContent);

                        //e.sendEmail();//sending email
                    }
                    catch(IOError i){
                        System.out.println("Invalid input!!!");
                    }
                    break;
                /*====================================================================================================================*/
                case 3:
                    // input format - yyyy/MM/dd (ex: 2018/09/17)
                    try {
                        Scanner scanner5 = new Scanner(System.in);
                        System.out.println("Enter day with yr/mo/date format to check recipients who have birthdays ");
                        String date = scanner5.nextLine();
                        String[] a = date.strip().split("/");

                        ReadingFromTextFile.getBirthdays(a[1], a[2]);
                    }
                    catch(IOError p){
                        System.out.println("invalid input try again!!!");
                    }
                    catch(ArrayIndexOutOfBoundsException v){
                        System.out.println("invalid input try again with correct input3 !!!");
                    }
                    break;
                /*====================================================================================================================*/
                case 4:
                    // code to print the details of all the emails sent on the input date
                    // input format - yyyy/MM/dd (ex: 2018/09/17)
                    try {
                        Scanner scanner6 = new Scanner(System.in);
                        System.out.println("Enter date(yyyy/MM/dd (ex: 2018/09/17)) which need to display sent emails...");
                        String sentDate = scanner6.nextLine();

                        boolean check = EmailList.containsKey(sentDate);

                        if (check) {
                            ArrayList<Email> ab;
                            ab = EmailList.get(sentDate);
                            for (int y = 0; y < ab.size(); y++) {
                                System.out.println("this is email address of " + y + " email: \n" + "    "+ab.get(y).getEmailAddress());
                                System.out.println("this is subject of " + y + " email: \n" + "    "+ab.get(y).getEmailSubject());
                                System.out.println("this is content of " + y + " email: \n" +"    "+ ab.get(y).getEmailContent());
                                System.out.println();
                            }
                        }
                        else{
                            System.out.println("There is no any Email sent on "+sentDate);
                        }
                    }
                    catch(IndexOutOfBoundsException h){
                        System.out.println("Error!!!");
                    }

                    break;
                /*====================================================================================================================*/
                case 5:
                    // code to print the number of recipient objects in the application
                    System.out.println("Total Number of Recipients is = "+num_of_reccipients);

                    break;
                /*====================================================================================================================*/
                case -1:
                    //close the programme
                    //serializing all details of sent emails as a hashmap
                    LocalDate now1 = LocalDate.now();
                    DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                    String formattedDate1 = now1.format(dtf1);
                    //System.out.println(formattedDate1);
                    EmailList.put(formattedDate1,sentEmails);

                    //putting details of sent emails to  EmailList hashmap
                    Serialize.serial(EmailList); //serialize EmailList hashmap


                    DataStoring data1 = new DataStoring();
                    data1.ListStore(recipients_array);
                    data1.ListStore(wishtoArray);

                    System.out.println("System is closed...-");
                    flag=false;
                    break;
                }


                }
         }
            catch(IOException e){
                System.out.println("exception occured.");
            }


        }
    }



