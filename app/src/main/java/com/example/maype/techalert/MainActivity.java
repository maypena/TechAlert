package com.example.maype.techalert;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.view.View;
import android.widget.TextView;

import com.vidyo.VidyoClient.Connector.ConnectorPkg;
import com.vidyo.VidyoClient.Connector.Connector;

public class MainActivity extends AppCompatActivity implements Connector.IConnect{

    private Connector vc;
    private FrameLayout videoFrame;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConnectorPkg.setApplicationUIContext(this);
        ConnectorPkg.initialize();
        videoFrame = (FrameLayout)findViewById(R.id.videoFrame);
    }

    public void start(View v) {
        vc = new Connector(videoFrame, Connector.ConnectorViewStyle.VIDYO_CONNECTORVIEWSTYLE_Default, 15, "warning info@VidyoClient info@VidyoConnector", "", 0);
        vc.showViewAt(videoFrame, 0, 0, videoFrame.getWidth(), videoFrame.getHeight());
    }

    public void connect(View v) {
        String token = "cHJvdmlzaW9uAHVzZXIxQDMyYTQ4MS52aWR5by5pbwA2Mzc1NTQ0MzgwNAAAM2ZmM2Y3NTZiMmI4OTk3YTIzYjU4ODc3N2FiY2U3MWVkM2RhZGRmYjQ5NmI4MTg2YTUzZjU2M2YwYWQyYzYyYzc1NjQ0MDZiNjdhNjMxNDE5MjIyMzZmMWEwNjA4ODBi";
        vc.connect("prod.vidyo.io", token, "DemoUser", "DemoRoom", this);
    }

    public void disconnect(View v) {
        vc.disconnect();
    }

    public void onSuccess() {
        TextView text = (TextView)findViewById(R.id.textView1);
        text.setText("conncted");
    }

    public void onFailure(Connector.ConnectorFailReason reason) {
        TextView text = (TextView)findViewById(R.id.textView1);
        text.setText("failed");

    }

    public void onDisconnected(Connector.ConnectorDisconnectReason reason){
        TextView text = (TextView)findViewById(R.id.textView1);
        text.setText("disconncted");
    }
}
