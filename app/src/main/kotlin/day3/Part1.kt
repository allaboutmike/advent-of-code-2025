package day3

import readInput

fun part1() {

	val data = readInput("d3p1")
	var acc = 0
	data.forEach {

		// within each line we want to find the largest value
		// two pass solution. find largest digit in length - 1, then largest digit in sublist following
		val batteries = it.map { it.digitToInt() }
		var first = -1
		var firstIdx = -1
		for ((i, battery) in batteries.take(batteries.size - 1).withIndex()) {
			if (battery > first) {
				first = battery
				firstIdx = i
			}
		}

		var second = -1
		for (battery in batteries.drop(firstIdx + 1)) {
			if (battery > second) second = battery
		}

		acc += (first * 10 + second)
		println("line: $it batteries $first $second acc $acc")
	}

	println("Result: $acc")
}

fun part2() {
	// largest 12 digits. So we are taking 0..length - 12 for the first, then 0..length - 11 for the second.
	// we always want to maximize digits on the left. So we get the largest digit and the index from a function

	val data = readInput("d3p1")
	var acc: Long = 0
	data.forEach {
		var batteries = it.map { it.digitToInt() }
		val results = mutableListOf<Int>()
		for (i in 11 downTo 0) {
			val (largest, largestIdx) = largestValue(batteries.take(batteries.size - i))
			results.add(largest)
			batteries = batteries.drop(largestIdx + 1)
		}
		val total = results.joinToString("").toLong()
		acc += total
		println("line: $it batteries $total acc $acc")
	}

	println("Result: $acc")
}

fun largestValue (batteries: List<Int>): Pair<Int, Int> {
	var largest = -1
	var largestIdx = -1
	for ((i, battery) in batteries.withIndex()) {
		if (battery > largest) {
			largest = battery
			largestIdx = i
		}
	}

	return Pair(largest, largestIdx)
}

fun main() {
	part2()
}
