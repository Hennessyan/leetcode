package amazon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

// Design In-Memory File System
public class Q588 {

    // it's expandable to include more commands like rmdir, rename file / sub-dir, relocate file / sub-dir
    public class FileSystem {
        class Dir {
            HashMap< String, Dir > dirs = new HashMap < > ();
            HashMap < String, String > files = new HashMap < > ();
        }
        Dir root;
        public FileSystem() {
            root = new Dir();
        }
        // O(m+n+klgk) - m for length of path, n for depth of directory, k for # of files and sub-dirs.
        public List < String > ls(String path) {
            Dir t = root;
            List < String > files = new ArrayList< >();
            if (!path.equals("/")) {
                String[] d = path.split("/");
                for (int i = 1; i < d.length - 1; i++) {
                    t = t.dirs.get(d[i]);
                }
                if (t.files.containsKey(d[d.length - 1])) {
                    files.add(d[d.length - 1]);
                    return files;
                } else {
                    t = t.dirs.get(d[d.length - 1]);
                }
            }
            files.addAll(new ArrayList < > (t.dirs.keySet()));
            files.addAll(new ArrayList < > (t.files.keySet()));
            Collections.sort(files);
            return files;
        }
        // O(m+n)
        public void mkdir(String path) {
            Dir t = root;
            String[] d = path.split("/");
            for (int i = 1; i < d.length; i++) {
                if (!t.dirs.containsKey(d[i]))
                    t.dirs.put(d[i], new Dir());
                t = t.dirs.get(d[i]);
            }
        }
        // O(m+n)
        public void addContentToFile(String filePath, String content) {
            Dir t = root;
            String[] d = filePath.split("/");
            for (int i = 1; i < d.length - 1; i++) {
                t = t.dirs.get(d[i]);
            }
            t.files.put(d[d.length - 1], t.files.getOrDefault(d[d.length - 1], "") + content);
        }

        public String readContentFromFile(String filePath) {
            Dir t = root;
            String[] d = filePath.split("/");
            for (int i = 1; i < d.length - 1; i++) {
                t = t.dirs.get(d[i]);
            }
            return t.files.get(d[d.length - 1]);
        }
    }
    /*
    // list only directories or files need to traverse whole structure.
    class FileSystem {

        class File {
            Map<String, File> files = new HashMap<>();
            String content = "";
            boolean isFile = false;
        }
        File root;
        public FileSystem() {
            root = new File();
        }

        public List<String> ls(String path) {
            File t = root;
            List<String> files = new ArrayList<>();
            if(!path.equals("/")) {
                String[] d = path.split("/");
                for(int i = 1; i < d.length; i++) {
                    if(!t.files.containsKey(d[i])) {
                        t.files.put(d[i], new File());
                    }
                    t = t.files.get(d[i]);
                }
                if(t.isFile) {
                    files.add(d[d.length - 1]);
                    return files;
                }
            }
            files.addAll(t.files.keySet());
            Collections.sort(files);
            return files;
        }

        public void mkdir(String path) {
            File t = root;
            String[] d = path.split("/");
            for(int i = 1; i < d.length; i++) {
                if(!t.files.containsKey(d[i])) {
                    t.files.put(d[i], new File());
                }
                t = t.files.get(d[i]);
            }
        }

        public void addContentToFile(String filePath, String content) {
            File t = root;
            String[] d = filePath.split("/");
            for(int i = 1; i < d.length; i++) {
                if(!t.files.containsKey(d[i])) {    // needed !
                    t.files.put(d[i], new File());
                }
                t = t.files.get(d[i]);
            }
            t.isFile = true;
            t.content += content;
        }

        public String readContentFromFile(String filePath) {
            File t = root;
            String[] d = filePath.split("/");
            for(int i = 1; i < d.length; i++) {
//                if(!t.files.containsKey(d[i])) {      // not needed !
//                    t.files.put(d[i], new File());
//                }
                t = t.files.get(d[i]);
            }
            return t.content;
        }
    }
    */
}
