/**
 * @name:Rachel Minkowitz
 * @since: 06-09-2021
 * @version: 3.0
 * @Description: Proffesor Lowenthal, HomeWork2 Babysitter Club payroll
 */
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Homework2 {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(new File("Personnel.txt"));
        Scanner scan = new Scanner(new File("Payroll.txt"));
        PrintStream outPut = new PrintStream("output.txt");

        ArrayList<BabySitter> arrEmployees = new ArrayList<>();

        // while the Personnel.txt file has more info to use

        while (sc.hasNext()) {

            BabySitter employee = BabySitter.read(sc, scan);
            double pay = 0;

            // accrues the payment for the employee by calculating the pay for the day and
            // if they worked
            // more than one day, it adds the day's pay to the pay before

            for (int i = 0; i < employee.getDaysWorked(); i++) {
                String startTime = scan.next();
                String endTime = scan.next();

                pay = pay + employee.calculatePay(startTime, endTime);
            }

            employee.setPayAmount(pay); // sets the pay as the payAmount data field in the 'employee' Babysitter object
            arrEmployees.add(employee); // adds this employee to the ArrayList of employees
        }

        // sorts the arrayList according to last name

        Collections.sort(arrEmployees);

        // uses an enhanced for loop to print the sorted ArrayList

        for (BabySitter sitter : arrEmployees) {
            outPut.printf("%sBABYSITTER FEE: $%.2f %n%n", sitter.toString(), sitter.getPayment());
        }

    }
}
