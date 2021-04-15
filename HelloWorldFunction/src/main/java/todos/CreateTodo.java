package todos;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import java.util.Map;

public class CreateTodo {

  public APIGatewayProxyResponseEvent handleRequest(final APIGatewayProxyRequestEvent input, final
      Context context) {
    return new APIGatewayProxyResponseEvent()
        .withHeaders(Map.of("Content-Type", "application/json"))
        .withStatusCode(201);
  }
}
