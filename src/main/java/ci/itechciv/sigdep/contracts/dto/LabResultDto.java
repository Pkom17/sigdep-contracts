package ci.itechciv.sigdep.contracts.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

/**
 * One lab observation = one DTO. A bilan biologique encounter on the
 * source side typically yields several DTOs (one per concept observed).
 *
 * Exactly one of {valueNumeric, valueText, valueCoded} is non-null.
 */
public record LabResultDto(
        UUID sourceUuid,                 // obs.uuid
        UUID patientSourceUuid,
        UUID encounterSourceUuid,        // encounter.uuid
        LocalDate examDate,              // encounter_datetime
        String testUuid,                 // concept UUID
        String testName,                 // resolved concept name
        BigDecimal valueNumeric,
        String valueText,
        String valueCoded,               // resolved concept_name when value_coded is set
        String unit,
        Boolean voided
) {}
