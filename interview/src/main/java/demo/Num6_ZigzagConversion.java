package interview;

/**
 * @author yuzhengwu
 * https://leetcode.cn/problems/zigzag-conversion/description/?envType=study-plan-v2&envId=top-interview-150
 * @version 1.0
 * @description
 * @date 2024/3/18 10:12 PM
 */
public class Num6_ZigzagConversion {

    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        // 1组有多少个   1组占几列
        int groupNum = numRows * 2 - 2, groupCol = numRows - 1;
        int totalCol = (s.length()/ groupNum + 1) * groupCol;
        char[][] chars = new char[numRows][totalCol];
        boolean[][] isUsed = new boolean[numRows][totalCol];
        for (int i = 0; i < s.length(); i++) {
            // 属于第几组，第几组第几个
            int group = i / groupNum, index = i % groupNum;
            int r = 0, c = 0;
            if (index >= numRows) {
                r = (numRows - 2) - (index - numRows);
                c = groupCol * group + index + 1 - numRows;
            } else {
                r = index;
                c = groupCol * group;
            }
            chars[r][c] =  s.charAt(i);
            isUsed[r][c] = true;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < totalCol; j++) {
                if (isUsed[i][j]) {
                    builder.append(chars[i][j]);
                }
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        Num6_ZigzagConversion num6ZigzagConversion = new Num6_ZigzagConversion();
        System.out.println(num6ZigzagConversion.convert("PAYPALISHIRING",3));
    }
}
