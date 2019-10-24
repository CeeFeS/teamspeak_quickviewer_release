package de.ts.cfschulze;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.TS3Query;
import com.github.theholywaffle.teamspeak3.api.event.*;

import java.util.HashMap;

public class ApiConnection {

    private HashMap<Integer, String> start_data;
    private final TS3Api api;

    public ApiConnection(String host, int port, String username, String password, HashMap<Integer, String> start_data) {

        this.start_data = start_data;


        final TS3Config config = new TS3Config();
        config.setHost(host);
        config.setQueryPort(port);
        config.setEnableCommunicationsLogging(true);


        final TS3Query query = new TS3Query(config);
        query.connect();

        api = query.getApi();
        api.login(username, password);
        api.selectVirtualServerById(1);
        api.setNickname("TSQV_Bot");

        api.registerAllEvents();
        api.addTS3Listeners(new TS3Listener() {

            @Override
            public void onTextMessage(TextMessageEvent e) {
                refresh();

            }

            @Override
            public void onServerEdit(ServerEditedEvent e) {
                refresh();
            }

            @Override
            public void onChannelEdit(ChannelEditedEvent channelEditedEvent) {
                refresh();
            }

            @Override
            public void onChannelDescriptionChanged(ChannelDescriptionEditedEvent channelDescriptionEditedEvent) {
                refresh();
            }

            @Override
            public void onClientMoved(ClientMovedEvent e) {
                refresh();
            }

            @Override
            public void onChannelCreate(ChannelCreateEvent channelCreateEvent) {
                refresh();
            }

            @Override
            public void onChannelDeleted(ChannelDeletedEvent channelDeletedEvent) {
                refresh();
            }

            @Override
            public void onChannelMoved(ChannelMovedEvent channelMovedEvent) {
                refresh();
            }

            @Override
            public void onChannelPasswordChanged(ChannelPasswordChangedEvent channelPasswordChangedEvent) {
                refresh();
            }

            @Override
            public void onPrivilegeKeyUsed(PrivilegeKeyUsedEvent privilegeKeyUsedEvent) {
                refresh();
            }

            @Override
            public void onClientLeave(ClientLeaveEvent e) {
                refresh();
            }

            @Override
            public void onClientJoin(ClientJoinEvent e) {
                refresh();
            }

        });

    }

    private void refresh() {
        HTML html = new HTML(start_data.get(Main.INDEXPHP_OUT), start_data.get(Main.PATH_STYLESHEET), start_data.get(Main.PATH_HEAD),
                start_data.get(Main.PATH_HEADER), start_data.get(Main.PATH_BODY_BEFORE_CLIENT), start_data.get(Main.PATH_ADDITIONAL_BODY_CONTENT));

        html.setApi(api);
        html.run();
    }
}
