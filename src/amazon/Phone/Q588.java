package amazon.Phone;

import java.util.*;

/*
    Linux find
    add one more class as File with size and type fields.
    do dfs to find all files based on requirements.
 */
public class Q588 {

    class FileSystem {
        Dir root;
        public FileSystem() {
            root = new Dir();
        }

        public List<String> ls(String path) {
            List<String> list = new ArrayList<>();
            Dir cur = root;
            if(!path.equals("/")) {
                String[] ds = path.split("\\/");
                int n = ds.length;
                for(int i = 1; i < n - 1; i++) {
                    cur = cur.dirs.get(ds[i]);
                }
                if(cur.files.containsKey(ds[n - 1])) {
                    list.add(ds[n-1]);
                    return list;
                } else {
                    cur = cur.dirs.get(ds[n-1]);
                }
            }
            list.addAll(cur.dirs.keySet());
            list.addAll(cur.files.keySet());
            Collections.sort(list);
            return list;
        }

        public void mkdir(String path) {
            Dir cur = root;
            String[] ds = path.split("\\/");
            int n = ds.length;
            for(int i = 1; i < n; i++) {
                if(!cur.dirs.containsKey(ds[i])) {
                    cur.dirs.put(ds[i], new Dir());
                }
                cur = cur.dirs.get(ds[i]);
            }
        }

        public void addContentToFile(String filePath, String content) {
            Dir cur = root;
            String[] ds = filePath.split("\\/");
            int n = ds.length;
            for(int i = 1; i < n - 1; i++) {
                if(!cur.dirs.containsKey(ds[i])) {
                    cur.dirs.put(ds[i], new Dir());
                }
                cur = cur.dirs.get(ds[i]);
            }
            cur.files.put(ds[n-1], cur.files.getOrDefault(ds[n-1], "") + content);
        }

        public String readContentFromFile(String filePath) {
            Dir cur = root;
            String[] ds = filePath.split("\\/");
            int n = ds.length;
            for(int i = 1; i < n - 1; i++) {
                cur = cur.dirs.get(ds[i]);
            }
            return cur.files.get(ds[n-1]);
        }
    }

    class Dir {
        Map<String, Dir> dirs;
        Map<String, String> files;
        public Dir() {
            dirs = new HashMap<>();
            files = new HashMap<>();
        }
    }
}
