package de.ts.cfschulze;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.TS3Query;
import com.github.theholywaffle.teamspeak3.api.wrapper.Channel;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import com.github.theholywaffle.teamspeak3.api.wrapper.IconFile;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HTML {
    public static String stylesheet;

    private StringBuilder head = new StringBuilder();
    private StringBuilder header = new StringBuilder();
    private StringBuilder body_content_before_clientboxes = new StringBuilder();
    private StringBuilder additional_body_content = new StringBuilder();
    private String refresh = "<?php header(\"refresh: 3;\"); ?>";
    private ArrayList<ClientInformation> clientInformation;

    private String path_index_out;

    private String host;
    private int port;
    private String username;
    private String password;


    public HTML(String path_index_out, String path_stylsheet, String path_head, String path_header, String path_body_content_before_client, String path_additional_body_content) {
        clientInformation = new ArrayList<ClientInformation>();

        this.path_index_out = path_index_out;

        read_stylesheet(path_stylsheet);
        read_head(path_head);
        read_header(path_header);
        read_body_content_before_clientboxes(path_body_content_before_client);
        read_additional_body_content(path_additional_body_content);


    }

    public void run() {
        String php_string = create_index_php(head.toString(), header.toString(),
                body_content_before_clientboxes.toString(), additional_body_content.toString());

        write_index_php(path_index_out, php_string);
    }


    private void write_index_php(String path_index_out, String php_string) {
        File f = new File(path_index_out);
        BufferedWriter htmlwriter = null;

        try {
            htmlwriter = new BufferedWriter(new FileWriter(f, false));

            htmlwriter.write(php_string);

            htmlwriter.close();

        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }


    private void read_stylesheet(String path) {
        String s = null;
        StringBuilder stsh = new StringBuilder();
        try {
            FileReader reader = new FileReader(path);
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

    private void read_head(String path) {
        String s = null;

        try {
            FileReader reader = new FileReader(path);
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

    private void read_header(String path) {

        String s = null;

        try {
            FileReader reader = new FileReader(path);
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

    private void read_body_content_before_clientboxes(String path) {
        String s = null;

        try {
            FileReader reader = new FileReader(path);
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

    private void read_additional_body_content(String path) {
        String s = null;

        try {
            FileReader reader = new FileReader(path);
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


    private String create_index_php(String head, String header, String body_content_before_clientboxes,
                                    String additional_body_content) {

        ArrayList<ClientInformation> clients = getData(getHost(), getPort());


        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>\r\n" + "<html lang=\"de\">\r\n" + refresh + "  <head>\r\n" + "<style>" + stylesheet
                + "</style> <meta charset=\"utf-8\" />" + head + "</head>\r\n" + "  <body> <div class=\"all-content-wrapper\">   <header>\r\n" +

                header + "    </header><div class=\"body_content_before_clientboxes\">" + body_content_before_clientboxes + "</div><div class=\"boxes-wrapper\">");

        for (ClientInformation client : clients) {

            if (!(client.getNickname().equals("TSQV_Bot"))) {

                if (client.getMarked().equals("marked")) {
                    String result_einzeln = "<div id=\"box\"><div class=\"profile-wrapper\"><div class=\"nickname-wrapper\">" + client.getNickname() + "<div class=\"marked\">" + "markiert" + "</div></div><div class=\"channel-wrapper\">Channel:" + client.getChannel() + "</div><div class=\"ip-wrapper\">IP:" + client.getIp() + "</div></div><div class=\"description-wrapper\">" + client.getBeschreibung() + "</div></div><br>";
                    sb.append(result_einzeln);
                } else if (client.getMarked().equals("untrusted")) {
                    String result_einzeln = "<div id=\"box\"><div class=\"profile-wrapper\"><div class=\"nickname-wrapper\">" + client.getNickname() + "<div class=\"untrusted\">" + "untrusted" + "</div></div><div class=\"channel-wrapper\">Channel:" + client.getChannel() + "</div><div class=\"ip-wrapper\">IP:" + client.getIp() + "</div></div><div class=\"description-wrapper\">" + client.getBeschreibung() + "</div></div><br>";
                    sb.append(result_einzeln);
                } else if (client.getMarked().equals("trusted")) {
                    String result_einzeln = "<div id=\"box\"><div class=\"profile-wrapper\"><div class=\"nickname-wrapper\">" + client.getNickname() + "<div class=\"trusted\">" + "trusted" + "</div></div><div class=\"channel-wrapper\">Channel:" + client.getChannel() + "</div><div class=\"ip-wrapper\">IP:" + client.getIp() + "</div></div><div class=\"description-wrapper\">" + client.getBeschreibung() + "</div></div><br>";
                    sb.append(result_einzeln);
                }
            }
        }

        sb.append("</div><div class=\"additional-bodycontent-wrapper\">" + additional_body_content + "</div></div>" + "<footer><div class=\"branding-wrapper\">CeeFeS: Apache TS-QV: v1.02 &#169 2019</div></footer></body>\r\n" + "</html>");

        return sb.toString();

    }


    private ArrayList<ClientInformation> getData(String host, int port) {


        final TS3Config config = new TS3Config();
        config.setHost(host);
        config.setQueryPort(port);
        config.setEnableCommunicationsLogging(true);


        final TS3Query query = new TS3Query(config);
        query.connect();

        final TS3Api api = query.getApi();
        api.login(getUsername(), getPassword());
        api.selectVirtualServerById(1);
        api.setNickname("TSQV_Bot");

        // Get all channels and map their channel IDs to them
        List<Channel> channels = api.getChannels();

        Map<Integer, Channel> channelMap = new HashMap<>(channels.size());
        for (Channel channel : channels) {
            channelMap.put(channel.getId(), channel);

        }
        // List all clients in the console
        for (Client c : api.getClients()) {
            // Get the client's channel
            Channel channel = channelMap.get(c.getChannelId());
            String beschreibung = api.getClientInfo(c.getId()).getDescription();

            // Write the client and channel name into the console
            ClientInformation client = new ClientInformation(c.getNickname(), c.getIp(), channel.getName(), beschreibung, c.getServerGroups());
            clientInformation.add(client);
        }


        // We're done, disconnect
        query.exit();

        return clientInformation;
    }


    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}



