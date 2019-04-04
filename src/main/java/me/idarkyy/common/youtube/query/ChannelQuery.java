package me.idarkyy.common.youtube.query;

import me.idarkyy.common.youtube.Channel;
import me.idarkyy.common.youtube.Youtube;

import java.util.List;

public class ChannelQuery extends Query<String, Channel> {
    public ChannelQuery(List<String> finds, Youtube youtube) {
        super(finds, youtube);
    }

    @Override
    public Channel get(int index) {
        return getYoutube().getChannel(getFinds().get(index));
    }
}
