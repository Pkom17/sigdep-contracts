package ci.itechciv.sigdep.contracts.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record PatientDto(
        UUID sourceUuid,
        String sex,
        LocalDate birthDate,
        Boolean birthDateEstimated,
        String birthPlace,
        String profession,
        String educationLevel,
        String maritalStatus,
        String religion,
        List<IdentifierDto> identifiers,
        Boolean voided
) {
    public record IdentifierDto(
            String typeCode,
            String value,
            Boolean preferred,
            LocalDate validFrom,
            LocalDate validTo
    ) {}
}
