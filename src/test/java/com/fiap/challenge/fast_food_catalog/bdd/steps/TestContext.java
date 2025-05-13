package com.fiap.challenge.fast_food_catalog.bdd.steps;

import io.cucumber.spring.ScenarioScope;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.env.Environment;

import java.util.HashMap;

@ScenarioScope
public class TestContext {

    @Setter
    @Getter
    private Environment environment;

    private HashMap<String, Object> map = new HashMap<>();

    public <T> T get(String key, Class<T> type) {
        Object value = map.get(key);
        if (value == null) {
            return null;
        }

        if (type.isInstance(value)) {
            return type.cast(value);
        } else {
            throw new ClassCastException(
                    "Value for key '" + key + "' is of type '" + value.getClass().getName() +
                            "' and cannot be cast to '" + type.getName() + "'"
            );
        }

    }

    public void add(String key, Object object) {
        map.put(key, object);
    }


    public void setEnv(Environment environment) {
        this.environment = environment;
    }

}
