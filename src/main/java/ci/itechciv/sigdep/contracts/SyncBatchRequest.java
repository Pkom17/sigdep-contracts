package ci.itechciv.sigdep.contracts;

import java.util.List;
import java.util.UUID;

/**
 * Enveloppe d'un lot de synchronisation poussé par l'agent vers le hub.
 *
 * <p>Le site est déclaré DEUX fois de façon indépendante, ce qui permet au hub
 * de détecter une mauvaise configuration d'agent (cf. incident : un mauvais
 * {@code siteCode} saisi dans la config a fait attribuer des données au mauvais
 * site) :
 * <ul>
 *   <li>{@code siteCode} : code du référentiel configuré sur l'agent
 *       (SIGDEP_SITE_CODE), saisi par l'administrateur — donc faillible ;</li>
 *   <li>{@code locationUuid} : uuid de la {@code location} OpenMRS réellement
 *       portée par les données locales, déterminé par le préflight de l'agent
 *       (non saisi à la main). Le hub le résout contre {@code core.sites
 *       .source_uuid} et REJETTE le lot si le site résolu diffère du
 *       {@code siteCode} ou du site de la clé API.</li>
 * </ul>
 * {@code locationUuid} peut être {@code null} pour un agent antérieur à ce
 * mécanisme (rétro-compatibilité) : le hub retombe alors sur la seule
 * vérification {@code siteCode} == site de la clé.
 */
public record SyncBatchRequest<T>(
        String siteCode,
        String locationUuid,
        UUID batchId,
        EntityType entityType,
        List<T> records
) {}
