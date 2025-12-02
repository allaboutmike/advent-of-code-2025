import kotlin.io.path.Path
import kotlin.io.path.readLines

fun readInput(name: String) = Path("app/src/input/$name.txt").readLines()
