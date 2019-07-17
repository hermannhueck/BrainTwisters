package mytry;


import io.vavr.CheckedFunction0;
import io.vavr.control.Try;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Supplier;

import static java.lang.System.err;
import static java.lang.System.out;

@SuppressWarnings("Convert2MethodRef")
public class MainWithVavr extends MainBase {

    public static void main(String[] args) {

        out.println("=====>> " + MainWithVavr.class.getSimpleName());

        new MainWithVavr();

        out.println("<<===== " + MainWithVavr.class.getSimpleName());
    }

    private MainWithVavr() {

        Try
                .of(() -> readInput("Enter 2 integer numbers to divide: "))
                .flatMap(input -> Try.of(() -> parse(input)))
                .flatMap(pair -> Try.of(() -> divide(pair)))
                .onSuccess(quotient -> out.println("Quotient: " + quotient))
                .onFailure(throwable -> err.println("Error: " + throwable.getMessage()));
    }
}
