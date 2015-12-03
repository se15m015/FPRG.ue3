package fprg.figl.winterhalder;

import java.util.ArrayList;

public class Main {

    interface Iscan<B,A> {
        B scanFunc(B inputB, A inputA);
    }

    public static void main(String[] args) {

        //Integer
        ArrayList<Integer> inputInteger = new ArrayList<Integer>(){{
            add(3);
            add(7);
            add(15);
            add(44);
        }};

        //Integer resultIntegerPlusOne = fold((x,y) -> ( x + y), 5, inputInteger);

        System.out.println("--------Integer--------");
       // System.out.println("resultIntegerPlusOne: " + resultIntegerPlusOne);

        // String
        ArrayList<String> inputString = new ArrayList<String>(){{
            add("Hello");
            add("World");
            add("Foo");
            add("Bar");
        }};

        ArrayList<Integer> resultString = scan((x,y) -> ( x * y.length()), 5, inputString);

        System.out.println("--------String--------");
        System.out.println("resultStringConcat: " + resultString);

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
