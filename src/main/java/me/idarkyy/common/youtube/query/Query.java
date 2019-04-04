package me.idarkyy.common.youtube.query;

import me.idarkyy.common.youtube.Youtube;

import java.util.List;

public abstract class Query<T, RT> {
    private List<T> finds;
    private Youtube youtube;

    public Query(List<T> finds, Youtube youtube) {
        this.finds = finds;
        this.youtube = youtube;
    }

    public List<T> getFinds() {
        return finds;
    }

    public Youtube getYoutube() {
        return youtube;
    }

    public abstract RT get(int index);
}
