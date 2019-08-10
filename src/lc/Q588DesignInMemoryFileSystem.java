package lc;

import java.util.*;
import java.util.stream.Collectors;

public class Q588DesignInMemoryFileSystem {

    Dir root;

    public Q588DesignInMemoryFileSystem() {
        root = new Dir("root");
    }

    public List<String> ls(String path) {
        List<String> paths = getPath(path);
        Dir dir = root;
        List<String> res = new ArrayList<>();
        for (int i = 0; i < paths.size(); i ++) {
            if (i == paths.size() - 1 && dir.files.containsKey(paths.get(i))) {
                res.add(paths.get(i));
                return res;
            } else {
                dir = dir.dirs.get(paths.get(i));
                if (dir == null) {
                    return res;
                }
            }
        }

        res.addAll(dir.dirs.keySet());
        res.addAll(dir.files.keySet());
        Collections.sort(res);
        return res;
    }

    public void mkdir(String path) {
        List<String> paths = getPath(path);
        Dir dir = root;
        for (String p : paths) {
            dir = dir.dirs.computeIfAbsent(p, k -> new Dir(p));
        }
    }

    public void addContentToFile(String filePath, String content) {
        List<String> paths = getPath(filePath);
        Dir dir = root;
        for (int i = 0; i < paths.size(); i ++) {
            if (i == paths.size() - 1) {
                dir.files.compute(paths.get(i), (k, v) -> v == null ? content : v + content);
            } else {
                dir = dir.dirs.get(paths.get(i));
            }
        }
    }

    public String readContentFromFile(String filePath) {
        List<String> paths = getPath(filePath);
        Dir dir = root;
        for (int i = 0; i < paths.size(); i ++) {
            if (i == paths.size() - 1) {
                return dir.files.get(paths.get(i));
            } else {
                dir = dir.dirs.get(paths.get(i));
            }
        }
        return null;
    }

    private List<String> getPath(String str) {
        return Arrays.stream(str.split("/")).filter(Objects::nonNull).filter(e -> !e.isEmpty()).collect(Collectors.toList());
    }
}


class Dir{
    Map<String, Dir> dirs = new HashMap<>();
    String dir;
    Map<String, String> files = new HashMap<>();
    public Dir(String dir) {
        this.dir = dir;
    }

}
