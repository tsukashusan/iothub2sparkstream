package example

import org.scalatest._

class IotHub2SparkStreamSpec extends FlatSpec with Matchers {
  "The Hello object" should "say hello" in {
    IotHub2SparkStream.greeting shouldEqual "hello"
  }
}
