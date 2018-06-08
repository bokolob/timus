
fun main(args: Array<String>) {
    val dimensions = readLine()!!.split(' ').map({ it.toInt() }).toIntArray();

    val graph:MutableMap<Int, MutableMap<Int, Int>> = HashMap();

    for (i in 1..dimensions[1]) {
        val s:List<String> = readLine()!!.split(' ');

        val u = Integer.parseInt(s[0]);
        val v = Integer.parseInt(s[1]);
        val cost =  Integer.parseInt(s[2]);
        
        //Так будет медленно и не пройдет проверку
        //val edge = readLine()!!.split(' ').map({ it.toInt() }).toIntArray();
        
        if (!graph.contains(u)) {
            graph.put(u, HashMap());
        }

        graph[u]!!.put(v, cost);
    }
    
    val startAndFinish = readLine()!!.split(' ').map({ it.toInt() }).toIntArray();

    val tSorted = dfs(graph, startAndFinish[0]);

    //println(tSorted);

    val dist = getDistance(graph, tSorted, startAndFinish[1]);
    
    if (dist == -1) {
        println("No solution");
    }
    else {
        println(dist);
    }
}

fun getDistance(graph: Map<Int,Map<Int,Int>>, tSorted:List<Int>, finishNode:Int):Int {
    
    val weights:MutableMap<Int,Int> = HashMap();

    weights.put(tSorted[tSorted.lastIndex],0);

    for (node in tSorted.asReversed()) {
        
        if (!graph.contains(node)) {
            continue;
        }

        for (child in graph[node]!!.keys) {
            weights.put(child, Math.max( weights.getOrDefault(child, 0), weights[node]!! + graph[node]!![child]!! ));
        }
    }
    
    return weights.getOrDefault(finishNode, -1);
}

fun dfs(graph: Map<Int,Map<Int,Int>>, startNode:Int):List<Int> {
    
    val result:MutableList<Int> = mutableListOf();
    val visited:MutableSet<Int> = mutableSetOf();

    _dfs(graph, startNode, result, visited);
    return result;
}

fun _dfs(graph: Map<Int,Map<Int,Int>>, startNode:Int, result:MutableList<Int>, visited:MutableSet<Int>) {

    visited.add(startNode);

    for (node in graph.getOrElse(startNode, { HashMap() }).keys) {
        if (!visited.contains(node))
            _dfs(graph, node, result, visited);
    }

    result.add(startNode);
}

