package ci.itechciv.sigdep.contracts.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

public record VisitDto(
        UUID sourceUuid,
        UUID patientSourceUuid,
        String sourceForm,
        LocalDate visitDate,
        LocalDate nextVisitDate,
        String tbScreeningResult,
        Boolean tbDiagnosed,
        String tbTreatmentStatus,
        LocalDate tbTreatmentStartDate,
        Short whoStage,
        String cdcStage,
        Boolean ctxPrescribed,
        LocalDate ctxStartDate,
        LocalDate ivsaSuccessConfirmationDate,
        Boolean isPregnant,
        Boolean isBreastfeeding,
        BigDecimal weightKg,
        BigDecimal heightCm,
        Map<String, Object> extraData,
        Boolean voided
) {}
