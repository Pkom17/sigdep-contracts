package ci.itechciv.sigdep.contracts.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Pediatric extension of TreatmentInitiationDto. Nested inside the parent
 * DTO; null when the source enrolment had no pediatric-specific obs.
 *
 * No PII fields here by design (no parent/guardian names or phone numbers).
 */
public record PediatricInitiationDto(
        // Birth / baby clinical snapshot
        BigDecimal birthWeightKg,
        BigDecimal birthLengthCm,
        BigDecimal headCircumferenceCm,
        Short apgarScore,
        String deliveryMode,
        String deliveredAtFacility,

        // Mother PTME
        String motherReceivedPtme,
        String motherHivStatus,
        String motherVitalStatus,
        String motherPtmeRegimen,
        String infantArvProphylaxisGiven,
        String infantArvProtocol,

        // Nutrition / suivi
        String feedingMode,
        LocalDate weaningDate,
        String vaccinations,

        // Family / context (coded values only)
        String fatherVitalStatus,
        String fatherEducationLevel,
        String fatherActivityType,
        String motherEducationLevel,
        String motherActivityType,
        String guardianVitalStatus,
        String guardianEducationLevel,
        String guardianActivityType,
        String guardianHivStatus,

        // Other
        LocalDate admissionDate,
        String schoolingStatus,
        String screeningCode
) {}
