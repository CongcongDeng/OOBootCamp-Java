package org.oobootcamp.warmup;

public record FizzBuzzGame(int count) {


    public String countOff() {
        if(this.count%3==0 && this.count%5==0){
            return "FizzBuzz";
        }
        if (this.count % 3 == 0){
            return "Fizz";
        }
        if (this.count % 5 == 0){
            return "Buzz";
        }

        return null;
    }
}
