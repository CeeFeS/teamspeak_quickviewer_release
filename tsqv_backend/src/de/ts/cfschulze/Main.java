package de.ts.cfschulze;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class Main {

    public static final String HOST = "127.0.0.1";
    public static final Integer PORT = 10011;

    private static String path_settings = "";

    private static String username = "";
    private static String password = "";


    public static void main(String[] args) {
        final String index_out = args[0];
        final String path_stylesheet = args[1];
        final String path_head = args[2] + "/head.html";
        final String path_header = args[2] + "/header.html";
        final String path_body_content_before_client = args[2] + "/body_content_before_clientboxes.html";
        final String path_additional_body_content = args[2] + "/additional_body_content.html";
        path_settings = args[3];

        HTML html = new HTML(index_out, path_stylesheet, path_head, path_header, path_body_content_before_client, path_additional_body_content);
        html.setHost(HOST);
        html.setPort(PORT);

        read_properties();

        html.setUsername(username);
        html.setPassword(password);

        html.run();


    }


    private static void read_properties() {
        try (InputStream input = new FileInputStream(path_settings)) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            username = prop.getProperty("username");
            password = prop.getProperty("password");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}

