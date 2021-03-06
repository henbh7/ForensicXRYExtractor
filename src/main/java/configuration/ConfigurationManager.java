package configuration;

import java.io.*;
import java.util.Properties;

//Singleton class
public class ConfigurationManager {
    public String es_type;
    public String es_url;
    public String es_index;
    public Boolean is_logstach;
    public String ls_ip;
    public String ls_port;

    public String call_json_path;
    public String contact_json_path;
    public String device_json_path;
    public String message_json_path;
    public String chat_json_path;
    public String calendar_json_path;
    public String browse_history_json_path;
    public String email_json_path;
    public String location_json_path;
    public String log_json_path;
    public String parsers_types_json_path;
    public String app_json_path;

    private static ConfigurationManager instance = null;

    public ConfigurationManager() throws IOException {
        initConfiguration();
    }

    public static ConfigurationManager getInstance() {
        if (instance == null) {
            try {
                instance = new ConfigurationManager();
            } catch (Exception e) {
                System.out.println("Exiting duo to absence of configuration file");
                System.exit(1);
            }
        }
        return instance;
    }

    private void initConfiguration() throws IOException {
        InputStream inputStream = null;

        try {
            Properties prop = new Properties();

            String jarDirPath = ConfigurationManager.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath().replace("ForensicXRYExtractor-1.0-SNAPSHOT-jar-with-dependencies.jar", "") + "stream_config.properties";
            String propFileName = "resources/stream_config.properties";

            System.out.println(jarDirPath);

            InputStream fis;
            File fJAR = new File(jarDirPath);
            File f = new File(propFileName);
            if (fJAR.exists()) {
                System.out.println("loading resource from: " + jarDirPath);
                fis = new FileInputStream(fJAR);
            } else if (f.exists()) {
                System.out.println("loading resources/stream_config.properties");
                fis = new FileInputStream(propFileName);
            } else {
                System.out.println("loading resources/stream_config.properties from jar");
                fis = ConfigurationManager.class.getResourceAsStream("/stream_config.properties");
            }

            if (fis != null) {
                prop.load(fis);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

            try {
                // set properties values
                es_type = prop.getProperty("es_type");
                es_url = prop.getProperty("es_url");
                es_index = prop.getProperty("es_index");
                ls_ip = prop.getProperty("ls_ip");
                ls_port = prop.getProperty("ls_port");
                is_logstach = Boolean.parseBoolean(prop.getProperty("is_logstach"));

                call_json_path = prop.getProperty("call_json_path");
                contact_json_path = prop.getProperty("contact_json_path");
                device_json_path = prop.getProperty("device_json_path");
                message_json_path = prop.getProperty("message_json_path");
                chat_json_path = prop.getProperty("chat_json_path");
                calendar_json_path = prop.getProperty("calendar_json_path");
                browse_history_json_path = prop.getProperty("browse_history_json_path");
                email_json_path = prop.getProperty("email_json_path");
                location_json_path = prop.getProperty("location_json_path");
                log_json_path = prop.getProperty("log_json_path");
                parsers_types_json_path = prop.getProperty("parsers_types_json_path");
                app_json_path = prop.getProperty("app_json_path");
            } catch (Exception e) {
                e.printStackTrace();
            }
            fis.close();
            System.out.println("loaded configuration.");
        } catch (Exception e) {
            //logger.error(e.getMessage());
        }
    }
}