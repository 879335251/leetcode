package demo;

import java.util.Stack;

/**
 * @author yuzhengwu
 * @version 1.0
 * @description
 * @date 2024/3/20 10:37 PM
 */
public class Num71_SimplifyPath {

    public String simplifyPath(String path) {
        Stack<Character> stack = new Stack<>();
        int len = path.length();

        for (int i = 0; i < len; i++) {
            char c = path.charAt(i);
            if (c == '/') {
                while (!stack.isEmpty() && stack.peek() == '/') {
                    stack.pop();
                }
                stack.push(c);
            } else if (c == '.') {
                // .. 开始
                if (!stack.isEmpty() && stack.peek() == '/') {
                    int count = 0;
                    while (count + i < len && path.charAt(count + i) == '.') {
                        count++;
                    }
                    if (((count + i  < len && path.charAt(count + i) == '/') || count + i == len) && count <= 2) {
                        if (count == 2) {
                            // /的数量 父目录
                            int count1 = 0;
                            while (!stack.isEmpty() && count1 < 2) {
                                char temp = stack.pop();
                                if (temp == '/') {
                                    count1++;
                                }
                            }
                            i += count - 1;
                        }
                    } else {
                        // 正常目录名
                        for (int j = 0; j < count; j++) {
                            stack.push(path.charAt(i + j));
                        }
                        i += count - 1;
                    }
                } else {
                    stack.push(c);
                }
            } else {
                stack.push(c);
            }
        }
        if (stack.isEmpty() || stack.size() == 1) {
            return "/";
        }
        char[] chars = new char[stack.size()];
        int i = stack.size() - 1;
        while (!stack.isEmpty()) {
            chars[i--] = stack.pop();
        }
        String res = new String(chars);
        return res.endsWith("/") ? res.substring(0, res.length() - 1): res;
    }

    public static void main(String[] args) {
        Num71_SimplifyPath num71SimplifyPath = new Num71_SimplifyPath();
        System.out.println(num71SimplifyPath.simplifyPath("/a//b////c/d//././/.."));
    }
}
