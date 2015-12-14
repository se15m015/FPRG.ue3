package fprg.figl.winterhalder;

import java.util.ArrayList;

public class Main {

    interface Iscan<B,A> {
        B scanFunc(B inputB, A inputA);
    }

    public static void main(String[] args) {
        //Integer to Float
        ArrayList<Integer> inputInteger = new ArrayList<Integer>(){{
            add(3);
            add(7);
            add(15);
            add(44);
        }};


        ArrayList<Float> resultFloat = scan((a,b) -> (a/2 + b), 5f, inputInteger);
        System.out.println("--------Floats--------");
        System.out.println("Numbers: " + inputInteger);
        System.out.println("resultCalculated: " + resultFloat);

        // String to Int
        ArrayList<String> inputString = new ArrayList<String>(){{
            add("Hello");
            add("World");
            add("Foo");
            add("Bar");
        }};

        ArrayList<Integer> resultString = scan((x,y) -> ( x + y.length()), 5, inputString);

        System.out.println("--------StringCounts--------");
        System.out.println("StringConcat: " + inputString);
        System.out.println("resultStringConcat: " + resultString);


        //Integer to Strings

        ArrayList<String> resultStrings= scan((a,b) -> (a + String.valueOf(b)), "", inputInteger);
        System.out.println("--------Strings--------");
        System.out.println("Numbers: " + inputInteger);
        System.out.println("resultConcatStrings: " + resultStrings);
    }

    private static <B,A> ArrayList<B> scan(Iscan<B,A> func, B initialValue, ArrayList<A> input){

        ArrayList<B> output = new ArrayList<>();

        if(input.size() == 0){
            return output;
        }

        output.add(func.scanFunc(initialValue, input.get(0)));
        scanRec(func, input, output, 1);
        return output;
    }

    private static <B,A> void scanRec(Iscan<B,A> func, ArrayList<A> input, ArrayList<B> output, int count){
        // abort
        if(count == input.size()) {
            return;
        }
        output.add(func.scanFunc(output.get(count-1), input.get(count)));
        scanRec(func, input, output, count+1);
    }
}
