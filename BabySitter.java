import java.util.Scanner;

public class BabySitter implements Comparable<BabySitter> {
    private int employeeNumber;
    private String lastName;
    private String firstName;
    private Address address;
    private double hourlyRateBeforeNinePm;
    private double hourlyRateBetwNinePmMid;
    private double hourlyRateAfterMid;
    private int daysWorked;
    private int employeesNumber;// get rid of this
    private String startTime;
    private String endTime;
    private int hours;
    private int minutes;
    private double payAmount;

    public BabySitter() {
        this(0000, "", "", new Address(), 0, 0, 0, 0, 0);
    }

    public BabySitter(int employeeNumber, String lastName, String firstName, Address address,
            double hourlyRateBeforeNinePm, double hourlyRateBetwNinePmMid, double hourlyRateAfterMid,
            int employeesNumber, int daysWorked) {
        this.employeeNumber = employeeNumber;
        this.lastName = lastName;
        this.firstName = firstName;
        this.address = address;
        this.hourlyRateBeforeNinePm = hourlyRateBeforeNinePm;
        this.hourlyRateBetwNinePmMid = hourlyRateBetwNinePmMid;
        this.hourlyRateAfterMid = hourlyRateAfterMid;
        this.employeesNumber = employeesNumber;
        this.daysWorked = daysWorked;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return lastName + " " + firstName;
    }

    public Address getAddress() {
        return address;
    }

    public double getHourlyRateBeforeNinePm() {
        return hourlyRateBeforeNinePm;
    }

    public double getHourlyRateBetwNinePmMid() {
        return hourlyRateBetwNinePmMid;
    }

    public double getHourlyRateAfterMid() {
        return hourlyRateAfterMid;
    }

    public int getDaysWorked() {
        return daysWorked;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    /** SETTER and GETTER FOR THE PAYMENT AMOUNT */
    public void setPayAmount(double payAmount) {
        this.payAmount = payAmount;
    }

    public double getPayment() {
        return payAmount;
    }

    /** */
    public String toString() {
        return "Employee Number: " + getEmployeeNumber() + "\n" + getFullName() + "\n" + getAddress() + "\n";
    }

    // This method is used to sort the ArrayList alphabetically by last name
    // uses the compareTo() of the String class to compare the last name String

    @Override
    public int compareTo(BabySitter other) {
        if (this.getLastName().compareTo(other.getLastName()) > 0) {
            return 1;
        } else if (this.getLastName().compareTo(other.getLastName()) < 0) {
            return -1;
        }
        return 0;
    }

    // this method takes a string of time in the form 9:45 and separates the hours
    // (9) from the minutes (45) by using a delimiter
    // which recognizes the ':' as the separator between the hours and minutes
    // if the minutes are not = 00 the minutes are converted to a double in the form
    // of minutes/60
    // this method returns the Hours and minutes in the form of a double aka 9:30
    // would convert to 9.5

    public double makeHoursAndMinutes(String time) {
        Scanner readIn = new Scanner(time);
        readIn.useDelimiter(":");// uses a delimiter to deliminate the minutes from the hours
        while (readIn.hasNext()) {
            hours = readIn.nextInt();
            minutes = readIn.nextInt();
        }
        if (minutes != 0) {
            double doubleMinutes = ((double) minutes / 60);
            return hours + doubleMinutes;
        }
        return hours + minutes;
    }

    // this method calculates the pay of the babysitter according to
    // the start hours and minutes and end hours and minutes worked each day
    public double calculatePay(String startTime, String endTime) {
        double payForDay = 0;

        double startTimeInHours = makeHoursAndMinutes(startTime);
        double endTimeInHours = makeHoursAndMinutes(endTime);

        // if the end time is less than the start time aka the end time is after
        // 12:59AM, we +12 to the endHours
        // so that (endTime - startTime) is accurate

        if (endTimeInHours < startTimeInHours) {
            endTimeInHours = endTimeInHours + 12;
        }

        // The following if statements accrue the pay for the day
        // by using the proper price for the different times brackets worked within one
        // day

        // OPTION 1 start Before 9PM and end after 12AM
        if ((startTimeInHours < 9 && endTimeInHours > 12)) {
            payForDay = (9 - startTimeInHours) * getHourlyRateBeforeNinePm();
            payForDay = payForDay + (3 * getHourlyRateBetwNinePmMid());
            payForDay = payForDay + (endTimeInHours - 12) * getHourlyRateAfterMid();
        }

        // OPTION 2 start Before or at 9PM and end between 9PM and 12AM
        else if (startTimeInHours <= 9 && endTimeInHours >= 9 && endTimeInHours <= 12) {
            payForDay = (9 - startTimeInHours) * getHourlyRateBeforeNinePm();
            payForDay = payForDay + ((endTimeInHours - 9) * getHourlyRateBetwNinePmMid());

        }

        // OPTION 3 Start Before 9PM and end before or at 9PM
        else if (startTimeInHours < 9 && endTimeInHours <= 9) {
            payForDay = (endTimeInHours - startTimeInHours) * getHourlyRateBeforeNinePm();

        }

        // OPTION 4 Start at or After 9PM and end after 12AM
        else if (startTimeInHours >= 9 && endTimeInHours >= 12) {
            payForDay = (12 - startTimeInHours) * getHourlyRateBetwNinePmMid();
            payForDay = payForDay + (endTimeInHours - 12) * getHourlyRateAfterMid();

        }

        // OPTION 5 Start After 12AM and end after 12AM
        else if (startTimeInHours >= 12 && endTimeInHours >= 12) {
            payForDay = (endTimeInHours - startTimeInHours) * getHourlyRateAfterMid();
        }

        return payForDay;
    }

    // this method creates a new BabySitter object using the info from the two files

    public static BabySitter read(Scanner sc, Scanner scan) {
        if (!sc.hasNext()) {
            return null;
        }
        return new BabySitter(sc.nextInt(), sc.next(), sc.next(), Address.read(sc), sc.nextDouble(), sc.nextDouble(),
                sc.nextDouble(), scan.nextInt(), scan.nextInt());
    }

}
