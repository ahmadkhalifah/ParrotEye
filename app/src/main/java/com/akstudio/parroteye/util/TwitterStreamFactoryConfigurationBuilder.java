package com.akstudio.parroteye.util;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterStreamFactoryConfigurationBuilder {

    public static TwitterStream getStream() {

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true);

        cb.setOAuthConsumerKey("LOYigNrnYAyGTVFfHcaJNhSAS");
        cb.setOAuthConsumerSecret("YIvxMLRF6by8vT2cD0oxktQwvZjpeoV5ssLOop2ZJRI6R84cXG");
        cb.setOAuthAccessToken("841193197476970496-bmHtnyEp027QfGzrc71q2yi0SgiWS7p");
        cb.setOAuthAccessTokenSecret("P5jLZpCq5xOBsWgt2oIf3azSaTSlv9RaiD5Rs7vLBjvS0");

        TwitterStreamFactory factory = new TwitterStreamFactory(cb.build());
        TwitterStream twitter = factory.getInstance();
        return twitter;

    }

}
