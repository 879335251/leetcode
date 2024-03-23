package demo;

import java.util.*;

/**
 * @author yuzhengwu
 * @version 1.0
 * @description
 * @date 2024/3/23 6:16 PM
 */
public class Num399_EvaluateDivision {

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int numIndex = 0;
        Map<String, Integer> str2Index = new HashMap<>();
        for (List<String> equation: equations) {
            String a = equation.get(0), b = equation.get(1);
            if (!str2Index.containsKey(a)) {
                str2Index.put(a, numIndex++);
            }
            if (!str2Index.containsKey(b)) {
                str2Index.put(b, numIndex++);
            }
        }
        double[][] graph = new double[numIndex][numIndex];
        for (int i = 0; i < numIndex; i++) {
            Arrays.fill(graph[i], -1.0);
        }
        for (int i = 0; i < equations.size(); i++) {
            String a = equations.get(i).get(0), b = equations.get(i).get(1);
            graph[str2Index.get(a)][str2Index.get(b)] = values[i];
            graph[str2Index.get(b)][str2Index.get(a)] = 1.0 / values[i];
        }
        for (int k = 0; k < numIndex; k++) {
            for (int i = 0; i < numIndex; i++) {
                for(int j = 0; j < numIndex; j++) {
                    // floyd 有精度问题
                    if (graph[i][k] >= 1e-05 && graph[k][j] > 1e-05) {
                        graph[i][j] = graph[i][k] * graph[k][j];
                    }
                }
            }
        }
        double[] res = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            List<String> query = queries.get(i);
            double result = -1.0d;
            if (str2Index.get(query.get(0)) != null && str2Index.get(query.get(1)) != null) {
                if (graph[str2Index.get(query.get(0))][str2Index.get(query.get(1))] > 0) {
                    result = graph[str2Index.get(query.get(0))][str2Index.get(query.get(1))];
                }
            }
            res[i] = result;
        }
        return res;
    }

    public static void main(String[] args) {
        Num399_EvaluateDivision num399EvaluateDivision = new Num399_EvaluateDivision();
        // [["b","a"],["c","b"],["d","c"],["e","d"],["f","e"],["g","f"],["h","g"],["i","h"],["j","i"],["k","j"],["k","l"],["l","m"],["m","n"],["n","o"],["o","p"],["p","q"],["q","r"],["r","s"],["s","t"],["t","u"]]
        List<List<String>> equations = Arrays.asList(
                Arrays.asList("b", "a"),
                Arrays.asList("c", "b"),
                Arrays.asList("d", "c"),
                Arrays.asList("e", "d"),
                Arrays.asList("f", "e"),
                Arrays.asList("g", "f"),
                Arrays.asList("h", "g"),
                Arrays.asList("i", "h"),
                Arrays.asList("j", "i"),
                Arrays.asList("k", "j"),
                Arrays.asList("k", "l"),
                Arrays.asList("l", "m"),
                Arrays.asList("m", "n"),
                Arrays.asList("n", "o"),
                Arrays.asList("o", "p"),
                Arrays.asList("p", "q"),
                Arrays.asList("q", "r"),
                Arrays.asList("r", "s"),
                Arrays.asList("s", "t"),
                Arrays.asList("t", "u")
        );
        double[] values = new double[]{1e-05,1e-05,1e-05,1e-05,1e-05,1e-05,1e-05,1e-05,1e-05,1e-05,1e-05,1e-05,1e-05,1e-05,1e-05,1e-05,1e-05,1e-05,1e-05,1e-05};
        List<List<String>> queries = Collections.singletonList(Arrays.asList("a", "u"));
        double[] res = num399EvaluateDivision.calcEquation(equations, values, queries);
        for (double r: res) {
            System.out.println(r);
        }
    }

}
