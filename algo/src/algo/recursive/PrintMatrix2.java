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
 * 要计算某个坐标点的值，将这个任务分解成几个小任务：
 * 1）确定这个坐标点所在的圈，最外层为第一圈，依次向里，第二圈。。。
 * 2）确定每个圈的起始值
 * 3）确定坐标点与所在圈的起始点的距离
 * 4）由2），3）计算坐标点的值
 */
public class PrintMatrix2 {

    /**
     * 计算某个坐标所在的圈，即离最外圈的最短距离
     * 
     * @param i
     * @param j
     * @param N
     * @return
     */
    private static int locateCircle(int i, int j, int N) {
        int min = Integer.MAX_VALUE;
        if (i < min) {
            min = i;
        }
        if (N - i + 1 < min) {
            min = N - i + 1;
        }
        if (j < min) {
            min = j;
        }
        if (N - j + 1 < min) {
            min = N - j + 1;
        }
        return min;
    }

    /**
     * 计算某个圈左上角的数值
     * 
     * @param circleId
     * @param N
     * @return
     */
    private static int getTopLeftNumber(int circleId, int N) {
        int sum = 0;
        for (int i = 1; i < circleId; i++) {
            sum += 4 * N - 4;
            N -= 2;
        }
        return sum + 1;
    }

    /**
     * 获取某圈内的点与圈的左上角点的距离
     * 
     * @param circleId
     * @param i
     * @param j
     * @return
     */
    private static int getDistanceFromTopLeft(int circleId, int i, int j, int N) {
        if (i == circleId) {
            return j - circleId;
        } else if (j == N - circleId + 1) {
            return (N - 2 * (circleId - 1)) + i - circleId - 1;
        } else if (i == N - circleId + 1) {
            return (N - 2 * (circleId - 1)) * 2 - 1 + (N - circleId + 1) - j - 1;
        } else if (j == circleId) {
            return (N - 2 * (circleId - 1)) * 3 - 2 + (N - circleId + 1) - i - 1;
        } else {
            throw new RuntimeException("not in same circle.");
        }
    }

    /**
     * 计算坐标i,j的数值
     * 
     * @param i
     * @param j
     * @param N
     * @return
     */
    private static int calcValue(int i, int j, int N) {
        int circleId = locateCircle(i, j, N);
        return getTopLeftNumber(circleId, N) + getDistanceFromTopLeft(circleId, i, j, N);
    }

    /**
     * 打印目标矩阵
     * 
     * @param N
     */
    public static void printMatrix(int N) {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                System.out.print(String.format("%-5d", calcValue(i, j, N)));
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        printMatrix(6);
    }
}
