package io.mhan.stompexample.chats.service;

import io.mhan.stompexample.chats.dto.ChatRoomDto;
import io.mhan.stompexample.chats.entity.ChatRoom;
import io.mhan.stompexample.chats.repository.ChatRoomRepository;
import io.mhan.stompexample.users.UserService;
import io.mhan.stompexample.users.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final UserService userService;

    public ChatRoom createAndSave(String name, Long ownerId) {

        User owner = userService.findByIdElseThrow(ownerId);

        ChatRoom chatRoom = ChatRoom.create(name, owner);

        ChatRoom savedChatRoom = chatRoomRepository.save(chatRoom);

        chatRoom.addChatUser(owner);

        return savedChatRoom;
    }

    public List<ChatRoom> findAll() {
        return chatRoomRepository.findAll();
    }

    public ChatRoom findById(Long roomId) {
        return chatRoomRepository.findById(roomId).orElseThrow();
    }

    public ChatRoomDto getByIdAndUserId(Long roomId, Long userId) {
        ChatRoom chatRoom = findById(roomId);

        chatRoom.getChatUsers().stream()
                .filter(chatUser -> chatUser.getUser().getId().equals(userId))
                .findFirst()
                .orElseThrow();

        return ChatRoomDto.fromChatRoom(chatRoom);
    }
}
