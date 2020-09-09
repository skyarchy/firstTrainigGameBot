package timeBot.Utils;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import timeBot.messages.SimpleAnswers;

@Service
@AllArgsConstructor
public class MessageReceived {

    private final SimpleAnswers simpleAnswers;


    public void updatedMessage(Update update) {

        if (update.getCallbackQuery() != null) {
            String text = update.getCallbackQuery().getData();

            if (text.contains("killerBean")) {
                //do code buttons code
            }

        }
        Message msg = update.getMessage();
        if (msg != null) {
            if (msg.getText().length() > 4) {
                // делаем все входящие сообщения строчными буквами
                String msgText = msg.getText().toLowerCase();

                if (msg.getText().contains("/hello")){
                    simpleAnswers.hiAnswer(msg);
                }
                //do code main message
            }
        }
    }
}
