package cn.com.ljy.testaudiomp3;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.xl.xlaudio.XLAudioClient;
import com.xl.xlaudio.XLAudioPlayerListener;

import java.io.File;
import java.io.IOException;

import com.xl.undercover.mp3recorder.MP3Recorder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final File voiceFile = new File(Environment.getExternalStorageDirectory(),"gangsdk_temp.mp3");
        final MP3Recorder mp3Recorder = new MP3Recorder(voiceFile);

        findViewById(R.id.btnAudioStart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    mp3Recorder.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        findViewById(R.id.btnAudioStop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    mp3Recorder.stop();
                XLAudioClient.sharedInstance().play(voiceFile.getAbsolutePath(), new XLAudioPlayerListener() {
                    @Override
                    public void onFinished(String url) {
                        Toast.makeText(MainActivity.this, "播放完成", Toast.LENGTH_SHORT).show();
                    }
                });
                }
        });
    }
}
