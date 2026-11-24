package com.budgeting;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.google.genai.GoogleGenAiChatModel;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EnabledIfEnvironmentVariable(named= "GENAI_API_KEY", matches = ".+")
public class ToolCallingIT {
    @Autowired
    private GoogleGenAiChatModel googleGenAiChatModel;

    static class MathTools{
        @Tool(description = "Soma dois numeros inteiros a e b")
        public int sum(int a, int b){
            return a+b;
        }
        @Tool(description = "Subtrai dois numeros inteiros a e b")
        public int diff(int a, int b){
            return a-b;
        }
    }

    @Test
    void should_executeSum_when_prompted(){
        var chatClient = ChatClient.builder(googleGenAiChatModel).defaultSystem("Voce eh um matematico")
                .defaultTools(new MathTools())
                .build();

        var response = chatClient.prompt("soma 10 mais 20 e depois subtraia 30 do resultado anterior e exiba o resultado sem mais explicacoes")
                .call().content(); // o chatclient eh uma interface centrada em volta de um chatmodel que disponibiliza mais possibilidades de configuracao, e nesse caso ele permite diversidade nas configs do prompt para so entao chamar o metodo call do chatmodel e pegar o conteudo


        assertThat(response).contains("0");
        System.out.println(response);
    }
}

// ToolCalling eh uma ferramnta do ecosistema do springAI que permite com que possamos dar ferramentas ao modelo ao inves de deixar a tarefa a mercer da LLM, nesse caso de teste de integracao ao inves da LLM resolver a conta sozinha ela chama a tool conforme achar necessario inferindo o contexto de acordo com o prompt e a descricao da mesma tool.

// voce cria a classe que vai comportar os metodos que nesse caso sao tools ai quando voce esta buildando o chatclient voce instancia a classe das tools dentro do metodo .defaultTools(new class()) ai o proprio chatclient vai ter acesso aos metodos da sua classe de tools e vai poder usar eles de acordo com o prompt.
