import io.grpc.ManagedChannelBuilder
import io.ontherocks.hellogrpc.helloworld.HelloWorldGrpc.{HelloWorldBlockingStub, HelloWorldStub}
import io.ontherocks.hellogrpc.helloworld.ToBeGreeted.Person
import io.ontherocks.hellogrpc.helloworld.{Greeting, HelloWorldGrpc, ToBeGreeted}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object HelloWorldClient {
  def main(args: Array[String]): Unit = {

    val channel = ManagedChannelBuilder
      .forAddress("localhost", 50051)
      .usePlaintext
      .asInstanceOf[ManagedChannelBuilder[_]].build()

    val person = Person(name = "CINDOR")

    val toBeGreeted = ToBeGreeted(Some(person))

    println(s"BUENAS")

    // async client
    val stub: HelloWorldStub        = HelloWorldGrpc.stub(channel)
    val greetingF: Future[Greeting] = stub.sayHello(toBeGreeted)
    Thread.sleep(1000)
    greetingF.map( response => println(s"ASYNC RESULT: ${response.message}") )

  }
}
