package producerconsumer;

public class OperationParser {
    public float evaluate(String operation){
        String[] operationDescriptor = operation.split("\\s+");
        if(operationDescriptor.length != 5){
            System.out.println("Cannot process");
            return 0;
        }
        int firstNumber = Integer.parseInt(operationDescriptor[2]);
        int secondNumber = Integer.parseInt(operationDescriptor[3]);

        return Execute(operationDescriptor[1], firstNumber, secondNumber);
    }

    private float Execute(String operation, int a, int b){
        switch(operation){
            case "+":
                return Add(a, b);
            case "-":
                return Substract(a, b);
            case "/":
                return Division(a, b);
            case "*":
                return Times(a, b);
            default:
                return 0;
        }
    }

    private float Division(int a, int b){
        if(b == 0){
            return Float.NaN;
        }

        return a / b;
    }

    private float Times(int a, int b){
        return a * b;
    }

    private float Substract(int a, int b){
        return a - b;
    }

    private float Add(int a, int b){
        return a + b;
    }
}