package com.folautech.graphql.chat;

import com.folautech.graphql.dto.ChatDTO;

public interface ChatService {
    ChatDTO getById(Long id);
}
