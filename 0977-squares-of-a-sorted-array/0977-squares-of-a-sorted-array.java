class Solution {
    public int[] sortedSquares(int[] nums) {
    int l=0;
    int r=nums.length-1;
    int[] result=new int[nums.length];
    for(int i=nums.length-1;i>=0;i--){
        int leftsqu=nums[l]*nums[l];
        int rightsqu=nums[r]*nums[r];
        if(leftsqu>rightsqu){
            result[i]=leftsqu;
            l++;
        }
        else{
            result[i]=rightsqu;
            r--;
        }
    }
    return result;
}
}