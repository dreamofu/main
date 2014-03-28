package algo.dp;

/*
 * 最大连子数组的乘积
 * 考虑到负负得正，因此需要保存最大，最小两种状态
 * dpMax[i]表示以num_i结尾的最大连续子数组乘积
 * dpMin[i]表示以num_i结尾的最小连续子数组乘积，则有以下的状态转移方程
 * 
 * dpMax[i] = max{num_i, dpMax[i-1]*num_i, dpMin[i-1]*num_i}
 * dpMax[i] = min{num_i, dpMax[i-1]*num_i, dpMin[i-1]*num_i}
 * 
 * dp初值为dpMax[1] = num_i, dpMin[1] = num_i
 */
public class MaxContSubSeqMulti {

	static float maxMult = 0.0f;
	static int maxIdx = -1;
	
	static void maxContSubSeqMulti(float[] data, float[] dpMax, float[] dpMin) {
		// 1. 设置dp初值
		dpMax[1] = data[0];
		dpMin[1] = data[0];

		// 2. 自底向上求解dp
		for (int i = 2; i <= data.length; i++) {
			dpMax[i] = max(data[i-1], data[i-1] * dpMax[i-1], data[i-1] * dpMin[i-1]);
			dpMin[i] = min(data[i-1], data[i-1] * dpMax[i-1], data[i-1] * dpMin[i-1]);
			
			if (dpMax[i] > maxMult) {
				maxMult = dpMax[i];
				maxIdx = i;
			}
		}
		
		//3. 依据最优值，输出最优解
		output(data, dpMax, dpMin);
	}


	private static void output(float[] data, float[] dpMax, float[] dpMin) {
		System.out.println(maxMult);
		for (int i = maxIdx; i >= 0; i--) {
			System.out.println(data[i-1]);
			if(dpMax[i] == data[i-1]){
				break;
			}
		}
	}

	private static float max(float max, float max2, float max3) {
		return Math.max(max, Math.max(max2, max3));
	}

	private static float min(float min, float min2, float min3) {
		return Math.min(min, Math.min(min2, min3));
	}
	
	public static void main(String[] args) {
		float[] data = { -2.5f, 4, 0, 3, 0.5f, 8, -1 };
		maxContSubSeqMulti(data, new float[data.length+1], new float[data.length+1]);
	}
}
