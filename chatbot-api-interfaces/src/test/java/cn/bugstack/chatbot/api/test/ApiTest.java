package cn.bugstack.chatbot.api.test;


import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import javax.swing.text.html.parser.Entity;
import java.io.IOException;

public class ApiTest {

    @Test
    public void query_unanswered_questions() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/28885822144141/topics?scope=unanswered_questions&count=20");
        get.addHeader("cookie","__cuid=93e78872cbf94b94af3c4ed2b184d4cd; amp_fef1e8=e2b14c3b-213c-4c30-aa8b-90a985b81a09R...1gsqm3dar.1gsqm4qkd.2.1.3; zsxq_access_token=B227DD67-B121-FD77-FB66-6CAFDA78AD50_5B727D7E22D63632; abtest_env=product; zsxqsessionid=be28da9c61d55b0bc3b4cf4f418c6a8d; sensorsdata2015jssdkcross={\"distinct_id\":\"1873563883fd25-0c038b445d065d-7a545471-1327104-18735638840a7d\",\"first_id\":\"\",\"props\":{},\"$device_id\":\"1873563883fd25-0c038b445d065d-7a545471-1327104-18735638840a7d\"}; sajssdk_2015_cross_new_user=1");
        get.addHeader("Content-Type","application/json; charset=UTF-8");

        CloseableHttpResponse response = httpClient.execute(get);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        }else{
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }

    @Test
    public void answer() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // uri中的数字是 要回答的unanswered_questions的topic_id
        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/181455442428452/answer");
        post.addHeader("cookie","__cuid=93e78872cbf94b94af3c4ed2b184d4cd; amp_fef1e8=e2b14c3b-213c-4c30-aa8b-90a985b81a09R...1gsqm3dar.1gsqm4qkd.2.1.3; zsxq_access_token=B227DD67-B121-FD77-FB66-6CAFDA78AD50_5B727D7E22D63632; abtest_env=product; zsxqsessionid=be28da9c61d55b0bc3b4cf4f418c6a8d; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%221873563883fd25-0c038b445d065d-7a545471-1327104-18735638840a7d%22%2C%22first_id%22%3A%22%22%2C%22props%22%3A%7B%7D%2C%22%24device_id%22%3A%221873563883fd25-0c038b445d065d-7a545471-1327104-18735638840a7d%22%7D; sajssdk_2015_cross_new_user=1");
        post.addHeader("Content-Type","application/json; charset=UTF-8");

        // text中的文字是回答的内容
        String paramJson = "{\n" +
                "  \"req_data\": {\n" +
                "    \"text\": \"建议去查看JavaGuide\\n\",\n" +
                "    \"image_ids\": []\n" +
                "  }\n" +
                "}";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json","UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        }else{
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }

    @Test
    public void test_chatGPT() throws IOException{
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
//        HttpPost post = new HttpPost("https://api.openai.com/v1/chat/completions");
        // 使用了代理
        HttpPost post = new HttpPost("https://open.aiproxy.xyz/v1/chat/completions");
        post.addHeader("Content-Type","application/json");
        post.addHeader("Authorization","Bearer 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");

        String paramJson = "{\n" +
                "     \"model\": \"gpt-3.5-turbo\",\n" +
                "     \"messages\": [{\"role\": \"user\", \"content\": \"介绍一下哈尔滨\"}],\n" +
                "     \"temperature\": 0.7\n" +
                "   }";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json","UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        }else{
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }
}
