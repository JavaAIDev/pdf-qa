package com.javaaidev.pdfqa;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QaController {

  private final ChatClient chatClient;

  public QaController(ChatClient.Builder builder,
      QuestionAnswerAdvisor questionAnswerAdvisor,
      SimpleLoggerAdvisor simpleLoggerAdvisor) {
    this.chatClient = builder.defaultAdvisors(
        questionAnswerAdvisor,
        simpleLoggerAdvisor).build();
  }

  @PostMapping("/qa")
  public QaResponse qa(@RequestBody QaRequest request) {
    return new QaResponse(chatClient.prompt().user(request.input()).call().content());
  }

  public record QaRequest(String input) {

  }

  public record QaResponse(String output) {

  }
}
