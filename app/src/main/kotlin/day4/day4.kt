package day4

import readInput

fun main() {
	part1()
}

fun part1() {
	val data = readInput("d4p1").map { it -> (it.map { it == '@' }).toMutableList()}.toMutableList()
	var count = 0
	var canRemove = true

	while (canRemove) {
		canRemove = false
		data.forEachIndexed { row, list ->
			list.forEachIndexed { col, check ->
				if (check && checkSquare(
						data,
						row,
						col
					)
				) {
					data[row][col] = false
					canRemove = true
					count++
				}
			}
		}
	}

	println(count)
}

fun checkSquare( data: List<List<Boolean>>, row: Int, column: Int, limit: Int = 4) : Boolean {
	val candidates = listOf(Pair(-1, -1), Pair(-1, 0), Pair(-1, 1), Pair(0, -1), Pair(0, 1), Pair(1, -1), Pair(1, 0), Pair(1,1))
	var count = 0

	candidates.forEach {
		val r = row + it.first
		val c = column + it.second

		if (0 <= r && r < data.size && 0 <= c && c < data[r].size) {
			if (data[r][c]) { count++ }
		}
	}
	return count < limit
}
