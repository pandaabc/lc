package lc;

import java.util.*;

public class Q212WordSearch_Trie {

    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        if (board == null || board.length == 0 || board[0].length == 0) {
            return res;
        }
        TrieNode dummy = constructTrieNodes(words);
        boolean[][] visited = new boolean[board.length][board[0].length];
        Set<String> resSet = new HashSet<>();
        for (int i = 0; i < board.length; i ++) {
            for (int j = 0; j < board[0].length; j ++) {
                dfs(board, dummy, i, j, visited, resSet);
            }
        }
        return new ArrayList<>(resSet);
    }

    public void dfs(char[][] board, TrieNode parNode, int i, int j, boolean[][] visited, Set<String> res) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || visited[i][j]) {
            return;
        }
        if (!parNode.getChildTrieNodes().containsKey(board[i][j])) {
            return;
        }
        visited[i][j] = true;
        TrieNode curNode = parNode.getChildTrieNodes().get(board[i][j]);
        if (curNode.isLeaf()) {
            res.add(curNode.getWord());
        }
        // check all direction
        dfs(board, curNode, i - 1, j, visited, res);
        dfs(board, curNode, i + 1, j, visited, res);
        dfs(board, curNode, i, j + 1, visited, res);
        dfs(board, curNode, i, j - 1, visited, res);
        visited[i][j] = false;
    }

    private TrieNode constructTrieNodes(String[] words) {

        TrieNode dummy = new TrieNode('a');

        for (String word : words) {
            TrieNode curNode = dummy;
            for (int i = 0; i < word.length(); i ++) {
                char c = word.charAt(i);
                curNode = curNode.getChildTrieNodes().computeIfAbsent(c, k -> new TrieNode(c));
                if (i == word.length() - 1) {
                    curNode.setLeaf(true);
                    curNode.setWord(word);
                }
            }
        }

        return dummy;
    }

}

