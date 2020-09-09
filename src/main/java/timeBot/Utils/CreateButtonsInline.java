package timeBot.Utils;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import timeBot.Utils.exception.BotAllException;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class CreateButtonsInline {

    //класс создания кнопок в строчки

    public InlineKeyboardMarkup createNewButtons(List<String> text, List<String> callBackText, int buttonsInRow) {
        if (text.size() != callBackText.size()) {
            throw new BotAllException("Списки кнопок и ответов не равны");
        }

        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();

        int call = 0;
        for (String data : text) {
            rowInline.add(new InlineKeyboardButton().setText(data).setCallbackData(callBackText.get(call)));
            call++;
        }

        int buttonCounter = 0;
        int buttonCounterInList = 0;
        int lastButtonInList = 0;
        while (buttonCounterInList != call) {
            if (buttonCounter == buttonsInRow) {
                if (buttonCounterInList == buttonCounter) {
                    rowsInline.add(rowInline.subList(lastButtonInList, buttonCounterInList));
                    lastButtonInList = buttonCounterInList;
                } else {
                    rowsInline.add(rowInline.subList(lastButtonInList + 1, buttonCounterInList));
                    lastButtonInList = buttonCounterInList;
                }
            }
            buttonCounter++;
            buttonCounterInList++;
        }

        return new InlineKeyboardMarkup().setKeyboard(rowsInline);
    }


}
