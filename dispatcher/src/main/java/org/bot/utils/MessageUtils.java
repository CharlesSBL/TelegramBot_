package org.bot.utils;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class MessageUtils {
    public SendMessage generateSendMessageWithText(Update update, String text){
        var message01 = update.getMessage();
        var newSendMessage = new SendMessage();

        newSendMessage.setChatId(message01.getChatId().toString());
        newSendMessage.setText(text);

        return newSendMessage;
    }
}
