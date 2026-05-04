package ci.itechciv.sigdep.contracts.dto;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

public record DispensationDto(
        UUID sourceUuid,
        UUID patientSourceUuid,
        LocalDate dispensationDate,
        String arvRegimen,
        Integer durationDays,
        LocalDate nextDispensationDate,
        LocalDate stockoutDate,
        Map<String, Object> extraData,
        Boolean voided
) {}
