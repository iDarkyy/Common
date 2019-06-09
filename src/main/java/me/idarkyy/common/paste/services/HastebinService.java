package me.idarkyy.common.paste.services;

import com.google.gson.JsonParser;
import me.idarkyy.common.paste.Paste;
import me.idarkyy.common.paste.PasteService;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HastebinService extends PasteService {
    private static final PasteService service = new HastebinService();

    public static PasteService getService() {
        return service;
    }

    @Override
    public String paste(Paste paste) throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost("https://hastebin.com/documents");

        post.setEntity(new StringEntity(paste.getText()));

        HttpResponse response = client.execute(post);
        String result = EntityUtils.toString(response.getEntity());

        return "https://hastebin.com/" + new JsonParser().parse(result).getAsJsonObject().get("key").getAsString();
    }
}
