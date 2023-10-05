public class Personal extends Recepients  {
    private String NickName;
    private String Birthday;
    private String Email;
    private String Nickname;
    private String designation;

    public Personal(String Name, String NickName, String Email, String Birthday){
        super(Name,Email,NickName,Birthday,null);
        this.NickName=NickName;
        this.Birthday=Birthday;
        this.designation=null;
        this.Email=Email;


    }

    @Override
    public String getBirthday() {
        return Birthday;
    }

    public String getEmail(){
        return Email;
    }
    public String getDesignation(){
        return designation;}



}
