package ftw;

import javaslang.Function1;
import javaslang.collection.List;
import javaslang.control.Option;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SuppressWarnings("WeakerAccess")
public class JSlangFtw {

    private static void checkDir(String dir) {
        if (!new File(dir).exists())
            throw new RuntimeException(new FileNotFoundException("File or directory doesn't exist: " + dir));
        if (!new File(dir).isDirectory())
            throw new RuntimeException(new FileNotFoundException("File exists but is not a directory: " + dir));
    }

    private static Function1<File, Boolean> alwaysTrue = file -> true;
    private static Function1<File, Boolean> isFile = File::isFile;
    private static Function1<File, Boolean> isDirectory = File::isDirectory;
    private static Function1<File, Boolean> isJavaFile = file -> file.getAbsolutePath().endsWith(".java");
    private static Function1<File, Boolean> isScalaFile = file -> file.getAbsolutePath().endsWith(".scala");

    public static void main(String[] args) {

        String defaultDir = "./src/main";
        String dir = args.length == 0 ? defaultDir : args[0];
        checkDir(dir);

        ftw(dir, isScalaFile)
                .forEach(System.out::println);
    }

    public static List<String> ftw(String dir, Function1<File, Boolean> predicate) {
        return ftw(new File(dir), predicate)
                .map(File::getPath);
    }

    public static List<File> ftw(File file, Function1<File, Boolean> predicate) {

        if (!file.exists())
            throw new RuntimeException(new FileNotFoundException("File or directory doesn't exist: " + file));

        List<File> prefix = predicate.apply(file) ? List.of(file) : List.empty();

        @SuppressWarnings("ConstantConditions")
        List<File> children = file.isDirectory() ?
                List.of(file.listFiles()).flatMap(f -> ftw(f, predicate)) :
                List.empty();

        return children.prependAll(prefix);
    }
}
