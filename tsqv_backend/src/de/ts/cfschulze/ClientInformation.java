package de.ts.cfschulze;

public class ClientInformation {

    private String nickname;
    private String ip;
    private String channel;
    private String beschreibung;

    public ClientInformation(String nickname, String ip, String channel, String beschreibung) {
        this.nickname = nickname;
        this.ip = ip;
        this.channel = channel;
        this.beschreibung = beschreibung;
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
}
