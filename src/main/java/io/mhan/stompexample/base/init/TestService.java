package io.mhan.stompexample.base.init;

import io.mhan.stompexample.chats.entity.ChatRoom;
import io.mhan.stompexample.chats.service.ChatRoomService;
import io.mhan.stompexample.users.UserService;
import io.mhan.stompexample.users.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TestService {

    private final UserService userService;
    private final ChatRoomService chatRoomService;

    public void createTestData() {
        User user1 = userService.createAndSave("user1", "pass");
        User user2 = userService.createAndSave("user2", "pass");

        ChatRoom chatRoom1 = chatRoomService.createAndSave("환영합니다.1", user1.getId());
        ChatRoom chatRoom2 = chatRoomService.createAndSave("환영합니다.2", user2.getId());

        chatRoom1.addChatUser(user2);
        chatRoom2.addChatUser(user1);
    }
}
