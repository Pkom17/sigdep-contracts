package ci.itechciv.sigdep.contracts;

import java.util.List;
import java.util.UUID;

public record SyncBatchRequest<T>(
        String siteCode,
        UUID batchId,
        EntityType entityType,
        List<T> records
) {}
