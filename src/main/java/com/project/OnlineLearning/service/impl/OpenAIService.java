package com.project.OnlineLearning.service.impl;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class OpenAIService {

    private static final String API_KEY = "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";
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";

    public String getExplanation(String question, String correctAnswer) {
        String prompt = "Explain why the correct answer to the question: '" + question + "' is '" + correctAnswer + "'.";

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + API_KEY)
                .POST(HttpRequest.BodyPublishers.ofString(buildRequestBody(prompt)))
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                JSONObject jsonObject = new JSONObject(response.body());
                JSONArray choices = jsonObject.getJSONArray("choices");
                if (choices.length() > 0) {
                    JSONObject firstChoice = choices.getJSONObject(0);
                    JSONObject message = firstChoice.getJSONObject("message");
                    String explanation = message.getString("content");
                    return explanation.trim();
                } else {
                    return "No explanation provided in the response.";
                }
            } else {
                return "The API returned an error: " + response.statusCode();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "An error occurred while trying to generate the explanation.";
        }
    }


    private String buildRequestBody(String prompt) {
        return "{\"model\": \"gpt-3.5-turbo\", \"messages\": [{\"role\": \"system\", \"content\": \"" + prompt + "\"}]}";
    }
}
