package mytry;

import mytry.mytry.Try;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Supplier;

import static java.lang.System.err;
import static java.lang.System.out;

@SuppressWarnings("Convert2MethodRef")
public class MainWithTry extends MainBase {

    public static void main(String[] args) {

        out.println("=====>> " + MainWithTry.class.getSimpleName());

        new MainWithTry();

        out.println("<<===== " + MainWithTry.class.getSimpleName());
    }

    private MainWithTry() {

        Try
                .of(() -> readInput("Enter 2 integer numbers to divide: "))
                .flatMap(input -> Try.of(() -> parse(input)))
                .flatMap(pair -> Try.of(() -> divide(pair)))
                .onSuccess(quotient -> out.println("Quotient: " + quotient))
                .onFailure(throwable -> err.println("Error: " + throwable.toString()));
    }
}
