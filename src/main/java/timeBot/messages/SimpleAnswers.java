package timeBot.messages;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import timeBot.mainbot.BotService;

@Service
@AllArgsConstructor
public class SimpleAnswers {

    private final BotService bot;


    public void hiAnswer(Message msg){
        // простой пример ответа без кнопок
        bot.sendMessageBase(true, true, msg.getChatId(), null, "Здароа кожанный мешок!");
    }

}
