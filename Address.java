import java.util.Scanner;

public class Address {
    private int houseNumber;
    private String streetName;
    private String streetType;
    private String city;
    private String state;
    private int zip;

    public Address(){
        this(000, "","", "","", 00000);
    }
    public Address(int houseNumber, String streetName, String streetType, String city, String state, int zip){
        this.houseNumber= houseNumber;
        this.streetName =  streetName;
        this.streetType = streetType;
        this.city= city;
        this.state= state;
        this.zip= zip;
    }

    public String toString(){
        return houseNumber + " "+ streetName+ " "+ streetType +"\n"+ city + ", " + state + " "+ zip; 
    }

    public static Address read(Scanner sc){
        if(sc.hasNext()){
             return new Address(sc.nextInt(), sc.next(), sc.next(), sc.next(), sc.next(), sc.nextInt());
            
        }
        return null;
    }

}
