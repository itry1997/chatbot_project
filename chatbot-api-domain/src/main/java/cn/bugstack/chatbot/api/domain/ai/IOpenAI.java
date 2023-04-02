package cn.bugstack.chatbot.api.domain.ai;

import java.io.IOException;

/**
 * @author cui
 * @create 2023--04--02--9:34
 */
public interface IOpenAI {
    String doChatGPT(String question) throws IOException;
}
