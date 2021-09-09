//https://cses.fi/paste/22b666621adb3a0d2aa7df/
import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.util.InputMismatchException;
import java.io.IOException;
import java.util.ArrayList;
import java.io.InputStream;
 
/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author Akshay Mishra
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        OutputWriter out = new OutputWriter(outputStream);
        RoundTrip solver = new RoundTrip();
        solver.solve(1, in, out);
        out.close();
    }
 
    static class RoundTrip {
        static ArrayList<ArrayList<Integer>> adj;
        static boolean vis[];
        static boolean done[];
        static boolean cycle_found;
        static boolean cycle_over;
        static int cycle_start_at;
        static ArrayList<Integer> res;
 
        static boolean dfs(int node, int parent_node) {
            done[node] = true;
            boolean ans = false;
            for (int adj_node : adj.get(node)) {
                if (adj_node != parent_node) {
                    if (!vis[adj_node]) {
                        vis[adj_node] = true;
                        ans = ans || dfs(adj_node, node);
                        if (ans) {
                            if (node == cycle_start_at) {
                                res.add(node);
                                cycle_over = true;
                            } else {
                                if (!cycle_over) {
                                    res.add(node);
                                }
                            }
                            break;
                        }
                    } else {
                        res.add(adj_node);
                        res.add(node);
                        cycle_start_at = adj_node;
                        cycle_over = false;
                        return cycle_found = true;
                    }
                }
            }
            vis[node] = false;
            return ans;
        }
 
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.nextInt(), m = in.nextInt();
            adj = new ArrayList<>();
            for (int i = 0; i < n + 1; i++) {
                adj.add(new ArrayList<>());
            }
            for (int i = 0; i < m; i++) {
                int u = in.nextInt(), v = in.nextInt();
                adj.get(u).add(v);
                adj.get(v).add(u);
            }
            cycle_over = true;
            vis = new boolean[n + 1];
            done = new boolean[n + 1];
            res = new ArrayList<>();
            for (int i = 0; i < n + 1; i++) {
                if (!done[i]) {
                    vis[i] = true;
                    if (dfs(i, 0)) {
                        break;
                    }
                }
            }
            if (!cycle_found) {
                out.println("IMPOSSIBLE");
            } else {
                out.println(res.size());
                for (int val : res) {
                    out.print(val + " ");
                }
                out.println();
            }
        }
 
    }
 
    static class InputReader {
        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;
        private InputReader.SpaceCharFilter filter;
 
        public InputReader(InputStream stream) {
            this.stream = stream;
        }
 
        public int read() {
            if (numChars == -1) {
                throw new InputMismatchException();
            }
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0) {
                    return -1;
                }
            }
            return buf[curChar++];
        }
 
        public int nextInt() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9') {
                    throw new InputMismatchException();
                }
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }
 
        public boolean isSpaceChar(int c) {
            if (filter != null) {
                return filter.isSpaceChar(c);
            }
            return isWhitespace(c);
        }
 
        public static boolean isWhitespace(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }
 
        public interface SpaceCharFilter {
            public boolean isSpaceChar(int ch);
 
        }
 
    }
 
    static class OutputWriter {
        private final PrintWriter writer;
 
        public OutputWriter(OutputStream outputStream) {
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
        }
 
        public OutputWriter(Writer writer) {
            this.writer = new PrintWriter(writer);
        }
 
        public void print(Object... objects) {
            for (int i = 0; i < objects.length; i++) {
                if (i != 0) {
                    writer.print(' ');
                }
                writer.print(objects[i]);
            }
        }
 
        public void println() {
            writer.println();
        }
 
        public void println(Object... objects) {
            print(objects);
            writer.println();
        }
 
        public void close() {
            writer.close();
        }
 
        public void println(int i) {
            writer.println(i);
        }
 
    }
}
