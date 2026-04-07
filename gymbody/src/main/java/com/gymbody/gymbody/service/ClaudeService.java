package com.gymbody.gymbody.service;
import org.springframework.beans.factory.annotation.Value;

import com.gymbody.gymbody.dto.WorkoutRequestDto;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

@Service
public class ClaudeService {

    @Value("${claude.api.key}")
    private String apiKey;

    public String generateWorkout(WorkoutRequestDto request) {
        // Build the prompt
        String prompt = "You are a personal trainer. " +
                "Create a beginner workout plan for someone with the following profile:\n" +
                "- Body zone: " + request.getBodyZone() + "\n" +
                "- Goal: " + request.getGoal() + "\n" +
                "- Level: " + request.getLevel() + "\n" +
                "Give 4-5 exercises with sets, reps and a short explanation for each.";

        // Build the request body
        String requestBody = "{"
                + "\"model\": \"claude-opus-4-5\","
                + "\"max_tokens\": 1024,"
                + "\"messages\": [{\"role\": \"user\", \"content\": \"" + prompt.replace("\n", "\\n") + "\"}]"
                + "}";

        try {
            URL url = new URL("https://api.anthropic.com/v1/messages");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("x-api-key", apiKey);
            conn.setRequestProperty("anthropic-version", "2023-06-01");
            conn.setDoOutput(true);

            conn.getOutputStream().write(requestBody.getBytes());

            Scanner scanner = new Scanner(conn.getInputStream());
            StringBuilder response = new StringBuilder();
            while (scanner.hasNextLine()) {
                response.append(scanner.nextLine());
            }
            scanner.close();

            return response.toString();

        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
