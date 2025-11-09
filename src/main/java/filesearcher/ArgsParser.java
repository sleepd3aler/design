package filesearcher;

import java.util.LinkedHashMap;
import java.util.Map;

public class ArgsParser {
    private final Map<String, String> params = new LinkedHashMap<>();
    private final String[] args;

    public ArgsParser(String[] args) {
        this.args = args;
        parseArgs();
    }

    public Map<String, String> getParams() {
        return params;
    }

    public String getDir() {
        return params.get("-d");
    }

    public String getName() {
        return params.get("-n");
    }

    public String getTypeOfSearch() {
        return params.get("-t");
    }

    public String getOutputPath() {
        return params.get("-o");
    }

    private void parseArgs() {
        validate(this.args);
        for (String arg : this.args) {
            String[] parts = arg.split("=", 2);
            params.put(parts[0], parts[1]);
        }
    }

    private void validate(String[] args) {
        if (args.length < 4) {
            throw new IllegalArgumentException("Not enough arguments to execute. Must contain: -d, -n, -t, -o");
        }
        if (!args[0].startsWith("-d")) {
            throw new IllegalArgumentException("Searching directory key \"-d\" is missing");
        } else if (args[0].charAt(2) != '=') {
            throw new IllegalArgumentException("Argument must contain '=' at: " + args[0]);
        }
        if (!args[1].startsWith("-n")) {
            throw new IllegalArgumentException("Searching file name or mask \"-n\" is missing");
        } else if (args[1].charAt(2) != '=') {
            throw new IllegalArgumentException("Argument must contain '=' at: " + args[1]);
        }
        if (!args[2].startsWith("-t")) {
            throw new IllegalArgumentException("Type of search \"-t\" is missing");
        } else if (args[2].charAt(2) != '=') {
            throw new IllegalArgumentException("Argument must contain '=' at: " + args[2]);
        }
        if (!args[3].startsWith("-o")) {
            throw new IllegalArgumentException("File to write \"-o\" is missing");
        } else if (args[3].charAt(2) != '=') {
            throw new IllegalArgumentException("Argument must contain '=' at: " + args[3]);
        }
        for (String arg : args) {
            String[] parts = arg.split("=", 2);
            if (parts.length < 2 || parts[1].isBlank()) {
                throw new IllegalArgumentException("Missing value for: " + arg);
            }
        }
    }
}
