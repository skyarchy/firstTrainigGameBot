package timeBot.messages;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import timeBot.configurations.properties.BotProperties;
import timeBot.mainbot.BotService;

@Service
@AllArgsConstructor
public class SimpleAnswers {

    private final BotService bot;
    private final BotProperties properties;

    // класс простых ответов бота

    public void hiAnswer(Message msg){
        // простой пример ответа без кнопок
        bot.sendMessageBase(true, true, msg.getChatId(), null, "Здароа кожанный мешок!");
    }

    public void testAnswer(Message msg){
        // тестовый ответ для проверки любого функционала


        String text = "not work";
        if(properties.isDod()){
             text = "work";
        }
        bot.sendMessageBase(true, true, msg.getChatId(), null, text);
    }

}
