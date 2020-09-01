package timeBot.Utils;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@AllArgsConstructor
public class MessageReceived {


    public void updatedMessage(Update update) {

        if (update.getCallbackQuery() != null) {
            String text = update.getCallbackQuery().getData();

            if (text.contains("getGWInfoFromBase")) {
                //do code buttons code
            }

        }
        Message msg = update.getMessage();
        if (msg != null) {
            if (msg.getText().length() > 4) {
                //do code main message
            }
        }
    }
}
