package me.idarkyy.common.youtube;

import me.idarkyy.common.web.WebUtil;
import org.json.JSONObject;

import java.io.IOException;

public class Channel {
    private Youtube youtube;
    private String channelId;

    private String stats;

    Channel(Youtube youtube, String channelId) {
        this.youtube = youtube;
        this.channelId = channelId;

        stats = "https://www.googleapis.com/youtube/v3/channels"
                + "?part=statistics"
                + "&id=" + channelId
                + "&key=" + youtube.getApiKey();
    }

    public int getSubscriberCount() throws IOException {
        return getStats().getInt("subscriberCount");
    }

    public int getVideoCount() throws IOException {
        return getStats().getInt("videoCount");
    }

    public int getViewCount() throws IOException {
        return getStats().getInt("viewCount");
    }

    public int getCommentCount() throws IOException {
        return getStats().getInt("commentCount");
    }

    public boolean isSubscriberCountHidden() throws IOException {
        return getStats().getBoolean("hiddenSubscriberCount");
    }

    public String getChannelId() throws IOException {
        return getItems().getString("id");
    }

    public Youtube getYoutube() {
        return youtube;
    }

    private JSONObject getItems() throws IOException {
        JSONObject object = new JSONObject(WebUtil.getWebPage(stats));
        return object.getJSONArray("items").getJSONObject(0);
    }

    private JSONObject getStats() throws IOException {
        return getItems().getJSONObject("statistics");
    }
}
