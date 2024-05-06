package org.example.utilities;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

public class IOUtilities {

    public static File findFile(File folder, String file) {
        File[] filteredFiles = folder.listFiles((dir, name) -> name.equals(file));
        return Objects.requireNonNull(filteredFiles)[0];
    }

    public static File getResource(String fileOrFolder) throws URISyntaxException {
        URL url = IOUtilities.class.getClassLoader().getResource(fileOrFolder);
        return new File(Objects.requireNonNull(url).toURI());
    }

}
