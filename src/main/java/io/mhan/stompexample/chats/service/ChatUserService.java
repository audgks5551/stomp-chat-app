package io.mhan.stompexample.chats.service;

import io.mhan.stompexample.chats.entity.ChatUser;
import io.mhan.stompexample.chats.repository.ChatUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatUserService {

    private final ChatUserRepository chatRoomUserRepository;


    public ChatUser findById(Long chatRoomUserId) {
        return chatRoomUserRepository.findById(chatRoomUserId).orElseThrow();
    }
}
