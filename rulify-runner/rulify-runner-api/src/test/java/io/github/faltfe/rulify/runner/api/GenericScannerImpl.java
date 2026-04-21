package io.github.faltfe.rulify.runner.api;

import io.github.faltfe.rulify.runner.api.annotations.Rule;
import java.util.Set;
import lombok.NonNull;

class GenericScannerImpl extends GenericScanner<Rule> {

    protected GenericScannerImpl(@NonNull String packageName) {
        super(packageName);
    }

    @Override
    public Set<Class<?>> scan() {
        return Set.of();
    }
}
