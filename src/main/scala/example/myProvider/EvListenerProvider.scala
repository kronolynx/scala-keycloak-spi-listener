package example.myProvider

import org.keycloak.events.{Event, EventListenerProvider, EventType}
import org.keycloak.events.admin.{AdminEvent, OperationType}

class EvListenerProvider(excludeEvents: Set[EventType],
                         excludeAdminOperations: Set[OperationType])
    extends EventListenerProvider {
  override def onEvent(event: Event): Unit = {
    if (!excludeEvents.contains(event.getType)) {
      // testing http library
      println(
        Console.CYAN + EvClient
          .send(s"postman echo (${event.getType})") + Console.RESET)
      // print event
      println(
        Console.BLUE + s"event: type => (${event.getType}), details => (${event.getDetails})" + Console.RESET)
    }
  }

  override def onEvent(event: AdminEvent,
                       includeRepresentation: Boolean): Unit = {
    if (!excludeAdminOperations.contains(event.getOperationType)) {
      // testing http library
      println(
        Console.CYAN + EvClient
          .send(s"postman echo (${event.getOperationType})") + Console.RESET)
      // print admin event
      println(
        Console.GREEN + s"event: type => (${event.getOperationType}), representation => (${event.getRepresentation})" + Console.RESET)
    }
  }

  override def close(): Unit = ()
}
