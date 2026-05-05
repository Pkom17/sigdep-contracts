package ci.itechciv.sigdep.contracts.dto;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

/**
 * Patient closure / "Cloture de dossier" record. One per encounter of
 * type PEC - Cloture (id=5 in OpenMRS).
 */
public record ClosureDto(
        UUID sourceUuid,
        UUID patientSourceUuid,

        // Closure category derived on the agent (TRANSFER, AUTO_TRANSFER,
        // DEATH, VOLUNTARY_STOP, HIV_NEGATIVE, OTHER).
        String closureType,
        LocalDate closureDate,

        // Transfer
        LocalDate transferDate,
        String transferDestination,
        String transferReason,

        // Death
        LocalDate deathDate,
        LocalDate actualDeathDate,
        String deathCauseCode,
        String deathCauseText,

        // Voluntary stop / HIV negative
        LocalDate voluntaryStopDate,
        LocalDate hivNegativeDate,

        Map<String, Object> extraData,
        Boolean voided
) {}
