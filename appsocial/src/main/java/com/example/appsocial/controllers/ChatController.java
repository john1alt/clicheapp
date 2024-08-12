package com.example.appsocial.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.appsocial.models.User;
import com.example.appsocial.services.ChatService;

@Controller
public class ChatController {
	@Autowired
    private ChatService chatService;

	/*
	 * @GetMapping("/chat") public String getChat(Model model, Principal principal)
	 * { User currentUser = userService.getUserByUsername(principal.getName());
	 * List<ChatMessage> messages = chatService.getMessageHistory(currentUser);
	 * model.addAttribute("messages", messages); return "chat"; }
	 */
    
	/*
	 * @MessageMapping("/send-message") public void sendMessage(@Payload ChatMessage
	 * message, Principal principal) { User sender =
	 * userService.getUserByUsername(principal.getName());
	 * chatService.sendMessage(sender, message.getRecipient(),
	 * message.getContent()); }
	 */
}
