package de.faltfe.rulify.runner.cdi.example;

import de.faltfe.rulify.runner.api.annotations.Rule;

import java.util.Objects;

@Rule(CustomRule.class)
public class Entity {
    private String name;
    private final boolean isActive;

    public Entity(boolean isActive) {
        this.isActive = isActive;
    }


    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return isActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return isActive == entity.isActive && Objects.equals(name, entity.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, isActive);
    }

    @Override
    public String toString() {
        return "Entity{" +
                "name='" + name + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
