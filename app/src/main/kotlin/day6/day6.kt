package day6

import readInput

fun main() {
	//part1()
	part2()
}

fun part1() {
	val data = readInput("d6p1").map { it.trim().split("\\s+".toRegex()) }

	// Data is in columnar format, so we want to convert
	val columns = data . fold(
		initial = mutableListOf(mutableListOf<String>()),
	) { acc, strings ->
		strings . forEachIndexed { i, s ->
			if (acc.size == i ) {
				acc.addLast(mutableListOf(s))
			}
			else {
				acc[i].addLast(s)
			}
		}
		acc
	}

	// should now have all the values in their proper order.
	// operator is the last element

	// loop through main list, grab the operator from the last and then perform the operation
	val result = columns.fold (
		initial = 0
	) { acc: Long, strings ->
		val operator = strings.last()
		val nums = strings.dropLast(1)
		val total = if (operator == "+") {
			nums.sumOf { it.toLong() }
		} else {
			var product: Long = nums.first().toLong()

			nums.drop(1).forEach {
				product *= it.toLong()
			}

			product
		}

		acc + total
	}

	println(result)
}

fun part2() {
	val data = readInput("d6p1")

	var total:Long = 0
	var index = data[0].length - 1
	val valueList = mutableListOf<Long>()

	while (index >= 0) {
		val value = "${data[0].elementAt(index)}${data[1].elementAt(index)}${data[2].elementAt(index)}${data[3].elementAt(index)}${data[4].elementAt(index)}".trim()
		if (value.endsWith("*")) {
			// do the operation with this value and the others stored in our list.
			valueList.add(value.dropLast(1).trim().toLong())
			var product: Long = valueList.first()
			valueList.drop(1).forEach {
				product *= it
			}
			total += product
			valueList.clear()
		} else if (value.endsWith("+")) {
			valueList.add(value.dropLast(1).trim().toLong())
			total += valueList.sum()
			valueList.clear()
		} else {
			// if the string is empty, we skip
			if (!value.isEmpty()) {
				valueList.add(value.toLong())
			}
		}
		index--
	}

	println(total)
}
