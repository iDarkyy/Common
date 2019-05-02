package me.idarkyy.common.youtube;

import me.idarkyy.common.web.WebUtil;
import me.idarkyy.common.youtube.query.ChannelQuery;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class Youtube {
    private String apiKey;

    public Youtube() {
        this.apiKey = "AIzaSyD1wjRGbzKgvjqAU25pREy1dVio9WpcuS0";
    }

    public Youtube(String apiKey) {
        this.apiKey = apiKey;
    }

    public Channel getChannel(String channelId) {
        return new Channel(this, channelId);
    }

    public ChannelQuery findChannelsByUsername(String username) throws IOException {
        String url = "https://www.googleapis.com/youtube/v3/channels" +
                "?part=contentDetails" +
                "&key=" + apiKey +
                "&forUsername=" + encode(username) +
                "&maxResults=50";

        JSONArray array = new JSONObject(WebUtil.getWebPage(url)).getJSONArray("items");

        List<String> obj = new ArrayList<>();

        for (Object o : array) {
            JSONObject object = (JSONObject) o;

            obj.add(object.getString("id"));
        }

        return new ChannelQuery(obj, this);
    }

    private String encode(String string) {
        try {
            return URLEncoder.encode(string, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // ignored
        }

        return null; // won't happen
    }

    public String getApiKey() {
        return apiKey;
    }
}
