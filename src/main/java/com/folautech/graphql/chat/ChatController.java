package com.folautech.graphql.chat;

import com.folautech.graphql.dto.ChatDTO;
import com.folautech.graphql.message.Message;
import com.folautech.graphql.message.MessageRepository;
import com.folautech.graphql.model.User;
import com.folautech.graphql.utils.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.springframework.http.HttpStatus.OK;

@Slf4j
@Controller
public class ChatController {

    @Autowired
    ChatRepository chatRepository;
    @Autowired
    MessageRepository messageRepository;

    @SchemaMapping(typeName = "Query",value = "allChats")
    public List<Chat> getAllChats() {
        log.info("getAllChats");
        return chatRepository.findAll();
    }

    @SchemaMapping(typeName = "Query",value = "getMessagesByChatId")
    public List<Message> getMessagesByChatId(@Argument Long id) {
        log.info("getMessagesByChatId, id={}",id);
        return messageRepository.findByChatId(id);
    }

//    @QueryMapping
//    public List<Message> getMessagesByChatId(@Argument Long id) {
//        return messageRepository.findByChatId(id);
//    }

//    @SubscriptionMapping
//    public List<Message> getMessagesForChat() {
//        Long id = 1L;
//        log.info("getMessagesFoChat, id={}",id);
//        return messageRepository.findByChatId(id);
//    }
    @SubscriptionMapping
    public Flux<Message> getMessagesForChat() {
        Long id = 1L;
        log.info("getMessagesFoChat, id={}",id);
        return Flux.fromStream(Stream.generate(
                new Supplier<Message>() {
                    @Override
                    public Message get() {
                        return new Message("heyhey",new User(1L));
                    }
                }
        )).delayElements(Duration.ofSeconds(1))
                .take(10);
    }


}
