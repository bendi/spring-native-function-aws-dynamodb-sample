package pl.bedkowski.spring.lambda;

import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component("uppercase")
public class UpperCaseFunction implements Function<String, String> {
    @Override
    public String apply(String s) {
        return s.toUpperCase();
    }
}
