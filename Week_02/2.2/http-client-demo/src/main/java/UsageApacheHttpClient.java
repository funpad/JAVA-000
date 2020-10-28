import org.apache.commons.codec.Charsets;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class UsageApacheHttpClient {

    public static void main(String[] args) throws IOException {
        HttpGet httpGet = new HttpGet("http://localhost:8801");
        try (CloseableHttpResponse
                     response = HttpClients.createDefault().execute(httpGet)) {
            if (response != null && response.getStatusLine()
                    .getStatusCode() == HttpStatus.SC_OK) {
                String content = EntityUtils.toString(
                        response.getEntity(), Charsets.UTF_8);
                System.out.println("Content: \n" + content);
            } else {
                System.out.println("Error");
            }
        }
    }
}
