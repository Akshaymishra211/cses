//https://cses.fi/paste/f987ef4332df8f432a93ab/
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
        BuildingRoads solver = new BuildingRoads();
        solver.solve(1, in, out);
        out.close();
    }
 
    static class BuildingRoads {
        static boolean vis[];
        static ArrayList<ArrayList<Integer>> adj;
 
        static void dfs(int node) {
            for (int adjnode : adj.get(node)) {
                if (!vis[adjnode]) {
                    vis[adjnode] = true;
                    dfs(adjnode);
                }
            }
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
            vis = new boolean[n + 1];
            ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
            dfs(1);
            for (int i = 2; i < n + 1; i++) {
                if (!vis[i]) {
                    int temp = i;
                    ans.add(new ArrayList<Integer>() {
                        {
                            add(1);
                            add(temp);
                        }
                    });
                    vis[i] = true;
                    dfs(i);
                }
            }
            out.println(ans.size());
            for (int i = 0; i < ans.size(); i++) {
                out.println(ans.get(i).get(0) + " " + ans.get(i).get(1));
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
