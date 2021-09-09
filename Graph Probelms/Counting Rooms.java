//https://cses.fi/paste/8e6b761a00f334ce2a7f3d/
import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.util.Collection;
import java.util.InputMismatchException;
import java.io.IOException;
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
        CountingRooms solver = new CountingRooms();
        solver.solve(1, in, out);
        out.close();
    }
 
    static class CountingRooms {
        static boolean isValid(int x, int y, int n, int m) {
            return x >= 0 && x < n && y >= 0 && y < m;
        }
 
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.nextInt(), m = in.nextInt();
            char arr[][] = new char[n][m];
            for (int i = 0; i < n; i++) {
                arr[i] = in.next().toCharArray();
            }
            Queue<CountingRooms.Pair> q = new LinkedList<>();
            int cnt = 0;
            boolean vis[][] = new boolean[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (arr[i][j] == '.' && !vis[i][j]) {
                        cnt++;
                        vis[i][j] = true;
                        q.add(new CountingRooms.Pair(i, j));
                        while (!q.isEmpty()) {
                            CountingRooms.Pair p = q.poll();
                            if (isValid(p.x, p.y + 1, n, m) && arr[p.x][p.y + 1] == '.' && !vis[p.x][p.y + 1]) {
                                vis[p.x][p.y + 1] = true;
                                q.add(new CountingRooms.Pair(p.x, p.y + 1));
                            }
                            if (isValid(p.x, p.y - 1, n, m) && arr[p.x][p.y - 1] == '.' && !vis[p.x][p.y - 1]) {
                                vis[p.x][p.y - 1] = true;
                                q.add(new CountingRooms.Pair(p.x, p.y - 1));
                            }
                            if (isValid(p.x + 1, p.y, n, m) && arr[p.x + 1][p.y] == '.' && !vis[p.x + 1][p.y]) {
                                vis[p.x + 1][p.y] = true;
                                q.add(new CountingRooms.Pair(p.x + 1, p.y));
                            }
                            if (isValid(p.x - 1, p.y, n, m) && arr[p.x - 1][p.y] == '.' && !vis[p.x - 1][p.y]) {
                                vis[p.x - 1][p.y] = true;
                                q.add(new CountingRooms.Pair(p.x - 1, p.y));
                            }
                        }
                    }
                }
            }
            out.println(cnt);
        }
 
        static class Pair {
            int x;
            int y;
 
            public Pair(int x, int y) {
                this.x = x;
                this.y = y;
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
 
        public String nextString() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            StringBuilder res = new StringBuilder();
            do {
                if (Character.isValidCodePoint(c)) {
                    res.appendCodePoint(c);
                }
                c = read();
            } while (!isSpaceChar(c));
            return res.toString();
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
 
        public String next() {
            return nextString();
        }
 
        public interface SpaceCharFilter {
            public boolean isSpaceChar(int ch);
 
        }
 
    }
}
 
