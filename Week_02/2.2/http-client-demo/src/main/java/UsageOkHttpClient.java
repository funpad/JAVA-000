import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class UsageOkHttpClient {

    public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(100L, TimeUnit.MILLISECONDS)
                .callTimeout(1L, TimeUnit.SECONDS)
                .readTimeout(1L, TimeUnit.SECONDS)
                .build();

        Request request = new Request.Builder()
                .url("http://localhost:8801").get().build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                // handle failed request
                System.out.println(response.toString());
            }
            String responseBody = Objects.requireNonNull(
                    response.body()).string();
            // do something with response
            System.out.println("Content: \n" + responseBody);

        } catch (IOException e) {
            // handle IO exception
            e.printStackTrace();
        }

    }
}
