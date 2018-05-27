
class Segment(val left:Int, val right:Int){ 

    override fun toString():String {return left.toString()+" "+right.toString()}
}

fun main(args: Array<String>) {
    val M = readLine()!!.toInt();
    val ranges: MutableList<Segment> = mutableListOf();

    while(true) {
        val range = readLine()!!.split(' ').map({ it.toInt() }).toIntArray();

        if (range[0] == 0 && range[1] == 0)
            break;
        
        if ( (range[0] >= 0 || range[1] >= 0) && range[0] <= M )
            ranges.add(Segment(range[0], range[1]));
    }
    
    ranges.sortWith(compareBy({it.left}));

    val result = minCoverage(0, M, ranges);

    if (result.size == 0) {
        println("No solution");
    }
    else {
        println(result.size);

        for ( r in result) {
            println(r);
        }
    }
}


fun minCoverage(from:Int,to:Int, ranges:List<Segment>):List<Segment> {
    var start = from;

    val result:MutableList<Segment> = mutableListOf();
    var i = 0;

    while (i < ranges.size) {
        var maxRight = Integer.MIN_VALUE;
        var found:Segment? = null;
        
        var j = i;

        while ( j < ranges.size && ranges[j].left <= start) {
            if (ranges[j].right > maxRight) {
                maxRight = ranges[j].right;
                found = ranges[j];
            }

            j++;
        }

        if (found == null) {
            break;
        }

        i = j;
        result.add(found);
        start = found.right;

        if (found.right >= to) {
            break;
        }

    }
    
    if (result.size == 0 || result[result.size-1].right < to)
        return listOf();

    return result;
}

/*
                o
------------------------------------------------o
                |--------------------------|

              20
             -1 1
              0 4
              2 7
              5 8
              7 9
              8 12
              10 25
              0 0

             |----| |----| |----| |---------|
                |-----| |---|  |----|


             |----|
                |-----|
                    |----|
                        |---|
                           |----|
                               |----|


        |---------|       |------------------|
            |--------| |--------------|
               |-------|  |--------|
                      |------|
*/

