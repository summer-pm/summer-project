package ru.tinkoff.summer.taskmicroservice.domain.entity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;


public class AttemptTest {
    private static final String CORRECT_CODE = "class Solution{" +
            "public int method(int a, int b}{return 0}}";

    Task task = Task.of();
    @Test
    public void canCreateAttempt(){
        Attempt attempt = new Attempt();
        assertThat(attempt).isNotNull();
    }
    @Test
    public void createBlankCode_Error(){

        assertThatThrownBy(() -> {var attempt = Attempt.of("", null);})
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Введите код");
    }
    @Test
    public void createNullCode_Error(){
        assertThatThrownBy(() -> {var attempt = Attempt.of(null, null);})
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Введите код");
    }
    @Test
    public void createNullTask_Error(){
        assertThatThrownBy(() -> {var attempt = Attempt.of(CORRECT_CODE, null);})
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Введите код");
    }
}
