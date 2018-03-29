package producerconsumer;

import java.util.Random;
import java.lang.StringBuilder;

public class OperationBuilder {
    private int lowerBound;
    private int upperBound;
    private Random randomGenerator = new Random(System.currentTimeMillis());
    private StringBuilder stringBuilder = new StringBuilder();

    public OperationBuilder(int min, int max) {
        if(min > max){
            throw new Exception();
        }

        lowerBound = min;
        upperBound = max;
    }

    public String generate() {
        stringBuilder = new StringBuilder();

        stringBuilder.append("( ");
        stringBuilder.append(Operator(randomGenerator.nextInt(4)) + " ");
        stringBuilder.append(GetRandom() + " ");
        stringBuilder.append(GetRandom());
        stringBuilder.append(" )");

        return stringBuilder.toString();
    }

    private int GetRandom(){
        return (randomGenerator.nextInt(upperBound - lowerBound) + lowerBound);
    }

    private String Operator(int option) {
        switch (option) {
            case 0:
                return "+";
            case 1:
                return "-";
            case 2:
                return "/";
            case 3:
                return "*";
            default:
                return "";
        }
    }
}