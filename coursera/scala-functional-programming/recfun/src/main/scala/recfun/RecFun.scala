package recfun

import java.security.KeyStore.TrustedCertificateEntry

object RecFun extends RecFunInterface:

  def main(args: Array[String]): Unit =
    println("Pascal's Triangle")
    for row <- 0 to 10 do
      for col <- 0 to row do
        print(s"${pascal(col, row)} ")
      println()

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    if c == 0 || c == r then 1
    else if c < 0 || c > r then 0
    else pascal(c, r-1) + pascal(c-1, r-1)
  }

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {
    def balanceHelper(chars: List[Char], helper: Int): Boolean = {
      if chars.isEmpty && helper == 0 then true
      else if helper < 0 then false
      else
        val charsHead = chars.head
        val a = if charsHead == '(' then helper + 1 else if charsHead == ')' then helper - 1 else helper
        balanceHelper(chars.tail, a)
    }
    balanceHelper(chars, 0)
  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {

    if money == 0 then 1
    else if money < 0 || coins.isEmpty then 0
    else
      var a = coins.head
      countChange(money - a, coins) + countChange(money, coins.tail)
  }
