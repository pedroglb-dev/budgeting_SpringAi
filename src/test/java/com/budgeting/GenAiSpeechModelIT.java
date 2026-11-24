package com.budgeting;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.ai.google.genai.transcription.GoogleGenAiTranscriptionModel;
import org.springframework.ai.google.genai.tts.GoogleGenAiTextToSpeechModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.file.Files;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EnabledIfEnvironmentVariable(named= "GENAI_API_KEY", matches = ".+")
public class GenAiSpeechModelIT {
    @Autowired
    private GoogleGenAiTextToSpeechModel googleGenAiTextToSpeechModel;


    @Test
    public void should_produceAudio_when_textIsProvided() throws IOException {
        var response = googleGenAiTextToSpeechModel.call("O valor total do serviço ficou em 70 reais posso confirmar o pagamento?");

        assertThat(response).hasSizeGreaterThan(1024);

        var tempFile = Files.createTempFile("AUDIO_", ".wav");
            Files.write(tempFile, response);
            System.out.println(tempFile.toAbsolutePath());
    }
}
