package demo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuzhengwu
 * @version 1.0
 * @description
 * @date 2024/3/23 3:13 PM
 */
public class Num130_SurroundedRegions {

    int m, n;
    boolean[][] visited;
    public void solve(char[][] board) {
        m = board.length;
        n = board[0].length;
        visited = new boolean[m][n];
        // 只需要从内圈进行
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (!visited[i][j] && board[i][j] == 'O') {
                    List<int[]> oPoints = new ArrayList<>();
                    boolean isNotEdge = dfs(board, i, j, oPoints);
                    if (isNotEdge) {
                        for (int[] point: oPoints) {
                            board[point[0]][point[1]] = 'X';
                        }
                    }
                }
            }
        }
    }
    // 返回 false  代表o 触达边缘

    private boolean dfs (char[][] board, int i, int j, List<int[]> oPoints) {
        if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
            return board[i][j] == 'X';
        }
        if (board[i][j] == 'X') {
            return true;
        }
        if (visited[i][j]) {
            return true;
        }
        visited[i][j] = true;
        oPoints.add(new int[]{i, j});
        boolean r1 = dfs(board, i, j + 1, oPoints);
        boolean r2 = dfs(board, i, j - 1, oPoints);
        boolean r3 = dfs(board, i + 1, j, oPoints);
        boolean r4 = dfs(board, i - 1, j, oPoints);
        return  r1 && r2 && r3 && r4;
    }

    public static void main (String[] args) {
        Num130_SurroundedRegions num130SurroundedRegions = new Num130_SurroundedRegions();
        char[][] board = new char[][]{
                {'X','O','X','O','X','O','O','O','X','O'},
                {'X','O','O','X','X','X','O','O','O','X'},
                {'O','O','O','O','O','O','O','O','X','X'},
                {'O','O','O','O','O','O','X','O','O','X'},
                {'O','O','X','X','O','X','X','O','O','O'},
                {'X','O','O','X','X','X','O','X','X','O'},
                {'X','O','X','O','O','X','X','O','X','O'},
                {'X','X','O','X','X','O','X','O','O','X'},
                {'O','O','O','O','X','O','X','O','X','O'},
                {'X','X','O','X','X','X','X','O','O','O'}};
        num130SurroundedRegions.solve(board);

    }
}
