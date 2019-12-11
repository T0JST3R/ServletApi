import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.stream.LongStream;

public class HelloService {
    final static String FALL_BACK_NAME = "World";
    final static Lang FALLBACK_LANG = new Lang("en", "Hello", 1L);
    private Logger logger = LoggerFactory.getLogger(HelloService.class);
    private LangRepository repository;


    HelloService() {
        this(new LangRepository());
    }

    public HelloService(LangRepository repository) {
        this.repository = repository;
    }

    String prepareGreeting(String name, String lang) {
        Long idNum = 0L;
        try {
            idNum = Optional.ofNullable(lang).map(Long::valueOf).orElse(FALLBACK_LANG.getId());
        }
        catch (NumberFormatException e){
            logger.warn("Non numeric language id :" + lang);
            idNum = FALLBACK_LANG.getId();
        }
        String greeting = repository.findById(idNum).orElse(FALLBACK_LANG).getWelcomeMessage();
        String nameToWelcome = Optional.ofNullable(name).orElse(FALL_BACK_NAME);
        return greeting + " " + nameToWelcome + "!";
    }
}
