package lc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q399EvaluateDivision_UnionFind {

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Integer> idxByStr = new HashMap<>();
        int idx = 0;
        for (List<String> l : equations) {
            for (String p : l) {
                if (!idxByStr.containsKey(p)) {
                    idxByStr.put(p, idx);
                    idx ++;
                }
            }
        }
        //System.out.println(idxByStr);
        int[] parents = new int[idxByStr.size()];
        double[] divValues = new double[idxByStr.size()];
        for (int i = 0; i < divValues.length; i ++) {
            divValues[i] = 1.;
            parents[i] = i;
        }

        int i = 0;
        for (List<String> l : equations) {
            //System.out.println(Arrays.toString(parents));
            //System.out.println(Arrays.toString(divValues));
            union(parents, divValues, idxByStr.get(l.get(0)), idxByStr.get(l.get(1)), values[i]);
            //System.out.println(Arrays.toString(parents));
            //System.out.println(Arrays.toString(divValues));
            i ++;
        }
        //System.out.println(Arrays.toString(parents));
        //System.out.println(Arrays.toString(divValues));

        double[] results = new double[queries.size()];
        i = -1;
        for (List<String> q : queries) {
            i ++;
            if (!idxByStr.containsKey(q.get(0)) || !idxByStr.containsKey(q.get(1))) {
                results[i] = -1.0;
                continue;
            }
            double[]pi = find(parents, idxByStr.get(q.get(0)), divValues);
            double[]pj = find(parents, idxByStr.get(q.get(1)), divValues);
            //System.out.println("i is: " + q.get(0) + " and j is: " + q.get(1));
            //System.out.println(Arrays.toString(pi));
            //System.out.println(Arrays.toString(pj));
            //System.out.println(Arrays.toString(parents));
            //System.out.println(Arrays.toString(divValues));
            if (pi[0] != pj[0]) {
                results[i] = -1.0;
            } else {
                results[i] = pj[1] / pi[1];
            }
        }
        return results;
    }

    public void union(int[] parents, double[] divValues, int i, int j, double divNum) {

        // i is children
        // j is parent
        double[] ri = find(parents, i, divValues);
        double[] rj = find(parents, j, divValues);

        parents[(int)rj[0]] = (int)ri[0];
        divValues[(int)rj[0]] =divNum * ri[1] / rj[1];

    }

    public double[] find(int[] parents, int idx, double[] divValues) {

        double div = 1;
        if (parents[idx] != idx) {
            divValues[idx] = divValues[idx] * divValues[parents[idx]]; // updating the div values
            parents[idx] = parents[parents[idx]];
            //System.out.println(divValues[idx] +" * " + divValues[parents[idx]]);
            div *= divValues[idx];
            idx = parents[idx];
        }

        return new double[]{(double) idx, div};

    }
}
