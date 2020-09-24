package timeBot.configurations.properties;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class BotProperties {

    @Value("${test.properties.dod}")
    private boolean dod;

    private Integer ask = 10;

}