package com.folautech.graphql.chat;

import com.folautech.graphql.dto.ChatDTO;
import com.folautech.graphql.message.Message;
import com.folautech.graphql.message.MessageRepository;
import com.folautech.graphql.utils.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ChatServiceImp implements ChatService{

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public ChatDTO getById(Long id) {
        Chat chat = chatRepository.findById(id).orElse(new Chat());

        System.out.println(ObjectUtils.toJson(chat));

        ChatDTO chatDTO = new ChatDTO();
        chatDTO.setId(chat.getId());
        chatDTO.setTitle(chat.getTitle());
        chatDTO.setUuid(chat.getUuid());
//        chatDTO.setMessages(chat.getMessages());

        List<Message> messages = messageRepository.findByChatId(id);
        chatDTO.setMessages(messages);

        return chatDTO;
    }

}
