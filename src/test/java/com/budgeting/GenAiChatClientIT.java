package com.budgeting;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.google.genai.GoogleGenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EnabledIfEnvironmentVariable(named= "GENAI_API_KEY", matches = ".+")
public class GenAiChatClientIT {
    @Autowired
    private GoogleGenAiChatModel googleGenAiChatModel;

    @Test
    void should_executeSum_when_prompted(){
        var chatClient = ChatClient.builder(googleGenAiChatModel).defaultSystem("Voce eh um matematico").build();

        var response = chatClient.prompt("soma 10 mais 20 e depois subtraia 30 do resultado anterior e exiba o resultado sem mais explicacoes").call().content(); // o chatclient disponibiliza mais possibilidades de configuracao, e nesse caso ele permite diversidade nas configs do prompt para so entao chamar o metodo call do chatmodel e pegar o conteudo

        assertThat(response).contains("0");
        System.out.println(response);
    }
}
