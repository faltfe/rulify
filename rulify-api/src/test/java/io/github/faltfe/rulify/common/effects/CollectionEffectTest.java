package io.github.faltfe.rulify.common.effects;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CollectionEffectTest {

    @Test
    void removeNullObjects() {
        List<Object> objects = Arrays.asList(new Object(), null, new Object());
        assertEquals(3, objects.size());
        assertEquals(2, CollectionEffect.removeNull().apply(objects).size());
    }

}
