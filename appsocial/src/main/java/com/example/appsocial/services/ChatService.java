package com.example.appsocial.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.appsocial.models.User;

@Service
public class ChatService {
	/*
	 * @Autowired private ChatMessageRepository chatMessageRepository;
	 * 
	 * public void sendMessage(User sender, User recipient, String content) {
	 * ChatMessage message = new ChatMessage(); message.setSender(sender);
	 * message.setRecipient(recipient); message.setContent(content);
	 * message.setTimestamp(LocalDateTime.now());
	 * chatMessageRepository.save(message); // Broadcast the message to the
	 * recipient using WebSocket
	 * template.convertAndSendToUser(recipient.getUsername(), "/queue/messages",
	 * message); }
	 * 
	 * public List<ChatMessage> getMessageHistory(User user) { return
	 * chatMessageRepository.findBySenderOrRecipient(user, user); }
	 */
}
