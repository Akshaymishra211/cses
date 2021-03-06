//https://cses.fi/paste/b9c3310724d04e0e2a95c5/
import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.util.Collection;
import java.util.InputMismatchException;
import java.io.IOException;
import java.util.ArrayList;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.util.Queue;
import java.util.LinkedList;
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
        MessageRoute solver = new MessageRoute();
        solver.solve(1, in, out);
        out.close();
    }
 
    static class MessageRoute {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.nextInt(), m = in.nextInt();
            ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
            for (int i = 0; i < n + 1; i++) {
                adj.add(new ArrayList<>());
            }
            for (int i = 0; i < m; i++) {
                int u = in.nextInt(), v = in.nextInt();
                adj.get(u).add(v);
                adj.get(v).add(u);
            }
            Queue<Integer> q = new LinkedList<>();
            q.add(1);
            int dis[] = new int[n + 1];
            for (int i = 0; i < n + 1; i++) {
                dis[i] = Integer.MAX_VALUE;
            }
            dis[1] = 0;
            while (!q.isEmpty()) {
                int node = q.poll();
                for (int adj_node : adj.get(node)) {
                    if (dis[adj_node] > dis[node] + 1) {
                        dis[adj_node] = 1 + dis[node];
                        q.add(adj_node);
                    }
                }
            }
            if (dis[n] == Integer.MAX_VALUE) {
                out.println("IMPOSSIBLE");
            } else {
                ArrayList<Integer> ans = new ArrayList<>();
                ans.add(n);
                int temp = dis[n], curr_node = n;
                while (--temp >= 0) {
                    for (int adj_node : adj.get(curr_node)) {
                        if (dis[adj_node] == temp) {
                            ans.add(adj_node);
                            curr_node = adj_node;
                            break;
                        }
                    }
                }
                out.println(ans.size());
                for (int i = ans.size() - 1; i > -1; i--) {
                    out.print(ans.get(i) + " ");
                }
                out.println();
            }
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
}
