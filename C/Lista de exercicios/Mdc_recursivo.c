#include <stdio.h>

int MDC(int x,int y){
    if (x == y){
        return x;
    }
    else if((x % 2)== 0 && (y % 2) == 0){
        return 2*MDC(x/2,y/2);
    }
    else if((x%2) == 0 && (y%2)== 1){
        return MDC(x/2,y);
    }
    else if((x%2) == 1 && (y%2) == 0){
        return MDC(x,y/2);
    }
    else if((x%2) == 1 && (y%2) == 1 && x > y){
        return MDC((x-y)/2,y);
    }
    else if((x%2) == 1 && (y%2) == 1 && x < y){
        return MDC(x,(y-x)/2);
    }

}

int main(void){

    printf("%d\n", MDC(270,192));
    printf("%d\n", MDC(35,10));
    printf("%d\n", MDC(10,15));
    printf("%d\n", MDC(31,2));

}