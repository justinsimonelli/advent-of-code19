package main.java.com.sims.aoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.IntUnaryOperator;

public class DayOne {

    private static final IntUnaryOperator massFuelCalculator = DayOne::doCalculation;
    private static final IntUnaryOperator fuelCalculator = DayOne::doFuelCalculation;

    public static void main(String[] args) throws IOException {
        final List<String> input = Files.readAllLines(Paths.get("src/main/resources/dayone.txt"));
        System.out.println("Total fuel = " + calculateFuel(input, massFuelCalculator));
        System.out.println("Total fuel fuel = " + calculateFuel(input, fuelCalculator));
    }

    private static int calculateFuel(final List<String> input, final IntUnaryOperator operator) {
        return input.stream().mapToInt(Integer::parseInt).map(operator).sum();
    }

    private static int doCalculation(final int mass) {
        return (mass/3)-2;
    }

    private static int doFuelCalculation(final int mass) {
        int fuelCalculation = doCalculation(mass);

        if (fuelCalculation > 0) {
            fuelCalculation += doFuelCalculation(fuelCalculation);
        }

        return Math.max(fuelCalculation, 0);
    }
}
