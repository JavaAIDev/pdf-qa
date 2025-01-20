package com.javaaidev.pdfqa;

import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

  @Bean
  public QuestionAnswerAdvisor questionAnswerAdvisor(VectorStore vectorStore) {
    return new QuestionAnswerAdvisor(vectorStore, SearchRequest.builder().topK(3).build());
  }

  @Bean
  public PDFContentLoader pdfContentLoader(VectorStore vectorStore) {
    return new PDFContentLoader(vectorStore);
  }
}
