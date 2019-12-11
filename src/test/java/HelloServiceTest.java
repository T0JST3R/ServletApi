import org.junit.Test;

import javax.security.auth.callback.LanguageCallback;
import java.util.Locale;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class HelloServiceTest {
    LangRepository langRepository = alwaysReturningHelloRepository();
    HelloService SUT = new HelloService(langRepository);
    private final static String WELCOME = "Hello";
    private final static String fallBackIdWelcome = "Hola";

    @Test
    public void testPrepareGreetingWithNullName_returnsFallBackValue() {
        //Given
        String name = null;
        String lang = "-1";
        //when
        String result = SUT.prepareGreeting(name, lang);
        // then
        assertEquals(WELCOME + " " + HelloService.FALL_BACK_NAME + "!", result);
    }
    @Test
   public void test_prepareGreeting_nonExistingLang_returnsGreetingWithFallbackLang(){

        //Given
        LangRepository repository = fallBackLangNullBodyRepository();
        var SUT = new HelloService(repository);
        //when
        var result = SUT.prepareGreeting(HelloService.FALL_BACK_NAME , "13");
        //then
        assertEquals(WELCOME + " World!" , result);


    }

    @Test
    public void testPrepareGreetingWithName_returnsGreetingWithName() {

        //given
        String name = "Dzejson";
        String lang = "-2";

        //when
        String result = SUT.prepareGreeting(name, lang);
        //then
        assertEquals(WELCOME + " " + name + "!", result);
    }

    @Test
    public void test_prepareGreetingNullLang_returnsGreetingWithFallBackIdLang() {
        //given
        LangRepository mockRepository = fallBackLangIdRepository();
        var SUT = new HelloService(mockRepository);
        //when
        var result = SUT.prepareGreeting(HelloService.FALL_BACK_NAME, null);

        //then
        assertEquals(fallBackIdWelcome + " " + HelloService.FALL_BACK_NAME + "!", result);
    }
    @Test
    public void test_prepareGreetingTextLang_returnsGreetingWithFallBackIdLang() {
        //given
        LangRepository mockRepository = fallBackLangIdRepository();
        var SUT = new HelloService(mockRepository);
        //when
        var result = SUT.prepareGreeting(HelloService.FALL_BACK_NAME, "abc");

        //then
        assertEquals(fallBackIdWelcome + " " + HelloService.FALL_BACK_NAME + "!", result);
    }

    private LangRepository alwaysReturningHelloRepository() {
        return new LangRepository() {
            @Override
            Optional<Lang> findById(Long id) {
                return Optional.of(new Lang(null, WELCOME, 777L));
            }
        };

    }

    private LangRepository fallBackLangIdRepository() {
        return new LangRepository() {

            @Override
            Optional<Lang> findById(Long id) {

                if (id.equals(HelloService.FALLBACK_LANG.getId())) {
                    return Optional.of(new Lang(null, fallBackIdWelcome, null));
                } else return Optional.empty();
            }
        };
    }
    private LangRepository fallBackLangNullBodyRepository(){

        return new LangRepository() {

            @Override
            Optional<Lang> findById(Long id) {

                    return Optional.empty();
            }
        };
    }

}
