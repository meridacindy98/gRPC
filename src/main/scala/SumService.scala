import io.grpc.stub.StreamObserver
import io.ontherocks.hellogrpc.sum.{SumGrpc, SumRequest, SumResponse}

class SumService extends SumGrpc.Sum {
  override def add(responseObserver: StreamObserver[SumResponse]): StreamObserver[SumRequest] = ???
}
