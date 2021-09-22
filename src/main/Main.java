package main;

import java.util.Scanner;

/**
 * Calculates E-field of a finite thin rod, based on user specified values.
 * Uses both the n point charge summation approximation, as well as the
 * evaluated integral to calculate E-field.
 *
 * @author Hritik "Ricky" Gupta | rg4825
 * @version 2021.9.14.7
 */

public class Main {

    public static double K = (8.99 * Math.pow(10, 9)); //Coulomb's constant

    public static void main(String[] args) {
        double rodCharge, rodLength, distanceFromRod;
        int numPoints;

        Scanner in = new Scanner(System.in);

        System.out.print("Enter total charge of rod in nC: ");
        rodCharge = in.nextDouble() * Math.pow(10, -9);

        System.out.print("Enter length of the rod in cm: ");
        rodLength = in.nextDouble() * Math.pow(10, -2);

        System.out.print("Enter distance from left side of rod of point P in cm: ");
        distanceFromRod = in.nextDouble() * Math.pow(10, -2);

        while (true) {
            System.out.print("Enter desired number of point charges: ");
            numPoints = in.nextInt();

            double sum = 0;

            for (int i = 0; i < numPoints; ++i) {
                sum = sum + ((rodCharge/numPoints) / (Math.pow((distanceFromRod + ((((2*i) + 1)*rodLength) / (2*numPoints))),2)));
            }

            double eFieldApprox = (sum * K * -1)/1000;
            double eFieldTrue = -1 * (((K * rodCharge) / (Math.pow(distanceFromRod, 2) + (distanceFromRod * rodLength))) / 1000);
            double error = (Math.abs(eFieldTrue - eFieldApprox) / Math.abs(eFieldTrue)) * 100;

            System.out.print("\nTotal sum for " + numPoints + " point charge(s): ");
            System.out.format("%.4f", eFieldApprox); //rounds to 4 decimal places
            System.out.println(" kN/C");

            System.out.print("True value: ");
            System.out.format("%.4f", eFieldTrue); //rounds to 4 decimal places
            System.out.println(" kN/C");

            System.out.print("Percent error: ");
            System.out.format("%.6f", error); //rounds to 6 decimal places
            System.out.println(" %");

            System.out.print("\nWould you like to put in a different number of point charges? (y/n) ");
            if (in.next().equals("n")) {
                break;
            }

        }
    }
}
