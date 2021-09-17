package de.faltfe.rulify.common.conditions;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ObjectConditionTest {

    @Nested
    class IsNull {
        @Test
        void withNull() {
            assertTrue(ObjectCondition.isNull().test(null));
        }

        @Test
        void withObject() {
            assertFalse(ObjectCondition.isNull().test(new Object()));
        }
    }

    @Nested
    class IsNotNull {
        @Test
        void withNull() {
            assertFalse(ObjectCondition.isNotNull().test(null));
        }

        @Test
        void withObject() {
            assertTrue(ObjectCondition.isNotNull().test(new Object()));
        }
    }

}