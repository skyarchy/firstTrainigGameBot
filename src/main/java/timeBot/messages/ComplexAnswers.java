package timeBot.messages;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import timeBot.Utils.CreateButtonsInline;
import timeBot.mainbot.BotService;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ComplexAnswers {

    private final BotService bot;
    private final CreateButtonsInline createButtons;


    public void buttonAnswer(Message msg){
        List<String> buttonNames = new ArrayList<>();
        List<String> buttonAnswers = new ArrayList<>();
        buttonNames.add("123");
        buttonNames.add("321");
        buttonNames.add("132");
        buttonNames.add("312");
        buttonNames.add("111");
        buttonNames.add("222");
        buttonNames.add("333");
        buttonNames.add("122");
        buttonNames.add("133");
        buttonNames.add("211");
        buttonAnswers.add("buttonRequest1");
        buttonAnswers.add("buttonRequest2");
        buttonAnswers.add("buttonRequest3");
        buttonAnswers.add("buttonRequest4");
        buttonAnswers.add("buttonRequest5");
        buttonAnswers.add("buttonRequest6");
        buttonAnswers.add("buttonRequest7");
        buttonAnswers.add("buttonRequest8");
        buttonAnswers.add("buttonRequest9");
        buttonAnswers.add("buttonRequest10");
        bot.sendMessageBase(true, true,  msg.getChatId(), createButtons.createNewButtons(buttonNames, buttonAnswers, 3), "answer with buttons");

    }
}
