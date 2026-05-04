package ci.itechciv.sigdep.contracts;

import java.util.List;
import java.util.UUID;

public record SyncBatchResponse(
        UUID batchId,
        int accepted,
        int rejected,
        List<RecordError> errors
) {
    public record RecordError(UUID sourceUuid, String code, String message) {}
}
