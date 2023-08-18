package org.bot.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;

@Component
@Log4j
public class TelegramBot extends TelegramLongPollingBot {
    @Value("${bot.name}")
    private String botName;
    @Value("${bot.token}")
    private String botToken;

    private String chatId = null;
    private UpdateController updateController;

    public TelegramBot(UpdateController updateController) {
        this.updateController = updateController;
    }

    @PostConstruct
    public void init(){
        this.updateController.registerBot(this);
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }


    @Override
    public void onUpdateReceived(Update update) {
        Message originalMessage = update.getMessage();

        if(originalMessage.getText().contains("/start")){
            this.chatId = originalMessage.getChatId().toString();

            sendNewMessage("Hello :)");
        } else {
            if(originalMessage.getText().contains("Dima")){
                sendNewMessage("Won otsuda");
            }else{
                checkAccount(originalMessage);
            }
        }
    }

    public void sendAnswerMessage (SendMessage message){
        if (message != null) {
            try {
                execute(message);
            } catch (TelegramApiException e){
                log.error(e);
            }
        }
    }
    public void sendNewMessage(String value){
        SendMessage newMessage01 = new SendMessage();

        newMessage01.setChatId(this.chatId);
        newMessage01.setText(value);

        sendAnswerMessage(newMessage01);
    }
    public void checkAccount(Message message){
        if(message.getText().equals("mops")){
            log.debug("Unlocked: " + message.getText());
            sendNewMessage("You have been unlocked...");
        } else {
            log.debug(message.getText());
            sendNewMessage("Wrong key...");
        }
    }
}
