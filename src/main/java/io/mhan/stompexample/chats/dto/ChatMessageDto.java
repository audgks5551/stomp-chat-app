package io.mhan.stompexample.chats.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.mhan.stompexample.chats.entity.ChatMessage;
import io.mhan.stompexample.chats.entity.ChatMessageType;
import io.mhan.stompexample.users.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageDto {

    @JsonProperty("message_id")
    private Long id;

    @JsonProperty("content")
    private String content;

    @JsonProperty("sender")
    private UserDto sender;

    @JsonProperty("type")
    private ChatMessageType type;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    public static ChatMessageDto fromChatMessage(ChatMessage chatMessage) {

        UserDto userDto = UserDto.fromUser(chatMessage.getSender().getUser());

        ChatMessageDto chatMessageDto = ChatMessageDto.builder()
                .id(chatMessage.getId())
                .type(chatMessage.getType())
                .sender(userDto)
                .content(chatMessage.getContent())
                .type(chatMessage.getType())
                .createdAt(chatMessage.getCreatedAt())
                .updatedAt(chatMessage.getUpdatedAt())
                .build();

        return chatMessageDto;
    }

    public static List<ChatMessageDto> fromChatMessages(List<ChatMessage> messages) {
        return messages.stream()
                .map(ChatMessageDto::fromChatMessage)
                .toList();
    }
}
