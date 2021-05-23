package debug;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class DebugDemo1 {

    public static void main(String[] args) {
        System.out.println("leet code...");
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> list = new ArrayList<>();
        int length = nums.length;
        for (int first = 0; first < length; first++) {
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            int third = length - 1;
            for (int second = first + 1; second < length; second++) {
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                while (second < third && nums[first] + nums[second] + nums[third] > 0) {
                    --third;
                }
                if (second == third) {
                    break;
                }
                if(nums[first] + nums[second] + nums[third] ==0){
                    List<Integer> res=new ArrayList<>();
                    res.add(nums[first]);
                    res.add(nums[second]);
                    res.add(nums[third]);
                    list.add(res);
                }
            }
        }
        return list;
    }
}
