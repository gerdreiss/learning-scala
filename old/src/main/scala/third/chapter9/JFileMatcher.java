package third.chapter9;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.function.Function;

public class JFileMatcher {

    private static final File[] filesHere = new File(".").listFiles();

    //def filesMatching(matcher: String => Boolean) = filesHere.filter(f => matcher(f.getName))
    //def filesEnding(query: String) = filesMatching(_ endsWith query)
    //def filesContaining(query: String) = filesMatching(_ contains query)
    //def filesRegex(query: String) = filesMatching(_ matches query)

    private static File[] filesMatching(Function<String, Boolean> matcher) throws IOException {
        return Arrays.stream(filesHere)
                .filter(f -> matcher.apply(f.getName()))
                .toArray(File[]::new);
    }

    public File[] filesEnding(String query) throws IOException {
        return filesMatching(s -> s.endsWith(query));
    }

    public File[] filesContaining(String query) throws IOException {
        return filesMatching(s -> s.contains(query));
    }

    public File[] filesRegex(String query) throws IOException {
        return filesMatching(s -> s.matches(query));
    }
}
