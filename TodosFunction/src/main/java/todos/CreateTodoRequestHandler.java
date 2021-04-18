package todos;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;
import java.util.Map;

public class CreateTodoRequestHandler implements
    RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

  TodosService todosService;

  Gson gson = new Gson();

  public CreateTodoRequestHandler() {
    this.todosService = new TodosService();
  }

  public CreateTodoRequestHandler(TodosService todosService) {
    this.todosService = todosService;
  }

  public APIGatewayProxyResponseEvent handleRequest(final APIGatewayProxyRequestEvent input, final
      Context context) {

    Map<String, String> todo = todosService.createTodo();

    return new APIGatewayProxyResponseEvent()
        .withHeaders(Map.of(
            "Content-Type", "application/json",
            "Location", "/todos/" + todo.get("id")
        ))
        .withStatusCode(201)
        .withBody(gson.toJson(todo));
  }
}
