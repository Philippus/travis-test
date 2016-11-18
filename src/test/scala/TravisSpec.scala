import org.scalacheck._
import org.scalacheck.Prop.forAll

object TravisSpec extends Properties("test") {
  // https://research.googleblog.com/2006/06/extra-extra-read-all-about-it-nearly.html

  val genIntPairs =
    for {
      low <- Gen.choose(1, Int.MaxValue)
      high <- Gen.choose(1, Int.MaxValue) if high > low
    } yield (low, high)

    property("binary search ints") = forAll(genIntPairs) {
      case (low, high) if high > low => {
        val mid = (low + high) / 2
        mid > low
      }
      case _ => true
    }
}
