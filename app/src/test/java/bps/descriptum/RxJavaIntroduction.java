package bps.descriptum;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import io.reactivex.Observable;
import static org.junit.Assert.assertNotEquals;

//similar to https://gist.github.com/staltz/868e7e9bc2a7b8c1f754#the-introduction-to-reactive-programming-youve-been-missing
public class RxJavaIntroduction {

    private final ExecutorService pool = Executors.newFixedThreadPool(10);
    Observable<String> requestStream = Observable.just("https://api.github.com/users");

    @Test
    public void mappingStreamToStream(){
        Observable<String> downloadedStream = requestStream.map( s -> downloadContents(s));
        String result = downloadedStream.blockingFirst();
        System.out.println(result);
        assertNotEquals("", result);
    }

    @Test
    public void mappingStreamFromFuture(){
        //map would create a metastream as new branches of streams are created with Futures,
        //flatMap emits them on the 'trunk' stream
        Observable<String> downloadedStream = requestStream.flatMap(s -> Observable.fromFuture(startDownloading(s)));
        String result = downloadedStream.blockingFirst();
        assertNotEquals("", result);
    }

    public Future<String> startDownloading(final String location){
        return pool.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return downloadContents(location);
            }
        });
    }

    public String downloadContents(String location) throws Exception{
        URL url = new URL(location);
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        String line = reader.readLine();
        return line;
    }
}
