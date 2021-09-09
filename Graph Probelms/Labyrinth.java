//https://cses.fi/paste/98f70b11c799262c2a8cf8/
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
        Labyrinth solver = new Labyrinth();
        solver.solve(1, in, out);
        out.close();
    }
 
    static class Labyrinth {
        static int n;
        static int m;
 
        static boolean isValid(int x, int y) {
            return x >= 0 && x < n && y >= 0 && y < m;
        }
 
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.nextInt(), m = in.nextInt();
            Labyrinth.n = n;
            Labyrinth.m = m;
            char[][] arr = new char[n][m];
            for (int i = 0; i < n; i++) {
                arr[i] = in.next().toCharArray();
            }
            boolean[][] vis = new boolean[n][m];
            int a1 = -1, a2 = -1, b1 = -1, b2 = -1;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (arr[i][j] == 'A') {
                        a1 = i;
                        a2 = j;
                    }
                    if (arr[i][j] == 'B') {
                        b1 = i;
                        b2 = j;
                    }
                }
            }
            int ans = 0;
            int[][] dis = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    dis[i][j] = Integer.MAX_VALUE;
                }
            }
            dis[b1][b2] = 0;
            Queue<Labyrinth.Pair> q = new LinkedList<>();
            vis[b1][b2] = true;
            q.add(new Labyrinth.Pair(b1, b2));
            while (!q.isEmpty()) {
                Labyrinth.Pair p = q.poll();
                if (isValid(p.x, p.y - 1) && arr[p.x][p.y - 1] != '#' && !vis[p.x][p.y - 1]) {
                    vis[p.x][p.y - 1] = true;
                    dis[p.x][p.y - 1] = dis[p.x][p.y] + 1;
                    q.add(new Labyrinth.Pair(p.x, p.y - 1));
                }
                if (isValid(p.x, p.y + 1) && arr[p.x][p.y + 1] != '#' && !vis[p.x][p.y + 1]) {
                    vis[p.x][p.y + 1] = true;
                    dis[p.x][p.y + 1] = dis[p.x][p.y] + 1;
                    q.add(new Labyrinth.Pair(p.x, p.y + 1));
                }
                if (isValid(p.x - 1, p.y) && arr[p.x - 1][p.y] != '#' && !vis[p.x - 1][p.y]) {
                    vis[p.x - 1][p.y] = true;
                    dis[p.x - 1][p.y] = dis[p.x][p.y] + 1;
                    q.add(new Labyrinth.Pair(p.x - 1, p.y));
                }
                if (isValid(p.x + 1, p.y) && arr[p.x + 1][p.y] != '#' && !vis[p.x + 1][p.y]) {
                    vis[p.x + 1][p.y] = true;
                    dis[p.x + 1][p.y] = dis[p.x][p.y] + 1;
                    q.add(new Labyrinth.Pair(p.x + 1, p.y));
                }
            }
            if (dis[a1][a2] == Integer.MAX_VALUE) {
                out.println("NO");
            } else {
                StringBuilder str = new StringBuilder("");
                int temp = dis[a1][a2];
                int x = a1, y = a2;
                while (--temp >= 0) {
                    if (isValid(x, y - 1) && dis[x][y - 1] == temp) {
                        str.append('L');
                        y--;
                        continue;
                    }
                    if (isValid(x, y + 1) && dis[x][y + 1] == temp) {
                        str.append('R');
                        y++;
                        continue;
                    }
                    if (isValid(x - 1, y) && dis[x - 1][y] == temp) {
                        str.append('U');
                        x--;
                        continue;
                    }
                    if (isValid(x + 1, y) && dis[x + 1][y] == temp) {
                        str.append('D');
                        x++;
                        continue;
                    }
                }
                out.println("YES");
                out.println(str.length());
                out.println(str.toString());
            }
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
