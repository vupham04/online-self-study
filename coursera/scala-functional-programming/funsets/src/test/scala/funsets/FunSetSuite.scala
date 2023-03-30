package funsets

/**
 * This class is a test suite for the methods in object FunSets.
 *
 * To run this test suite, start "sbt" then run the "test" command.
 */
class FunSetSuite extends munit.FunSuite:

  import FunSets.*

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }

  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   *   val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets:
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
    val s4 = singletonSet(4)
    val s5 = singletonSet(5)

  /**
   * This test is currently disabled (by using .ignore) because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", remove the
   * .ignore annotation.
   */

  test("singleton set one contains one") {
    
    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3".
     */
    new TestSets:
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
  }

  test("union contains all elements of each set") {
    new TestSets:
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
  }

  test("intersect tests") {
    new TestSets:
      val b = union(s1, s2)
      val s = intersect(b, s2)
      assert(!contains(s, 1), "Intersect 1")
      assert(contains(s, 2), "Intersect 2")
      assert(!contains(s, 3), "Intersect 3")
  }

  test("difference tests") {
    new TestSets:
      val b = union(s1, s2)
      val s = diff(b, s2)
      assert(contains(s, 1), "Difference 1")
      assert(!contains(s, 2), "Difference 2")
      assert(!contains(s, 3), "Difference 3")
  }

  test("filter tests") {
    new TestSets:
      val s = filter(s1, (x: Int) => x<2)
      assert(contains(s, 1), "Difference 1")
      assert(!contains(s, 2), "Difference 2")
      assert(!contains(s, 3), "Difference 3")
  }

  test("for all tests") {
    new TestSets:
      val a = union(s1, s2)
      val b = union(union(s1, s3), s5)
      assert(!forall(a, x => x%2 == 0), "For all 1")
      assert(forall(b, x => x%2 == 1), "For all 2")
  }

  test("exists tests") {
    new TestSets:
      val a = union(s1, s2)
      val b = union(union(s1, s3), s5)
      assert(exists(a, x => x % 2 == 0), "Exist 1")
      assert(!exists(b, x => x % 2 == 0), "Exist 2")
  }

  test("map tests") {
    new TestSets:
      val b = union(union(s1, s3), s5)
      val testMap = map(b, x => x*2)
      assert(contains(testMap, 10), "Map 1")
      assert(!contains(testMap, 3), "Map 2")
  }


  import scala.concurrent.duration.*
  override val munitTimeout = 10.seconds
