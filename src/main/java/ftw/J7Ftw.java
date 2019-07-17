package ftw;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("WeakerAccess")
public class J7Ftw {

    private static interface Predicate<T> {
        boolean test(T value);
    }

    private static void checkDir(String dir) {
        if (!new File(dir).exists())
            throw new RuntimeException(new FileNotFoundException("File or directory doesn't exist: " + dir));
        if (!new File(dir).isDirectory())
            throw new RuntimeException(new FileNotFoundException("File exists but is not a directory: " + dir));
    }

    private static Predicate<File> alwaysTrue = new Predicate<File>() {
        @Override
        public boolean test(File file) {
            return true;
        }
    };
    private static Predicate<File> isFile = new Predicate<File>() {
        @Override
        public boolean test(File file) {
            return file.isFile();
        }
    };
    private static Predicate<File> isDirectory = new Predicate<File>() {
        @Override
        public boolean test(File file) {
            return file.isDirectory();
        }
    };
    private static Predicate<File> isJavaFile = new Predicate<File>() {
        @Override
        public boolean test(File file) {
            return file.getAbsolutePath().endsWith(".java");
        }
    };
    private static Predicate<File> isScalaFile = new Predicate<File>() {
        @Override
        public boolean test(File file) {
            return file.getAbsolutePath().endsWith(".scala");
        }
    };

    public static void main(String[] args) {

        String defaultDir = "./src/main";
        String dir = args.length == 0 ? defaultDir : args[0];
        checkDir(dir);

        ftw(dir, isScalaFile)
                .forEach(System.out::println);
    }

    public static List<String> ftw(String dir, Predicate<File> predicate) {

        List<File> files = ftw(new File(dir), predicate);
        List<String> paths = new ArrayList<>();

        for (File f : files) {
            paths.add(f.getPath());
        }

        return paths;
    }

    public static List<File> ftw(File file, Predicate<File> predicate) {

        if (!file.exists())
            throw new RuntimeException(new FileNotFoundException("File or directory doesn't exist: " + file));

        List<File> fileList = new ArrayList<>();

        if (predicate.test(file))
            fileList.add(file);

        if (file.isDirectory()) {
            //noinspection ConstantConditions
            for (File f : file.listFiles()) {
                fileList.addAll(ftw(f, predicate));
            }
        }

        return fileList;
    }
}
