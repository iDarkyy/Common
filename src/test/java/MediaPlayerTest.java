import me.idarkyy.common.media.MediaPlayer;

import java.io.IOException;
import java.io.InputStream;

public class MediaPlayerTest {
    public void create() throws IOException, InterruptedException {
        InputStream file = getClass().getResourceAsStream("myfile.ogg");

        MediaPlayer player = new MediaPlayer();

        player.register(file, "test_file");

        use(player);
    }

    public void use(MediaPlayer mediaPlayer) throws InterruptedException {
        mediaPlayer.play("test_file");

        wait(50000);

        mediaPlayer.stop();
    }
}
