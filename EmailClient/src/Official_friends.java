public class Official_friends extends Officials {
    private String birthday;
    private String email;
    private String designation;
    private String name;


    public Official_friends(String name, String email, String designation,String birthday) {
        super(name, email,designation);
        this.birthday=birthday;
        this.email=email;
        this.designation=designation;
        this.name=name;
    }


    @Override
    public String getBirthday() {
        return birthday;
    }
    public String getEmail(){
        return email;
    }
    public String getDesignation(){
        return designation;}



}
