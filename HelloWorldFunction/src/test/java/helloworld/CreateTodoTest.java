package helloworld;

import static org.junit.Assert.assertEquals;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import org.junit.Test;
import todos.CreateTodo;

public class CreateTodoTest {

  @Test
  public void creating_a_todo() {
    CreateTodo createTodo = new CreateTodo();
    APIGatewayProxyResponseEvent response = createTodo.handleRequest(null, null);
    assertEquals(201, response.getStatusCode().intValue());
  }
}
