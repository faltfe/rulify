package de.faltfe.rulify.common.effects;

import de.faltfe.rulify.api.SideEffect;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CollectionEffect {

    public <T> SideEffect<List<T>> removeNull() {
        return list -> list.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }
}
