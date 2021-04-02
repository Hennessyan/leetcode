package amazon;

import java.util.LinkedList;

// Design Snake Game
public class Q353 {

    class SnakeGame {

        /** Initialize your data structure here.
         @param width - screen width
         @param height - screen height
         @param food - A list of food positions
         E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
        int width, height, index;
        int[][] food;
        LinkedList<Point> snake;
        public SnakeGame(int width, int height, int[][] food) {
            this.width = width;
            this.height = height;
            this.index = 0;
            this.food = food;
            snake = new LinkedList<>();
            snake.add(new Point(0, 0)); // add head
        }

        /** Moves the snake.
         @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
         @return The game's score after the move. Return -1 if game over.
         Game over when snake crosses the screen boundary or bites its body. */
        public int move(String direction) {
            Point head = snake.getFirst();
            int x = head.x;
            int y = head.y;
            switch(direction) {
                case "U":
                    x--;
                    break;
                case "L":
                    y--;
                    break;
                case "R":
                    y++;
                    break;
                case "D":
                    x++;
                    break;
            }
            if(x < 0 || x >= this.height || y < 0 || y >= this.width) {
                return -1;
            }
            Point tail = snake.removeLast();
            for(int i = 1; i < snake.size(); i++) { // we can use set to record and check if snake bit itself.
                Point body = snake.get(i);
                if(x == body.x && y == body.y) {
                    return -1;
                }
            }
            snake.addFirst(new Point(x, y));
            if(index < food.length && food[index][0] == x && food[index][1] == y) {
                snake.addLast(tail);
                index++;
            }
            return index;
        }
        class Point {
            int x, y;
            public Point(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }
    }
}
