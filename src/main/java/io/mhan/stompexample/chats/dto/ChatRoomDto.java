package io.mhan.stompexample.chats.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.mhan.stompexample.chats.entity.ChatRoom;
import io.mhan.stompexample.users.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomDto {

    @JsonProperty("chat_room_id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("owner")
    private UserDto owner;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    public static ChatRoomDto fromChatRoom(ChatRoom chatRoom) {
        UserDto userDto = UserDto.fromUser(chatRoom.getOwner());

        ChatRoomDto chatRoomDto = ChatRoomDto.builder()
                .id(chatRoom.getId())
                .name(chatRoom.getName())
                .owner(userDto)
                .createdAt(chatRoom.getCreatedAt())
                .updatedAt(chatRoom.getUpdatedAt())
                .build();

        return chatRoomDto;
    }
}
