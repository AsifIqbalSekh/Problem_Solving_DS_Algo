class Solution{
    static int isPrime(int N){
        if(N<2){
            return 0;
        }
        
        int n=(int)Math.sqrt(N);

        for(int i=2;i<=n;i++){
            if(N!=i && N%i==0){
                return 0;
            }
        }
        
        return 1;
    }
}