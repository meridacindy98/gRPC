import io.ontherocks.hellogrpc.helloworld.{Greeting, HelloWorldGrpc, ToBeGreeted}

import scala.concurrent.Future

class HelloWorldService extends HelloWorldGrpc.HelloWorld {
  def sayHello(request: ToBeGreeted): Future[Greeting] = {
    val greetedPerson = request.person match {
      case Some(person) => person.name
      case None         => "anonymous"
    }
    Future.successful(Greeting(message = s"Hello ${greetedPerson}!"))
  }
}
