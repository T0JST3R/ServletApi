import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LangRepository {
   private List<Lang> languages = new ArrayList<>();

    LangRepository() {
        languages.add(new Lang("en", "Hello", 1L));
        languages.add(new Lang("pl", "Siemano", 2L));
    }

    Optional<Lang> findById(Long id) {
        return languages.stream()
                .filter(l -> l.getId().equals(id))
                .findFirst();

    }
}
