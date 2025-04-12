import java.util.*;

//Stack<Integer> stack = new Stack<>();
//HashSet<Character> set = new HashSet<>();
//ArrayList<Integer> arr = new ArrayList<>();
//Map<Integer, Integer> map = new HashMap<>();

class Solution {
    public int maxAbsoluteSum(int[] nums) {
        int result = 0, l = nums.length;
        for(int i = 0; i < l; i++){
            int total = nums[i];
            if(i == l - 1 || i == 0){
                result = Math.max(result, Math.abs(nums[i]));
            }else{
                for(int j = i + 1; j < l; j++){
                    total += nums[j];
                    result = Math.max(result, Math.abs(total));
                }
            }
        }
        return result;
    }
}