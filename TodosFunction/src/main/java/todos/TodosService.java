package todos;

import java.util.Map;
import java.util.UUID;

public class TodosService {

  public Map<String, String> createTodo() {
    return Map.of(
        "id", UUID.randomUUID().toString(),
        "title", "Go to bed"
    );
  }
}
