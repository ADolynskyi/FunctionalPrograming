package org.example;

class RandomAlgorithm {
    private long a,c,m;
    private long x=0;
    private long seed;

    public RandomAlgorithm(long a, long c, long m) {
        this.a = a;
        this.c = c;
        this.m = m;
    }
    public long next(long x){

        if(this.x==0||seed!=x) {
            seed=x;
            this.x = ((a * x + c) % m);
        }else {
            this.x = ((a * this.x + c) % m);
        }
return this.x;
    }
}
