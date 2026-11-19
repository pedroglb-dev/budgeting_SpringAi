package com.budgeting;

import com.google.genai.Client;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.ai.google.genai.GoogleGenAiChatModel;
import org.springframework.ai.google.genai.GoogleGenAiChatOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EnabledIfEnvironmentVariable(named= "GENAI_API_KEY", matches = ".+")
public class GenAiChatModelIT {

    @Autowired
    private GoogleGenAiChatModel googleGenAiChatModel;


    @Test
    void shouldReceiveResponseWhenChatModelIsCalled() {
//        var option = googleGenAiChatOptions.builder()
//                .model("gemini-3.8-flash")
//                .temperature(0.8)
//                .build();
//
//        var client = Client.builder()
//                .apiKey(System.getenv("GENAI_API_KEY"))
//                .build();
//
//        var chatModel = googleGenAiChatModel.builder()
//                .genAiClient(client)
//                .defaultOptions(option)
//                .build();

        var response =  googleGenAiChatModel.call("gere um relatorio de budget, com descricao de gasto, valor em reais e local");
        assertThat(response).isNotEmpty();
        System.out.println(response);


    }

}
