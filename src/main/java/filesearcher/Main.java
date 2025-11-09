package filesearcher;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ArgsParser parser = new ArgsParser(args);
        FileSearcher searcher = new FileSearcher(parser);
        searcher.executeSearch();
    }
}
