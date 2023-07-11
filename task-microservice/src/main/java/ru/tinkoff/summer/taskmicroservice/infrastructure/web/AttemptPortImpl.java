package ru.tinkoff.summer.taskmicroservice.infrastructure.web;

import org.springframework.stereotype.Repository;
import ru.tinkoff.summer.taskmicroservice.application.port.data.AttemptPort;
import ru.tinkoff.summer.taskmicroservice.domain.Attempt;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AttemptPortImpl implements AttemptPort {
   private final List<Attempt> attempts = new ArrayList();
    @Override
    public Attempt save(Attempt attempt) {
        attempts.add(attempt);
        attempt.setId(attempts.size() + 1);
        return attempt;
    }

    @Override
    public Attempt getById(long id) {
        return null;
    }
}
