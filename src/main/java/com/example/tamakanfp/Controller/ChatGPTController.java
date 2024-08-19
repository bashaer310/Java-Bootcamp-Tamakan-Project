package com.example.tamakanfp.Controller;
import com.example.tamakanfp.DTO.ChatMessagePrompt;
import org.springframework.web.bind.annotation.*;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.service.OpenAiService;
@RestController
@RequestMapping("/api/v1/chatgpt")
public class ChatGPTController {

        @PostMapping("/chat")
        public String getChatMessages(@RequestBody ChatMessagePrompt prompt) {

            OpenAiService service = new OpenAiService("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");
            ChatCompletionRequest completionRequest = ChatCompletionRequest.builder().messages(prompt.getChatMessage())
                    .model("gpt-3.5-turbo-16k").build();
            return service.createChatCompletion(completionRequest).getChoices().get(0).getMessage().getContent();
        }

    }

