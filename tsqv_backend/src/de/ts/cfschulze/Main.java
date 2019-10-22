package de.ts.cfschulze;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static String stylesheet;
    private static StringBuilder result = new StringBuilder();
    private static StringBuilder head = new StringBuilder();
    private static StringBuilder header = new StringBuilder();
    private static StringBuilder body_content_before_clientboxes = new StringBuilder();
    private static StringBuilder additional_body_content = new StringBuilder();
    private static String refresh = "<?php header(\"refresh: 3;\"); ?>";


    public static void main(String[] args) {

        read_result(args);
        read_stylesheet(args);
        read_head(args);
        read_header(args);
        read_body_content_before_clientboxes(args);
        read_additional_body_content(args);


        String without_html_result = verarbeiten(result.toString(), head.toString(), header.toString(),
                body_content_before_clientboxes.toString(), additional_body_content.toString());

        File f = new File(args[0]);
        BufferedWriter htmlwriter = null;

        try {
            htmlwriter = new BufferedWriter(new FileWriter(f, false));

            htmlwriter.write(without_html_result);

            htmlwriter.close();

        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

    }

    public static void read_result(String[] args) {
        String s = null;

        try {
            FileReader reader = new FileReader(args[3] + "/result.txt");
            BufferedReader br = new BufferedReader(reader);

            while ((s = br.readLine()) != null) {
                result.append(s);
            }

            br.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void read_stylesheet(String[] args) {
        String s = null;
        StringBuilder stsh = new StringBuilder();
        try {
            FileReader reader = new FileReader(args[1]);
            BufferedReader br = new BufferedReader(reader);

            while ((s = br.readLine()) != null) {
                stsh.append(s);
            }

            br.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        stylesheet = stsh.toString();
    }

    public static void read_head(String[] args) {
        String s = null;

        try {
            FileReader reader = new FileReader(args[2] + "/head.html");
            BufferedReader br = new BufferedReader(reader);

            while ((s = br.readLine()) != null) {
                head.append(s);
            }

            br.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void read_header(String[] args) {

        String s = null;

        try {
            FileReader reader = new FileReader(args[2] + "/header.html");
            BufferedReader br = new BufferedReader(reader);

            while ((s = br.readLine()) != null) {
                header.append(s);
            }

            br.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void read_body_content_before_clientboxes(String[] args) {
        String s = null;

        try {
            FileReader reader = new FileReader(args[2] + "/body_content_before_clientboxes.html");
            BufferedReader br = new BufferedReader(reader);

            while ((s = br.readLine()) != null) {
                body_content_before_clientboxes.append(s);
            }

            br.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void read_additional_body_content(String[] args) {
        String s = null;

        try {
            FileReader reader = new FileReader(args[2] + "/additional_body_content.html");
            BufferedReader br = new BufferedReader(reader);

            while ((s = br.readLine()) != null) {
                additional_body_content.append(s);
            }

            br.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public static String verarbeiten(String result, String head, String header, String body_content_before_clientboxes,
                                     String additional_body_content) {

        String[] res2 = result.split("client_nickname=");
        int i = 1;
        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>\r\n" + "<html lang=\"de\">\r\n" + refresh + "  <head>\r\n" + "<style>" + stylesheet
                + "</style> <meta charset=\"utf-8\" />" + head + "</head>\r\n" + "  <body> <div class=\"all-content-wrapper\">   <header>\r\n" +

                header + "    </header><div class=\"body_content_before_clientboxes\">" + body_content_before_clientboxes + "</div><div class=\"boxes-wrapper\">");

        while (i < res2.length) {
            if (!(res2[i].startsWith("serveradmin"))) {
                // System.out.println(res2[i]);

                String[] res3 = res2[i].split("clid");
                String[] res4 = res3[0].split("client_type=0");
                String client_name = res4[0].trim().replace("\\s", "");
                String ip = res4[1].replace("|", "").trim();
                String result_einzeln = "<div id=\"box\"><p>" + client_name + "<br>" + ip + "<br></p><br></div>";
                sb.append(result_einzeln);

            }
            i++;
        }

        sb.append("</div><div class=\"additional-bodycontent-wrapper\">" + additional_body_content + "</div></div>" + "<footer><div class=\"branding-wrapper\">CeeFeS: Apache TS-QV: v1.02 &#169 2019</div></footer></body>\r\n" + "</html>");

        return sb.toString();

    }


}

