package de.ts.cfschulze;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;


public class Main {

    public static final String HOST = "127.0.0.1";
    public static final Integer PORT = 10011;

    private static String path_settings = "";

    private static String username = "";
    private static String password = "";

    public static final int INDEXPHP_OUT = 0, PATH_STYLESHEET = 1, PATH_HEAD = 2, PATH_HEADER = 3, PATH_BODY_BEFORE_CLIENT = 4, PATH_ADDITIONAL_BODY_CONTENT = 5;


    public static void main(String[] args) {
        HashMap<Integer, String> start_data = new HashMap<>();


        start_data.put(INDEXPHP_OUT, args[0]);
        start_data.put(PATH_STYLESHEET, args[1]);
        start_data.put(PATH_HEAD, args[2] + "/head.html");
        start_data.put(PATH_HEADER, args[2] + "/header.html");
        start_data.put(PATH_BODY_BEFORE_CLIENT, args[2] + "/body_content_before_clientboxes.html");
        start_data.put(PATH_ADDITIONAL_BODY_CONTENT, args[2] + "/additional_body_content.html");

        path_settings = args[3];

        read_properties();
        ApiConnection apiConnection = new ApiConnection(HOST, PORT, username, password, start_data);


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

