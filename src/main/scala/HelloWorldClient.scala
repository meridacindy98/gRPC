import io.grpc.ManagedChannelBuilder
import io.ontherocks.hellogrpc.helloworld.ToBeGreeted.Person
import io.ontherocks.hellogrpc.helloworld.{Greeting, HelloWorldGrpc, ToBeGreeted}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object HelloWorldClient {
  def main(args: Array[String]): Unit = {
    val channel = ManagedChannelBuilder
      .forAddress("localhost", 50051)
      .build
    val stub  = HelloWorldGrpc.stub(channel)

    val greetingF: Future[Greeting] = stub.sayHello(ToBeGreeted(Some(Person("Bob"))))
    //greetingF.foreach(response => println(s"ASYNC RESULT: ${response.message}"))
    greetingF onComplete println
  }
}
