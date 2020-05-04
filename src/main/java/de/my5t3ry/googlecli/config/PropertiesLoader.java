package de.my5t3ry.googlecli.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/** User: my5t3ry Date: 5/4/20 8:56 PM */
public class PropertiesLoader {
  public static Properties properties;

  public static void loadProperties() {
    Properties props = new Properties();
    try {
      try (InputStream resourceStream =
          PropertiesLoader.class.getResourceAsStream("/config.properties")) {
        props.load(resourceStream);
      }
      PropertiesLoader.properties = props;
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
