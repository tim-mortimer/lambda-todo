package todos;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
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

    Type todoRequestType = new TypeToken<Map<String, String>>() {}.getType();
    Map<String, String> todo = todosService.createTodo(gson.fromJson(input.getBody(), todoRequestType));

    return new APIGatewayProxyResponseEvent()
        .withHeaders(Map.of(
            "Content-Type", "application/json",
            "Location", "/todos/" + todo.get("id")
        ))
        .withStatusCode(201)
        .withBody(gson.toJson(todo));
  }
}
