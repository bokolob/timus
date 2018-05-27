import java.util.*;
import java.io.*;

public class KdTree
{
    public static class Point
    {
        public int coords[];

        public Point(int x, int y) {
            coords = new int[]{x,y};
        }
    }

    public static class Node
    {
        private int axis;
        private Point point;
        private Node left;
        private Node right;

        public Node(List<Point> list, int axis) {
            this.axis = axis;

            int median = (list.size() - 1) / 2;
            this.point = list.get(median);

            if (list.size() > 1) {
                int new_axis = (axis + 1) % 2;

                if (median > 0) {
                    List<Point> leftList   = list.subList(0, median);
                    leftList.sort((p1, p2) -> p1.coords[new_axis] > p2.coords[new_axis] ? 1 : -1);
                    this.left = new Node(leftList, new_axis);
                }

                if (median < list.size() - 1) {
                    List<Point> rightList  = list.subList(median+1, list.size());
                    rightList.sort((p1, p2) -> p1.coords[new_axis] > p2.coords[new_axis] ? 1 : -1);
                    this.right = new Node(rightList, new_axis);
                }
                
            }
            
        }
    }

    private Node root;

    public KdTree(List<Point> list) {
        //already sorted by y axis
        root = new Node(list, 1);
    }
    
}
