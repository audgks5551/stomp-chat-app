package io.mhan.stompexample.chats.repository;

import io.mhan.stompexample.chats.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
}
