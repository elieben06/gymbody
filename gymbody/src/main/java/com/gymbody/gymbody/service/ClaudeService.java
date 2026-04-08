    package com.gymbody.gymbody.service;
    import org.springframework.beans.factory.annotation.Value;

    import com.gymbody.gymbody.dto.WorkoutRequestDto;
    import org.springframework.stereotype.Service;

    import java.net.HttpURLConnection;
    import java.net.URL;
    import java.util.Scanner;


    @Service
    public class ClaudeService {

        @Value("${groq.api.key}")
        private String apiKey;

        public String generateWorkout(WorkoutRequestDto request) {
            String prompt = "You are a personal trainer. " +
                    "Create a beginner workout plan for someone with the following profile:\n" +
                    "- Body zone: " + request.getBodyZone() + "\n" +
                    "- Goal: " + request.getGoal() + "\n" +
                    "- Level: " + request.getLevel() + "\n" +
                    "Give 4-5 exercises with sets, reps and a short explanation for each.";

            String promptEscaped = prompt
                    .replace("\\", "\\\\")
                    .replace("\"", "\\\"")
                    .replace("\n", "\\n")
                    .replace("\r", "\\r");

            String requestBody = "{"
                    + "\"model\": \"llama-3.3-70b-versatile\","
                    + "\"messages\": [{\"role\": \"user\", \"content\": \"" + promptEscaped + "\"}]"
                    + "}";

            HttpURLConnection conn = null;
            try {
                URL url = new URL("https://api.groq.com/openai/v1/chat/completions");
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("Authorization", "Bearer " + apiKey);
                conn.setDoOutput(true);
                conn.getOutputStream().write(requestBody.getBytes());

                Scanner scanner = new Scanner(conn.getInputStream());
                StringBuilder response = new StringBuilder();
                while (scanner.hasNextLine()) {
                    response.append(scanner.nextLine());
                }
                scanner.close();
                String jsonResponse = response.toString();
                String searchFor = "\"content\":\"";
                int contentStart = jsonResponse.indexOf(searchFor);
                if (contentStart == -1) return "Error: could not parse response";
                contentStart += searchFor.length();
                int contentEnd = jsonResponse.indexOf("\"}", contentStart);
                if (contentEnd == -1) return "Error: could not find end of content";
                String content = jsonResponse.substring(contentStart, contentEnd);
                content = content.replace("\\n", "\n")
                        .replace("\\t", "\t")
                        .replace("\\\"", "\"");
                return content;

            } catch (Exception e) {
                if (conn != null) {
                    try {
                        Scanner errorScanner = new Scanner(conn.getErrorStream());
                        StringBuilder errorResponse = new StringBuilder();
                        while (errorScanner.hasNextLine()) {
                            errorResponse.append(errorScanner.nextLine());
                        }
                        errorScanner.close();
                        return "API Error: " + errorResponse.toString();
                    } catch (Exception e2) {
                        return "Error reading error stream: " + e2.getMessage();
                    }
                }
                return "Error: " + e.getMessage();
            }
        }
    }
