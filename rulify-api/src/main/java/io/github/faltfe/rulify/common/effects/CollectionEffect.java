package io.github.faltfe.rulify.common.effects;

import io.github.faltfe.rulify.api.Modifier;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CollectionEffect {

    private CollectionEffect() {}

    public static <T> Modifier<List<T>> removeNull() {
        return list -> list.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }
}
