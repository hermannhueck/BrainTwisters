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
public class JFtw {

    public static void main(String[] args) {

        String defaultDir = "./src/main";
        String dir = args.length == 0 ? defaultDir : args[0];

        Predicate<File> alwaysTrue = file -> true;
        Predicate<File> isFile = File::isFile;
        Predicate<File> isDirectory = File::isDirectory;
        Predicate<File> isJavaFile = file -> file.getAbsolutePath().endsWith(".java");
        Predicate<File> isScalaFile = file -> file.getAbsolutePath().endsWith(".scala");

        if (!new File(dir).exists())
            throw new RuntimeException(new FileNotFoundException("File or directory doesn't exist: " + dir));

        ftw(dir, isScalaFile)
                .forEach(System.out::println);
    }

    public static List<String> ftw(String dir, Predicate<File> predicate) {
        return ftw(new File(dir), predicate)
                .stream()
                .map(File::getPath)
                .collect(Collectors.toList());
    }

    public static List<File> ftw(File dir, Predicate<File> predicate) {

        File[] files = Optional
                .ofNullable(dir.listFiles())
                .orElse(new File[]{});

        Stream<File> children = Arrays.stream(files)
                .flatMap(file -> ftw(file, predicate).stream());

        return Stream.concat(
                testPredicate(dir, predicate),
                children
        ).collect(Collectors.toList());
    }

    private static Stream<File> testPredicate(File file, Predicate<File> predicate) {
        return predicate.test(file) ? Stream.of(file) : Stream.empty();
    }
}
