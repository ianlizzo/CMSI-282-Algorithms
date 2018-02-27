public class MatrixMultiplicationCost {

    public int findCost(int arr[]){
        int temp[][] = new int[arr.length][arr.length];
        int q = 0;
        for(int l=2; l < arr.length; l++){
            for(int i=0; i < arr.length - l; i++){
                int j = i + l;
                temp[i][j] = 1000000;
                for(int k=i+1; k < j; k++){
                    q = temp[i][k] + temp[k][j] + arr[i]*arr[k]*arr[j];
                    if(q < temp[i][j]){
                        temp[i][j] = q;
                    }
                }
            }
        }
        return temp[0][arr.length-1];
    }

    public static void main(String args[]){
        MatrixMultiplicationCost mmc = new MatrixMultiplicationCost();
        int arr[] = {5,10,10,-6};
        int cost = mmc.findCost(arr);
        System.out.println(cost);
    }
}
