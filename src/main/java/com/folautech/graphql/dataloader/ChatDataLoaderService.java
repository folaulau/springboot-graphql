package com.folautech.graphql.dataloader;

import com.folautech.graphql.chat.Chat;
import com.folautech.graphql.chat.ChatRepository;
import com.folautech.graphql.message.Message;
import com.folautech.graphql.message.MessageRepository;
import com.folautech.graphql.model.User;
import com.folautech.graphql.utils.RandomGeneratorUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

@DependsOn(value = {"userDataLoaderService"})
@Slf4j
@Component
public class ChatDataLoaderService {
    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private MessageRepository messageRepository;

    public void load(){
        loadChats();

        // load messages
        for (int i=1;i<=20;i++){
            Message message = Message.builder().build();
            message.setMessage("say this asdf"+RandomGeneratorUtils.getIntegerWithin(100,999999));
            message.setUser(User.builder()
                    .id(Long.valueOf(""+RandomGeneratorUtils.getIntegerWithin(1,3)))
                    .build());
            message.setChat(Chat.builder()
                    .id(Long.valueOf(""+RandomGeneratorUtils.getIntegerWithin(1,3)))
                    .build());
            messageRepository.saveAndFlush(message);
        }

        log.info("chat messages loaded!!!");
    }

    private void loadChats(){

        for (int i=1;i<=3;i++){
            Chat chat = Chat.builder()
                    .id(Long.valueOf(""+i))
                    .title("chat-"+i)
                    .build();
            chatRepository.saveAndFlush(chat);
        }
    }

}
