package com.budgeting;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.ai.audio.transcription.TranscriptionModel;
import org.springframework.ai.google.genai.transcription.GoogleGenAiAudioTranscriptionOptions;
import org.springframework.ai.google.genai.transcription.GoogleGenAiTranscriptionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EnabledIfEnvironmentVariable(named= "GENAI_API_KEY", matches = ".+")
public class GenAiTranscriptionModelIT {
    @Autowired
    private GoogleGenAiTranscriptionModel googleGenAiTranscriptionModel;

    @ParameterizedTest
    @CsvSource({
            "Fui comprar pão e acabei gasta...m4a, R$ 40 ",
            "fui na farmacia rapidinho e de...m4a, R$ 80 ",
            "fui no cinema comer um combo d...m4a, R$ 90 ",
            "Paguei hoje 60 reais de estaci...m4a, R$ 60 ",
            "pedi um delivery agora e a con...m4a, R$ 120 ",
            "Sai para jantar ontem e a cont...m4a, R$ 200 ",
    })
    public void should_containKeyWords_when_audioFilesAreProcessed(String fileName, String expectedKeyWord){

    var recording = new ClassPathResource("audio/" + fileName);

//    var options = GoogleGenAiAudioTranscriptionOptions.builder()
//            .model()
//            .temperature()
//            .prompt()
//            .language()
//            .build();  tambem da pra instanciar o transcriprion pelo builder sem ser direto pelo autoconfig

   var response = googleGenAiTranscriptionModel.transcribe(recording);

   assertThat(response).isNotEmpty();
   assertThat(response).contains(expectedKeyWord);
   System.out.println(response);

    }
}
