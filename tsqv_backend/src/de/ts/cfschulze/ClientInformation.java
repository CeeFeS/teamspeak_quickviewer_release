package de.ts.cfschulze;

public class ClientInformation {

    private String nickname;
    private String ip;
    private String channel;
    private String beschreibung;
    private int[] server_groups;

    public ClientInformation(String nickname, String ip, String channel, String description, int[] server_groups) {
        this.nickname = nickname;
        this.ip = ip;
        this.channel = channel;
        this.beschreibung = description;
        this.server_groups = server_groups;
    }


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }


    public String getMarked() {
        String marked = "trusted";

        for (int i = 0; i < server_groups.length; i++) {
            if (server_groups[i] == 16) {
                marked = "marked";
            } else if (server_groups[i] == 8) {
                marked = "untrusted";
            }


        }

        return marked;
    }

}
