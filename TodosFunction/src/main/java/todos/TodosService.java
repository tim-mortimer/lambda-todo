package todos;

import java.util.Map;
import java.util.UUID;

public class TodosService {

  public Map<String, String> createTodo(Map<String, String> createTodoCommand) {
    return Map.of(
        "id", UUID.randomUUID().toString(),
        "title", createTodoCommand.get("title")
    );
  }
}
