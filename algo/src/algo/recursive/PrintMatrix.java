package algo.recursive;

/**
 * 题目：
 * 给定一个N，打印按如下规律生成的N*N的矩阵
 * 1    2    3    4    
 * 12   13   14   5    
 * 11   16   15   6    
 * 10   9    8    7
 *
 * 思路：
 * 找出计算每个坐标对应的值的方法，然后逐个计算，逐个打印
 * 要计算某个坐标的值，按以下的思路：
 * 若这个点位于矩阵的最外圈，则可以直接求解
 * 若不是，则以递归的方式，计算它在内层小矩阵的值，此时要更新坐标，矩阵的维数，左上角的起始值
 */
public class PrintMatrix {

    /**
     * 递归方法，计算坐标i,j的值
     * 
     * @param i
     * @param j
     * @param N
     * @param startValue
     * @return
     */
    private static int calcValue(int i, int j, int N, int startValue) {
        if (i == 1) {
            return startValue + j;
        } else if (j == N) {
            return startValue + N + i - 1;
        } else if (i == N) {
            return startValue + 3 * N - 1 - j;
        } else if (j == 1) {
            return startValue + 4 * N - 2 - i;
        }

        return calcValue(i - 1, j - 1, N - 2, 4 * N - 4 + startValue);
    }

    /**
     * 打印目标矩阵
     */
    public static void printMatrix(int N) {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                System.out.print(String.format("%-5d", calcValue(i, j, N, 0)));
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        printMatrix(5);
    }
}
