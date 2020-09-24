package timeBot.Utils;


import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import timeBot.messages.ComplexAnswers;
import timeBot.messages.SimpleAnswers;

@Component
public class SpringConflictsRetran {

    private SimpleAnswers simpleAnswers;
    private ComplexAnswers complexAnswers;

    public SpringConflictsRetran (@Lazy SimpleAnswers simpleAnswers, @Lazy ComplexAnswers complexAnswers){
        this.simpleAnswers = simpleAnswers;
        this.complexAnswers = complexAnswers;
    }

    public void hiAnswer(Message msg){
        simpleAnswers.hiAnswer(msg);
    }

    public void testAnswer(Message msg){
        simpleAnswers.testAnswer(msg);
    }

    public void buttonAnswer(Message msg) {
        complexAnswers.buttonAnswer(msg);
    }


}
