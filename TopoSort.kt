
fun main(args: Array<String>) {
    val cnt = readLine()!!.split(' ').map({ it.toInt() }).toIntArray();

    val subjects = cnt[0];
    val limitations = cnt[1];

    val dependencies:Array<MutableList<Int>> = Array(subjects, {mutableListOf<Int>()});
    
    for (i in 0..limitations-1) {
        val pair = readLine()!!.split(' ').map({ it.toInt() }).toIntArray();
        dependencies[pair[1]-1].add(pair[0]-1);
    }

    val visited = mutableSetOf<Int>();
    val programm = readLine()!!.split(' ').map({ it.toInt() }).toIntArray();

    for (i in programm) {
        for (dep in dependencies[i-1]) {
            if (!visited.contains(dep)) {
                println("NO");
                return;
            }
        }

        visited.add(i-1);
    }
    
    println("YES");
}
