package io.mhan.stompexample.chats.repository;

import io.mhan.stompexample.chats.entity.ChatUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatUserRepository extends JpaRepository<ChatUser, Long> {
}
