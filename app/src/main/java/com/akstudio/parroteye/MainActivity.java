package com.akstudio.parroteye;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.akstudio.parroteye.util.TwitterStreamFactoryConfigurationBuilder;

import java.util.ArrayList;

import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

public class MainActivity extends ListActivity {

    //LIST OF ARRAY STRINGS WHICH WILL SERVE AS LIST ITEMS
    ArrayList<String> listItems = new ArrayList<String>();

    //DEFINING A STRING ADAPTER WHICH WILL HANDLE THE DATA OF THE LISTVIEW
    ArrayAdapter<String> adapter;

    //RECORDING HOW MANY TIMES THE BUTTON HAS BEEN CLICKED
    int clickCounter = 0;

    TwitterStream twitterStream;

    boolean clicked = false;

    Button btnScan;
    EditText edtSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnScan = (Button) findViewById(R.id.btnScan);
        edtSearch = (EditText) findViewById(R.id.edtSearch);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                listItems);
        setListAdapter(adapter);

    }

    //METHOD WHICH WILL HANDLE DYNAMIC INSERTION
    public void addItems(View v) {
        // Start listening
        if (!clicked) {
            streamStart();
            clicked = true;
            btnScan.setText("STOP");
            listItems.clear();
            adapter.notifyDataSetChanged();
        } else {
            stopStream();
            clicked = false;
            btnScan.setText("START");
        }
    }

    public void stopStream() {
        // Start listening
        twitterStream.cleanUp(); // shutdown internal stream consuming thread
        twitterStream.shutdown(); // Shuts down internal dispatcher thread shared by all TwitterStream instances
    }

    public void addItem(String item)
    {
        listItems.add(0, item);
        adapter.notifyDataSetChanged();
    }

    private void streamStart() {

        StatusListener listener = new StatusListener() {
            public void onStatus(Status status) {
                // Insert a tweet
                //listItems.add(new ParrotEye().parrotEye(status));
                addItem(new ParrotEye().parrotEye(status));
                //System.out.println("*************************" + status.getText());
            }

            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
            }

            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
            }

            public void onException(Exception ex) {
                ex.printStackTrace();
            }

            @Override
            public void onScrubGeo(long arg0, long arg1) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStallWarning(StallWarning arg0) {
                // TODO Auto-generated method stub
            }
        };
        //TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
        twitterStream = new TwitterStreamFactoryConfigurationBuilder().getStream();
        //TwitterStream twitterStream = twitterStream();

        FilterQuery fq = new FilterQuery();

        System.out.println("******************************" + edtSearch.getText().toString());
        String keywords[] = {edtSearch.getText().toString()};
        fq.track(keywords);

        twitterStream.addListener(listener);
        twitterStream.filter(fq);

    }

    public TwitterStream twitterStream() {
        String consumerKey = "LOYigNrnYAyGTVFfHcaJNhSAS";
        String consumerSecret = "YIvxMLRF6by8vT2cD0oxktQwvZjpeoV5ssLOop2ZJRI6R84cXG";
        String accessToken = "841193197476970496-bmHtnyEp027QfGzrc71q2yi0SgiWS7p";
        String accessTokenSecret = "P5jLZpCq5xOBsWgt2oIf3azSaTSlv9RaiD5Rs7vLBjvS0";

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setOAuthConsumerKey(consumerKey);
        cb.setOAuthConsumerSecret(consumerSecret);
        cb.setOAuthAccessToken(accessToken);
        cb.setOAuthAccessTokenSecret(accessTokenSecret);
        cb.setJSONStoreEnabled(true);
        cb.setIncludeEntitiesEnabled(true);

        return new TwitterStreamFactory(cb.build()).getInstance();
    }
}
