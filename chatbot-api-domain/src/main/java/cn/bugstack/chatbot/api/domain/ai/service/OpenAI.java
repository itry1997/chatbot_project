package cn.bugstack.chatbot.api.domain.ai.service;

import cn.bugstack.chatbot.api.domain.ai.IOpenAI;
import cn.bugstack.chatbot.api.domain.ai.model.aggregates.AIAnswer;
import cn.bugstack.chatbot.api.domain.ai.model.vo.Choices;
import com.alibaba.fastjson.JSON;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author cui
 * @create 2023--04--02--9:37
 */

@Service
public class OpenAI implements IOpenAI {

    private Logger logger = LoggerFactory.getLogger(OpenAI.class);

    // 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
    @Value("${chatbot-api.openAIKey}")
    private String openAIKey;

    @Override
    public String doChatGPT(String question) throws IOException {

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
//        HttpPost post = new HttpPost("https://api.openai.com/v1/chat/completions");
        // 使用了代理
        HttpPost post = new HttpPost("https://open.aiproxy.xyz/v1/chat/completions");
        post.addHeader("Content-Type","application/json");
        post.addHeader("Authorization","Bearer " + openAIKey);

        String paramJson = "{\n" +
                "     \"model\": \"gpt-3.5-turbo\",\n" +
                "     \"messages\": [{\"role\": \"user\", \"content\": \""+ question +"\"}],\n" +
                "     \"temperature\": 0.7\n" +
                "   }";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json","UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            String jsonStr = EntityUtils.toString(response.getEntity());
            AIAnswer aiAnswer = JSON.parseObject(jsonStr, AIAnswer.class);
            StringBuilder answers = new StringBuilder();
            List<Choices> choices = aiAnswer.getChoices();

            // JSON格式发生了变化 代码需要重构
            for (Choices choice:choices){
                answers.append(choice.getMessage().getContent());
            }
            return answers.toString();

        }else{
            throw new RuntimeException("open.aiproxy.xyz Error Code is " + response.getStatusLine().getStatusCode());
        }
    }

}
