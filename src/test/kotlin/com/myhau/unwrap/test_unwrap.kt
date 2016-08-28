package com.myhau.unwrap

import org.junit.Test
import kotlin.test.assertEquals

class UnwrapTest {

  class Dummy1(val value: Int)
  class Dummy2(val value: String)

  @Test
  fun testNotNull() {
    val _a = Dummy1(10)
    val _b = Dummy2("1")
    var count = 0

    notnull(_a, _b) {
      ++count
    }

    assertEquals(1, count)
  }

  @Test
  fun testNotNullManyArgs() {
    val _a = Dummy1(10)
    val _b = Dummy2("1")
    val _c = null
    val _d = null
    var count = 0

    notnull(_a, _b, _c, _d) {
      ++count
    }

    assertEquals(0, count)
  }

  @Test
  fun testUnwrapWithNull() {
    val _a = Dummy1(1)
    val _b = null
    var validCount = 0
    var invalidCount = 0

    val res = unwrap(_a, _b) { a, b ->
      ++validCount
    } ?: ++invalidCount

    assertEquals(0, validCount)
    assertEquals(1, invalidCount)
    assertEquals(res, invalidCount)
  }

  @Test
  fun testUnwrapWithoutNull() {
    val _a = Dummy1(1)
    val _b = Dummy1(2)
    var validCount = 0
    var invalidCount = 0

    unwrap(_a, _b) { a, b ->
      validCount++
      assertEquals(a.value, 1)
      assertEquals(b.value, 2)
    } ?: ++invalidCount

    assertEquals(1, validCount)
    assertEquals(0, invalidCount)

  }

  @Test
  fun testUnwrapWithoutNullMoreParams() {
    val _a = Dummy1(1)
    val _b = Dummy1(2)
    val _c = Dummy2("unwrap")
    val _d = Dummy2("unwrap123")
    var validCount = 0
    var invalidCount = 0

    unwrap(_a, _b, _c, _d) { a, b, c, _d ->
      validCount++
      assertEquals(a.value, 1)
      assertEquals(b.value, 2)
      assertEquals(c.value, "unwrap")
    }

    assertEquals(1, validCount)
    assertEquals(0, invalidCount)
  }
}
