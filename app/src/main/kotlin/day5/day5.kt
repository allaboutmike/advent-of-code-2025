package day5

import readInput

fun part1() {
	val raw = readInput("d5p1")

	val freshRanges = raw.filter { it.contains("-") }. map { it.split("-") } // get the ranges
	val ingredients = raw.filter { !it.contains("-")} .filter { !it.isEmpty() } // ingredients (without the blank)

	val fresh = ingredients.filter { i ->
		freshRanges.any { range ->
			range.first().toLong() <= i.toLong() && i.toLong() <= range.last().toLong()
		}
	}

	println("Fresh ingredients: ${fresh.size}")
}

fun part2() {
	val raw = readInput("d5p1")
	// Get the ranges as a sorted list of long values (sorted by the start of the range)
	val freshRanges = raw.filter { it.contains("-") }. map { it.split("-") }. map { Pair(it.first().toLong(), it.last().toLong() )}. sortedWith { l, r ->
		l.first.compareTo(r.first)
	}

	val mergedRanges = mutableListOf<Pair<Long, Long>>()
	var newRange = Pair(freshRanges.first().first, freshRanges.first().second)

	freshRanges.drop(1).forEach { next ->
		// If the next range starts within the current range, combine them.
		// candidate.first <= newRange.second -- start of candidate range is before end of current range
		if (next.first > newRange.second + 1) {
			// ranges don't touch.
			mergedRanges.add(newRange)
			newRange = Pair(next.first, next.second)
		} else if (next.second > newRange.second) {
			newRange = Pair(newRange.first, next.second)
		}
	}
	mergedRanges.add(newRange) // Add the final range in.

	// We now have the merged ranges with no overlap, so we just need to sum the size
	var count: Long = 0
	mergedRanges.forEach {
		count += it.second - it.first + 1
	}

	println("Possible ingredients: $count")
}

fun main() {
	part1()
	part2()
}
