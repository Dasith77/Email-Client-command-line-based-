public abstract class Recepients {
    public String Name;
    public String Email;
    public String NickName;
    public String Birthday;
    public String designation;

    public Recepients(String Name, String Email,String NickName,String Birthday,String designation) {

        this.NickName = NickName;
        this.Birthday = Birthday;
        this.Name = Name;
        this.Email = Email;
        this.designation = designation;

    }
    //public String getName(){
        //return Name;

    public String getEmail(){
        return Email;
    }

    public String getBirthday(){
        return Birthday;}

    public String getDesignation(){
        return designation;}


}
