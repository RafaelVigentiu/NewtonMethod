package polinom;

import java.util.Scanner;

public class NewtonRaphsonGeneric {
    public static void main(String[] args) {
        // Polinomul este de grad 3, coeficienții sunt fixați
        System.out.println("Introduceți aproximația inițială: ");
        double[] coefficients = {3, -5, 2, -7};
        double initialGuess = 3;
        double tolerance = 1e-6;
        int maxIterations = 100;

        double root = findRoot(coefficients, initialGuess, tolerance, maxIterations);
        if (!Double.isNaN(root)) {
            System.out.println("Aproximarea rădăcinii este: " + root);
        } else {
            System.out.println("Nu a fost găsită o soluție în numărul maxim de iterații.");
        }
    }

    public static double findRoot(double[] coefficients, double initialGuess, double tolerance, int maxIterations) {
        double inital = initialGuess;
        for (int i = 0; i < maxIterations; i++) {
            double functionValue = polynomialFunction(coefficients, inital);
            double derivativeValue = derivativeFunction(coefficients, inital);

            // Verificare pentru a preveni împărțirea la zero
            if (Math.abs(derivativeValue) < 1e-10) {
                System.out.println("Derivata este aproape zero, metoda nu poate continua.");
                return Double.NaN;
            }
            double x_n1 = inital - (functionValue / derivativeValue);
            // Verifică dacă diferența dintre iterații este suficient de mică
            if (Math.abs(x_n1 - inital) < tolerance) {
                return x_n1;
            }
            inital = x_n1;
        }
        return Double.NaN; // Dacă nu converge în numărul maxim de iterații
    }

    // Definirea funcției polinomului pentru un set de coeficienți
    public static double polynomialFunction(double[] coefficients, double x) {
        double result = 0;
        int degree = coefficients.length - 1;
        for (int i = 0; i <= degree; i++) {
            result += coefficients[i] * Math.pow(x, degree - i);
        }
        return result;
    }

    // Definirea derivatei polinomului pentru un set de coeficienți
    public static double derivativeFunction(double[] coefficients, double x) {
        double result = 0;
        int degree = coefficients.length - 1;
        for (int i = 0; i < degree; i++) {
            result += coefficients[i] * (degree - i) * Math.pow(x, degree - i - 1);
        }
        return result;
    }
}
