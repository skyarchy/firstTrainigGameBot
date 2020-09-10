package timeBot.Utils;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import timeBot.Utils.exception.BotAllException;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class CreateButtonsInline {

    //класс создания кнопок в строчки

    public InlineKeyboardMarkup createNewButtons(@NotNull List<String> text, @NotNull List<String> callBackText, int buttonsInRow) {
        // проверка массивов если у массивов разное количество элементов то бросаем ошибку
        if (text.size() != callBackText.size()) {
            throw new BotAllException("Списки кнопок и ответов не равны");
        }

        // объявляем линии с кнопками
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();

        // добавляем все кнопки в линию
        int call = 0;
        for (String data : text) {
            rowInline.add(new InlineKeyboardButton().setText(data).setCallbackData(callBackText.get(call)));
            call++;
        }

        // распределяем кнопки из 1 линии в множество
        int buttonCounter = 1;
        int buttonCounterInList = 0;
        int lastButtonInList = 0;
        boolean check;
        while (buttonCounterInList != call + 1) {
            check = true;
            if (buttonCounter == buttonsInRow - 1) {
                if (buttonCounterInList == buttonCounter) {
                    rowsInline.add(rowInline.subList(lastButtonInList, buttonCounterInList));
                    lastButtonInList = buttonCounterInList;
                } else {
                    rowsInline.add(rowInline.subList(lastButtonInList, buttonCounterInList));
                    lastButtonInList = buttonCounterInList;
                }
                buttonCounter = -1;
                check = false;
            }
            buttonCounter++;
            buttonCounterInList++;
            if (buttonCounterInList == call + 1) {
                if (check) {
                    rowsInline.add(rowInline.subList(lastButtonInList, buttonCounterInList - 1));
                }
            }
        }
        return new InlineKeyboardMarkup().setKeyboard(rowsInline);
    }

}
