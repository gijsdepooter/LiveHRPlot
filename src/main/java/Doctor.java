public class Doctor {
    private int doctorID;
    private String userName;
    private String password;
    private Boolean authenticate;

    Doctor(String userName, String password){
        this.userName = userName;
        this.password = password;
    }

    public int getDoctorID(){
        return doctorID;
    }

    public String getUserName(){
        return userName;
    }

    public String getPassword(){
        return password;
    }





}
