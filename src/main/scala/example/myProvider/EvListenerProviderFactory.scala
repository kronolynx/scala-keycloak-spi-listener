package example.myProvider

import org.keycloak.Config
import org.keycloak.events.admin.OperationType
import org.keycloak.events.{
  EventListenerProvider,
  EventListenerProviderFactory,
  EventType
}
import org.keycloak.models.{KeycloakSession, KeycloakSessionFactory}

class EvListenerProviderFactory extends EventListenerProviderFactory {
  private var excludeEvents: Set[EventType] = Set.empty
  private var excludeAdminOperations: Set[OperationType] = Set.empty

  override def create(session: KeycloakSession): EventListenerProvider =
    new EvListenerProvider(excludeEvents, excludeAdminOperations)

  override def init(config: Config.Scope): Unit = {
    println(Console.YELLOW + s"about to start $config" + Console.RESET)

    val excludes = Option(config.getArray("excludes")).getOrElse(Array.empty)
    excludeEvents = excludes.map(EventType.valueOf).toSet
    excludeAdminOperations = excludes.map(OperationType.valueOf).toSet
  }

  override def postInit(factory: KeycloakSessionFactory): Unit = ()

  override def close(): Unit = ()

  override def getId: String = "myEvListener"
}
