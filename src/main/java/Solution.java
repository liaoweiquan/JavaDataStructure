import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

class Solution {
    private int partition(int[] nums, int l, int r){
        // int v = nums[l];
        int vidx = (int)(Math.random() * (r - l + 1)) + l;
        swap(nums, l, vidx);
        int v = nums[l];
        int i = l + 1, j = r;
        while(true){
            while(i <= r && nums[i] < v) ++ i;
            while(j >= l + 1 && nums[j] > v) -- j;
            if(i > j) break;
            swap(nums, i, j);
            ++ i;
            -- j;
        }
        swap(nums, l, j);
        return j;
    }
    private void swap(int[] nums, int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
    private int findKthLargestHelper(int[] nums, int l, int r, int k){
        int p = partition(nums, l, r);
        int L = p - l + 1;
        if(L == k){
            return nums[p];
        }else if(L < k){
            return findKthLargestHelper(nums, p + 1, r, k - L);
        }else{
            return findKthLargestHelper(nums, l, p - 1, k);
        }
    }

    // 0, 1, 2, 3, 4
    // 5 - 2 = 3
    public int findKthLargest(int[] nums, int k) {
        int l = 0, r = nums.length - 1;
        int pos = nums.length - k;
        while (l <= r){
            int p = partition(nums, l, r);
            if(p == pos){
                return nums[pos];
            }else if(p > pos){
                r = p - 1;
            }else{
                l = p + 1;
            }
        }
        Queue<Integer> q = new ArrayDeque<>();
        return -1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {3,2,1,5,6,4};
        System.out.println(solution.findKthLargest(nums, 2));
    }
}