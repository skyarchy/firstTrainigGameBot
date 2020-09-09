package timeBot.mainbot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageMedia;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import timeBot.Utils.MessageReceived;

import java.io.File;


@Component
public class Bot extends TelegramLongPollingBot implements BotService {

    @Value("${bot.token}")
    private String token;

    @Value("${bot.name}")
    private String name;

    private final MessageReceived messageReceived;

    public Bot(MessageReceived messageReceived) {
        this.messageReceived = messageReceived;
    }


    @Override
    public synchronized void sendMessageBase(boolean html, boolean disableWebPreviw, Long chatId, InlineKeyboardMarkup inlineKeyboardMarkup, String text) {
        SendMessage sendMessage = new SendMessage()
                .enableHtml(html)
                .setChatId(chatId)
                .setReplyMarkup(inlineKeyboardMarkup)
                .setText(text);
        if (disableWebPreviw)
            sendMessage.disableWebPagePreview();
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
//            System.out.println(e);
        }
    }

    @Override
    public synchronized void editMessageBase(boolean html, boolean disableWebPreviw, Long chatId, Integer messageId, InlineKeyboardMarkup inlineKeyboardMarkup, String text) {
        EditMessageText editMessage = new EditMessageText()
                .enableHtml(html)
                .setChatId(chatId)
                .setMessageId(messageId)
                .setText(text)
                .setReplyMarkup(inlineKeyboardMarkup);
        if (disableWebPreviw)
            editMessage.disableWebPagePreview();

        try {
            execute(editMessage);
        } catch (TelegramApiException e) {
//            System.out.println(e);
        }
    }

    @Override
    public synchronized void sendPhotoBase(Long chatId, String imageCaption, String photo, File filePhoto, InlineKeyboardMarkup inlineKeyboardMarkup) {
        SendPhoto sendPhoto = new SendPhoto()
                .setChatId(chatId)
                .setCaption(imageCaption)
                .setPhoto(photo)
                .setReplyMarkup(inlineKeyboardMarkup);
        if (filePhoto != null)
            sendPhoto.setPhoto(filePhoto);
        try {
            execute(sendPhoto);
        } catch (TelegramApiException e) {
//            System.out.println(e);
        }
    }

    @Override
    public synchronized void editPhotoMessageBase(Long chatId, Integer messageId, String imageCaption, String photo, InlineKeyboardMarkup inlineKeyboardMarkup) {
        EditMessageMedia editMessageMedia = new EditMessageMedia();
        InputMediaPhoto inputMediaPhoto = new InputMediaPhoto();
        inputMediaPhoto.setMedia(photo).setCaption(imageCaption);
        editMessageMedia.setMedia(inputMediaPhoto);
        editMessageMedia.setChatId(chatId);
        editMessageMedia.setMessageId(messageId);
        editMessageMedia.setReplyMarkup(inlineKeyboardMarkup);
        try {
            execute(editMessageMedia);
        } catch (TelegramApiException e) {
//            System.out.println(e);
        }
    }

    @Override
    public synchronized void callBackNotice(String id, String text) {
        try {
            execute(new AnswerCallbackQuery()
                    .setShowAlert(false)
                    .setText(text)
                    .setCallbackQueryId(id));
        } catch (TelegramApiException e) {
//            System.out.println(e);
        }
    }

    @Override
    public synchronized void deleteMsg(int messageId, String chatId) {
        try {
            execute(new DeleteMessage()
                    .setChatId(chatId)
                    .setMessageId(messageId));
        } catch (TelegramApiException e) {
//            System.out.println(e);
        }
    }

    @Override
    public synchronized void sendReplyBase(boolean html, boolean disableWebPreviw, Long chatId, InlineKeyboardMarkup inlineKeyboardMarkup, String text, String replyMessageId) {
        SendMessage sendMessage = new SendMessage()
                .setReplyToMessageId(Integer.valueOf(replyMessageId))
                .enableHtml(html)
                .setChatId(chatId)
                .setReplyMarkup(inlineKeyboardMarkup)
                .setText(text);
        if (disableWebPreviw)
            sendMessage.disableWebPagePreview();
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
//            System.out.println(e);
        }
    }

    public void onUpdateReceived(Update update) {
        messageReceived.updatedMessage(update);
    }

    public String getBotUsername() {
        return name;
    }

    public String getBotToken() {
        return token;
    }


}
