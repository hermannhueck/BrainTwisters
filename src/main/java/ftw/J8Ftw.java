package ftw;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SuppressWarnings("WeakerAccess")
public class J8Ftw {

    private static void checkDir(String dir) {
        if (!new File(dir).exists())
            throw new RuntimeException(new FileNotFoundException("File or directory doesn't exist: " + dir));
        if (!new File(dir).isDirectory())
            throw new RuntimeException(new FileNotFoundException("File exists but is not a directory: " + dir));
    }

    private static Predicate<File> alwaysTrue = file -> true;
    private static Predicate<File> isFile = File::isFile;
    private static Predicate<File> isDirectory = File::isDirectory;
    private static Predicate<File> isJavaFile = file -> file.getAbsolutePath().endsWith(".java");
    private static Predicate<File> isScalaFile = file -> file.getAbsolutePath().endsWith(".scala");

    public static void main(String[] args) {

        String defaultDir = "./src/main";
        String dir = args.length == 0 ? defaultDir : args[0];
        checkDir(dir);

        ftw(dir, isScalaFile)
                .forEach(System.out::println);
    }

    public static List<String> ftw(String dir, Predicate<File> predicate) {
        return ftw(new File(dir), predicate)
                .map(File::getPath)
                .collect(Collectors.toList());
    }

    public static Stream<File> ftw(File file, Predicate<File> predicate) {

        if (!file.exists())
            throw new RuntimeException(new FileNotFoundException("File or directory doesn't exist: " + file));

        Stream<File> prefix = predicate.test(file) ? Stream.of(file) : Stream.empty();

        @SuppressWarnings("ConstantConditions")
        Stream<File> children = file.isDirectory() ?
                Arrays.stream(file.listFiles()).flatMap(f -> ftw(f, predicate)) :
                Stream.empty();

        return Stream.concat(prefix, children);
    }
}
