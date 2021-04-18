package todos;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;
import java.util.Map;
import java.util.UUID;
import org.junit.Test;

public class CreateTodoRequestHandlerTest {

  Gson gson = new Gson();

  TodosService todosService = mock(TodosService.class);

  @Test
  public void creating_a_todo() {
    String todoId = UUID.randomUUID().toString();

    when(todosService.createTodo(any())).thenReturn(Map.of(
        "id", todoId,
        "title", "Go to the shops"
    ));

    CreateTodoRequestHandler handler = new CreateTodoRequestHandler(todosService);

    APIGatewayProxyRequestEvent request =
        new APIGatewayProxyRequestEvent().withBody(gson.toJson(Map.of("title", "Go to the shops")));
    APIGatewayProxyResponseEvent response = handler.handleRequest(request, null);

    assertEquals(201, response.getStatusCode().intValue());
    assertEquals("application/json", response.getHeaders().get("Content-Type"));
    assertThat(response.getHeaders().get("Location"), containsString("/todos/" + todoId));
  }
}
