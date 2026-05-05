package ci.itechciv.sigdep.contracts.dto;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

/**
 * Patient enrollment / "Ouverture de dossier" record.
 * One per patient; subsequent visits go through VisitDto.
 */
public record TreatmentInitiationDto(
        UUID sourceUuid,
        UUID patientSourceUuid,
        LocalDate enrollmentDate,
        LocalDate arvInitDate,
        LocalDate hivTestDate,
        String hivType,
        String entryPoint,
        Map<String, Object> extraData,
        Boolean voided
) {}
