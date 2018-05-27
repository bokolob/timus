import java.util.Random

fun main(args: Array<String>) {
    val cnt = readLine()!!.toInt();

    val points:MutableList<Point> = mutableListOf<Point>();
    val random = Random()

    for (i in 1..cnt) {
        val coords = readLine()!!.split(' ').map({ it.toDouble() }).toDoubleArray();
        points.add(Point(coords[0],coords[1],coords[2]));
    }

    var hits=0;
    val total = 10_000;

    for (i in 1..total) {
        val x = random.nextDouble();
        val y = random.nextDouble();
        for (p in points) {
            if(p.dist(x,y) < p.radius) {
                hits++;
                break;
            }
        }
    }

    println(100.0 * hits/total.toDouble());
}

class Point(x:Double, y:Double, r:Double) {
    val x:Double = x;
    val y:Double = y;
    val radius = r;

    fun dist(x0:Double, y0:Double):Double {
        return Math.sqrt( Math.pow(x-x0, 2.0) + Math.pow(y-y0, 2.0) );
    }
}


