package io.mhan.stompexample.chats.controller;

import io.mhan.stompexample.chats.dto.ChatRoomDto;
import io.mhan.stompexample.chats.entity.ChatRoom;
import io.mhan.stompexample.chats.service.ChatRoomService;
import io.mhan.stompexample.security.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @GetMapping("/rooms")
    public String showRooms(Model model) {

        List<ChatRoom> chatRooms = chatRoomService.findAll();

        model.addAttribute("chatRooms", chatRooms);
        return "rooms";
    }

    @GetMapping("/rooms/{roomId}")
    public String showRoom(@PathVariable Long roomId, Model model, @AuthenticationPrincipal SecurityUser securityUser) {

        ChatRoomDto chatRoomDto = chatRoomService.getByIdAndUserId(roomId, securityUser.getId());

        model.addAttribute("chatRoom", chatRoomDto);

        return "room";
    }

    @GetMapping("/rooms/new")
    public String showNewRoom() {
        return "newRoom";
    }

    @PostMapping("/rooms")
    public String newRoom(String roomName, @AuthenticationPrincipal SecurityUser securityUser) {
        ChatRoom chatRoom = chatRoomService.createAndSave(roomName, securityUser.getId());

        return "redirect:/rooms/" + chatRoom.getId();
    }
}
