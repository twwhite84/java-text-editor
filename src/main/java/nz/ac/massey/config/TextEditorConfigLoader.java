package nz.ac.massey.config;

import org.yaml.snakeyaml.Yaml;

import java.awt.*;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Map;

/**
 * Responsible for loading a config file into the editor
 */
public class TextEditorConfigLoader {

    /**
     * File of the .yml config
     */
    private final File configFile;

    public TextEditorConfigLoader(File configFile) {
        this.configFile = configFile;
    }

    /**
     * Load the config file into this object
     */
    public TextEditorConfig load() {
        TextEditorConfig config = null;

        try {
            InputStream inputStream = Files.newInputStream(this.configFile.toPath());

            // Load yaml data
            Yaml yaml = new Yaml();
            Map<String, Object> data = yaml.load(inputStream);

            // Read data into object
            int defaultFontSize = (int) data.getOrDefault("defaultFontSize", 16);
            String defaultFont = (String) data.getOrDefault("defaultFont", Font.MONOSPACED);
            Color background = Color.decode((String) data.getOrDefault("background", "#FFFFFF"));
            Color fontColour = Color.decode((String) data.getOrDefault("fontColour", "#000000"));

            config = new TextEditorConfig(defaultFontSize, defaultFont, background, fontColour);
        } catch (Exception ex) {
            System.err.println("Error loading config.yml");
            ex.printStackTrace();
        }

        return config;
    }

}
