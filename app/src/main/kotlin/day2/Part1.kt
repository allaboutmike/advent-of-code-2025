package day2

import readInput
import java.math.BigInteger
import kotlin.io.path.Path
import kotlin.math.floor

// looking for repeating characters.
// if the number is odd, no repeats
// if the number is even, split the number into two parts and check if they are equal
// can do this for all values in the range

fun part1() {
	val input = readInput("d2p1")[0]
	val ranges = input.split(",")
	var totalInvalid: Long = 0

	ranges.forEach {
		val start = it.split("-")[0].toLong()
		val end = it.split("-")[1].toLong()

		var index = start
		while (index < end) {
			val asString = index.toString()
			if (asString.length % 2 == 0) {
				val left = asString.take(asString.length / 2)
				val right = asString.drop(asString.length / 2)
				if (left.length != right.length) {
					println("MATH ISN'T MATHING")
				} else {
					if (left == right) {
						totalInvalid += index
					}
				}
			}
			index++
		}
	}

	println("TOTAL INVALID: $totalInvalid")
}

fun part2() {
	val input = readInput("d2p1")[0]
	val ranges = input.split(",")
	var totalInvalid: Long = 0

	ranges.forEach {
		println("evaluating range $it")
		val start = it.split("-")[0].toLong()
		val end = it.split("-")[1].toLong()
		var invalidCount: Long = 0
		var index = start
		while (index < end) {
			if (containsRepeatingPattern(index.toString())) {
				println("Invalid code: $index")
				invalidCount += index
			}
			index++
		}
		totalInvalid += invalidCount
	}

	println("TOTAL INVALID: $totalInvalid")
}

fun containsRepeatingPattern(s: String): Boolean {
	if (s.length == 1) {
		return false
	}

	val upperLimit = floor(s.length / 2.0).toInt()
	var i = 1
	while (i <= upperLimit) {
		var j = i
		var matches = true
		val left = s.take(i)

		while ((j + i) <= s.length && matches) {
			val right = s.substring(j, j + i)
			matches = left == right
			j += i
		}

		if (matches) {
			return true
		}

		i++ // increase string length by 1
		while (s.length % i != 0) {
			i++ // the pattern must complete the full string, so keep increasing to the next even divisor
		}
	}
	return false
}

fun main() {
	part2()
}
