package org.oobootcamp.warmup;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FizzBuzzGameTest
{

    @Test
    void should_return_Fizz_given_count_is_multiple_of_3() {
        FizzBuzzGame fizzBuzzGame =  new FizzBuzzGame(6);
        assertThat(fizzBuzzGame.countOff()).isEqualTo("Fizz");
    }

    @Test
    void should_return_Buzz_given_count_is_multiple_of_5() {
        FizzBuzzGame fizzBuzzGame =  new FizzBuzzGame(10);
        assertThat(fizzBuzzGame.countOff()).isEqualTo("Buzz");
    }

    @Test
    void should_return_FizzBuzz_given_count_is_multiple_of_5_and_3() {
        FizzBuzzGame fizzBuzzGame =  new FizzBuzzGame(15);
        assertThat(fizzBuzzGame.countOff()).isEqualTo("FizzBuzz");
    }
}
