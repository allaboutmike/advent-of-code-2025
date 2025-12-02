package day1

import readInput
import kotlin.math.abs
import kotlin.math.floor

fun part1() {
	val input = readInput("d1p1")
	var currentPosition = 50;
	var zeroCount = 0;
	input.forEach {
		val direction = it.substring(0, 1)
		val value = it.substring(1).toInt()

		if (direction == "L") {
			currentPosition -= value
		} else if (direction == "R") {
			currentPosition += value
		}

		// circle around at the 100 mark
		currentPosition %= 100;
		if (currentPosition == 0) {
			zeroCount++
		}
	}

	println(zeroCount)
}

fun part2() {
	val input = readInput("d1p1")
	var currentPosition = 50;
	var zeroCount = 0;
	input.forEach {
		val direction = it.substring(0, 1)
		val value = it.substring(1).toInt()

		var zerosToAdd = 0

		// Reduce value to be less than 100. For every 100, we will add a count.
		// A spin of exactly 100 (in either direction, stays on the same number.
		val subValue = value % 100;
		zerosToAdd = floor(value / 100.0).toInt()

		if (direction == "L") {
			currentPosition -= subValue
		} else if (direction == "R") {
			currentPosition += subValue
		}

		if (currentPosition < 0 && (currentPosition + subValue != 0)) {
			// If we went left and passed zero, we need to count it here.
			zerosToAdd++
		} else if (currentPosition > 100) {
			// If we went right and passed zero, count it here
			zerosToAdd++
		}

		// We now need to "circle" around properly.
		currentPosition = if (currentPosition < 0) {
			(100 - abs(currentPosition % 100)) % 100
		} else {
			currentPosition % 100
		}

		// lastly, the check if we landed on a zero
		if (currentPosition == 0) {
			zerosToAdd++
		}

		zeroCount += zerosToAdd
	}

	println(zeroCount)
}

fun main() {
	part1()
	part2()
}

