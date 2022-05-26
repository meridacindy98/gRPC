import io.grpc.ServerBuilder
import io.grpc.netty.NettyServerBuilder
import io.ontherocks.hellogrpc.helloworld.{Greeting, HelloWorldGrpc, ToBeGreeted}

import scala.concurrent.{ExecutionContext, Future}

object HelloWorldServer{
  class HelloWorldService extends HelloWorldGrpc.HelloWorld {
    println("le pegue al server por que soy re capo- HelloWorldService")
    def sayHello(request: ToBeGreeted): Future[Greeting] = {
      val greetedPerson = request.person match {
        case Some(person) => person.name
        case None         => "anonymous"
      }
      Future.successful(Greeting(message = s"Hello ${greetedPerson}!"))
    }
  }

  def main(args: Array[String]): Unit = {
    val ssd = HelloWorldGrpc.bindService(new HelloWorldService(), ExecutionContext.global)
    val server = NettyServerBuilder
      .forPort(50051)
      .addService(ssd)
      .build
      .start

    // make sure our server is stopped when jvm is shut down
    Runtime.getRuntime.addShutdownHook(new Thread() {
      override def run(): Unit = server.shutdown()
    })

    server.awaitTermination()
  }

}

