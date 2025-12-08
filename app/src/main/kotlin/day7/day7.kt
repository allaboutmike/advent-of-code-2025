package day7

import readInput

fun main() {
	part1()
	part2()
}

fun part1() {
	val data = readInput("d7p1")
	var splitCounter = 0
	var above = data[0]

	data.drop(1).forEach { line ->
		// build the new above based on the current line and what is above us
		val newString = StringBuilder()
		var skip = false
		line.forEachIndexed { index, character ->
			if (skip) {
				skip = false
			} else {
				when (above.elementAt(index)) {
					'S' -> newString.append("|")
					'|' -> {
						if (character == '^') {
							newString.deleteAt(newString.length - 1).append("|").append("^").append("|")
							splitCounter++
							skip = true
						} else if (character == '.') {
							newString.append("|")
						}
					}
					'.' -> newString.append('.')
					'^' -> newString.append('.')
				}
			}
		}
		above = newString.toString()
	}

	println(splitCounter)
}

fun part2() {
	val data = readInput("d7p1")

	val startIndex = data[0].indexOf("S")
	val beamPaths = MutableList<Long>(data[0].length) { 0 }
	beamPaths[startIndex]++

	println("$beamPaths")

	data.forEachIndexed { i, line ->
		if (line.contains("^")) {
			println("$i: $line")
			// split line. determine if we intersect
			var startIndex = line.indexOf("^")
			while (startIndex != -1) {
				if (beamPaths[startIndex] > 0) {
					val temp = beamPaths[startIndex]
					beamPaths[startIndex] = 0
					beamPaths[startIndex - 1] += temp
					beamPaths[startIndex + 1] += temp
				}
				startIndex = line.indexOf("^", startIndex + 1)
			}
			println("$beamPaths")
		}
	}
	val total:Long = beamPaths.sum()

	println(total)
}
