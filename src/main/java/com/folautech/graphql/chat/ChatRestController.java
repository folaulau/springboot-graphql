package com.folautech.graphql.chat;

import com.folautech.graphql.dto.ChatDTO;
import com.folautech.graphql.message.Message;
import com.folautech.graphql.message.MessageCreateDTO;
import com.folautech.graphql.utils.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RequestMapping("/chats")
@RestController
public class ChatRestController {

    @Autowired
    ChatService chatService;

    @GetMapping
    public ResponseEntity<ChatDTO> getById(@RequestParam Long id) {
        log.info("getById id={}", ObjectUtils.toJson(id));

        ChatDTO chat = chatService.getById(id);

        return new ResponseEntity<>(chat, OK);
    }
}
