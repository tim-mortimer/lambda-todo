package todos;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

public class CreateTodo {

  public APIGatewayProxyResponseEvent handleRequest(final APIGatewayProxyRequestEvent input, final
      Context context) {
    return new APIGatewayProxyResponseEvent().withStatusCode(201);
  }
}
