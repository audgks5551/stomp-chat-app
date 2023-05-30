package io.mhan.stompexample.chats.entity;

import io.mhan.stompexample.users.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = LAZY)
    private User owner;

    @OneToMany(mappedBy = "chatRoom", cascade = PERSIST)
    @Builder.Default
    private Set<ChatUser> chatUsers = new HashSet<>();

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


    public static ChatRoom create(String name, User owner) {

        Assert.notNull(name, "name는 널일 수 없습니다.");
        Assert.notNull(owner, "owner는 널일 수 없습니다.");

        ChatRoom chatRoom = ChatRoom.builder()
                .name(name)
                .owner(owner)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        return chatRoom;
    }

    public void addChatUser(User owner) {
        ChatUser chatUser = ChatUser.builder()
                .user(owner)
                .chatRoom(this)
                .build();
        chatUsers.add(chatUser);
    }
}
