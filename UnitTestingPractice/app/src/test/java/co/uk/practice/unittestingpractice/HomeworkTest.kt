package co.uk.practice.unittestingpractice

import com.google.common.truth.Truth.assertThat
import org.junit.Test


class HomeworkTest {

    @Test
    fun `check fibonacci works for 0`() {
        val result = Homework.fib(0)
        assertThat(result).isEqualTo(0)
    }

    @Test
    fun `check fibonacci works for 1`() {
        val result = Homework.fib(1)
        assertThat(result).isEqualTo(1)
    }

    @Test
    fun `check fibonacci works for 2`() {
        val result = Homework.fib(2)
        assertThat(result).isEqualTo(1)
    }

    @Test
    fun `check fibonacci works for 13`() {
        val result = Homework.fib(13)
        assertThat(result).isEqualTo(233)
    }


    /**
     * returns false if...
     * ... more brackets to the right/left
     * ... braces not in correct order
     * returns true if...
     * ... no brackets
     * ... equal amount of brackets
     */

    @Test
    fun `more brackets to the right returns false`() {
        val result = Homework.checkBraces("((a+b)")
        assertThat(result).isFalse()
    }

    @Test
    fun `more brackets to the left returns false`() {
        val result = Homework.checkBraces("(a+b))")
        assertThat(result).isFalse()
    }

    @Test
    fun `no braces returns true`() {
        val result = Homework.checkBraces("a+b")
        assertThat(result).isTrue()
    }

    @Test
    fun `equal amount of braces returns true`() {
        val result = Homework.checkBraces("(a+b)")
        assertThat(result).isTrue()
    }

    @Test
    fun `braces in incorrect order returns false`() {
        val result = Homework.checkBraces(")a+b(")
        assertThat(result).isFalse()
    }
}